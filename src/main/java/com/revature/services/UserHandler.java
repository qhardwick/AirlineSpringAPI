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

@Service
public class UserHandler {

    private UserRepository userRepository;

    @Autowired
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Find all Users:
    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<UserDto> userDtoFlux = userRepository.findAll().map(user -> new UserDto(user));
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(userDtoFlux, UserDto.class);
    }

    // Find User by username:
    public Mono<ServerResponse> findByUsername(ServerRequest request) {
        String username = request.pathVariable("username");
        Mono<UserDto> foundUserDto = userRepository.findById(username).map(user -> new UserDto(user));
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(foundUserDto, UserDto.class);
    }

    // Add new User:
    public Mono<ServerResponse> addUser(ServerRequest request) {

        Mono<User> newUserMono = request.bodyToMono(UserDto.class).map(userDto -> userDto.toUser());
        return newUserMono.flatMap(newUser -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(userRepository.save(newUser), UserDto.class));
    }

    // User login method:
    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<UserDto> userDtoMono = request.bodyToMono(UserDto.class);
        return userDtoMono.flatMap(userDto -> {
            Mono<User> userMono = userRepository.findById(userDto.getUsername());
            return userMono.flatMap(user -> {
                if (user.getPassword().equals(userDto.getPassword())) {
                    return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Mono.just(new UserDto(user)), UserDto.class);
                } else {
                    return ServerResponse.badRequest().build();
                }
            }).switchIfEmpty(ServerResponse.notFound().build());
        });
    }

    // User logout method:
    public Mono<ServerResponse> logout(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Mono.just("User logged out."), String.class);
    }

    // Update User:
    public Mono<ServerResponse> updateUser(ServerRequest request) {
        String username = request.pathVariable("username");
        Mono<User> userMono = userRepository.findById(username);
        Mono<UserDto> userDtoMono = request.bodyToMono(UserDto.class);
        return userMono.zipWith(userDtoMono, (user, userDto) -> {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setType(userDto.getType());
            user.setFrequentFlyerMiles(userDto.getFrequentFlyerMiles());
            user.setReservations(userDto.getReservations());
            return user;
        }).flatMap(user -> ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(userRepository.save(user), UserDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // Delete User:
    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String username = request.pathVariable("username");
        Mono<User> userMono = userRepository.findById(username);
        return userMono.flatMap(user -> ServerResponse.ok().build(userRepository.delete(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
