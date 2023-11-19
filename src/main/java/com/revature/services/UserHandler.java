package com.revature.services;

import com.revature.beans.User;
import com.revature.dto.UserDto;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@Service
public class UserHandler {

    private UserRepository userRepository;

    @Autowired
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add new User:
    // TODO: Solve Validation issue:
    @Valid
    public Mono<ServerResponse> addUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(UserDto.class).map(UserDto::getUser);
        return ServerResponse.created(URI.create("/airline/users")).body(userRepository.saveAll(userMono), UserDto.class);
    }

    // Find User by username:
    public Mono<ServerResponse> findByUsername(ServerRequest request) {
        String username = request.pathVariable("username");
        Mono<UserDto> userDtoMono = userRepository.findById(username).map(UserDto::new);
        return ServerResponse.ok().body(userDtoMono, UserDto.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // User login method:
    public Mono<ServerResponse> login(ServerRequest request) {
        String username = request.queryParam("username").get();
        String password = request.queryParam("password").get();
        Mono<UserDto> loggedUserMono = userRepository.findById(username).map(UserDto::new);

        return loggedUserMono.flatMap(user -> {
            if (user.getPassword().equals(password)) {
                return ServerResponse.ok().body(user, UserDto.class);
            } else {
                return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).switchIfEmpty(ServerResponse.status(HttpStatus.UNAUTHORIZED).build());
    }

    // User logout method:
    public Mono<ServerResponse> logout(ServerRequest request) {
        return ServerResponse.noContent().build();
    }
    
    // Update User:
    // For the moment we're leaving open the possibility that the request will only contain some of the fields.
    // TODO: Solve Validation issue:
    public Mono<ServerResponse> updateUser(ServerRequest request) {
        String username = request.pathVariable("username");
        Mono<User> userToUpdateMono = userRepository.findById(username);
        Mono<UserDto> updatedInfoMono = request.bodyToMono(UserDto.class);
        return userToUpdateMono.zipWith(updatedInfoMono, (user, updatedInfo) -> {
            System.out.println("User to update: " + user.toString());
            System.out.println("Updated info: " + updatedInfo.toString());
            if(updatedInfo.getFirstName() != null) {
                user.setFirstName(updatedInfo.getFirstName());
            }
            if(updatedInfo.getLastName() != null) {
                user.setLastName(updatedInfo.getLastName());
            }
            if(updatedInfo.getEmail() != null) {
                user.setEmail(updatedInfo.getEmail());
            }
            if(updatedInfo.getPassword() != null) {
                user.setPassword(updatedInfo.getPassword());
            }
            if(updatedInfo.getType() != null) {
                user.setType(updatedInfo.getType());
            }
            if(updatedInfo.getFrequentFlyerMiles() != 0) {
                user.setFrequentFlyerMiles(updatedInfo.getFrequentFlyerMiles());
            }
            if(updatedInfo.getReservations() != null) {
                user.setReservations(updatedInfo.getReservations());
            }

            System.out.println("Updated User: " + user.toString());
            return user;
        }).flatMap(updatedUser -> ServerResponse.ok().body(userRepository.save(updatedUser), UserDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // Delete User:
    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String username = request.pathVariable("username");
        Mono<User> userMono = userRepository.findById(username);
        return userMono.flatMap(user -> ServerResponse.noContent().build(userRepository.delete(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


}
