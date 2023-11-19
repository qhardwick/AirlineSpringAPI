package com.revature.services;

import com.revature.beans.User;
import com.revature.dto.UserDto;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserHandlerTest {

    private static UserHandler userHandler;

    private static UserRepository userRepository;

    private static User user;
    private static ServerRequest request;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userHandler = new UserHandler(userRepository);

        request = mock(ServerRequest.class);
        defineUser();
    }

    private static void defineUser() {
        user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email@address.com");
        user.setPassword("password");

        when(userRepository.save(user)).thenReturn(Mono.just(user));
        when(userRepository.findById(user.getUsername())).thenReturn(Mono.just(user));
        when(request.bodyToMono(UserDto.class)).thenReturn(Mono.just(new UserDto(user)));
    }

    // Test addUser() success:
    @Test
    public void testAddUserSuccess() {

        // Call addUser() method:
        Mono<ServerResponse> response = userHandler.addUser(request);

        // Verify that the response is CREATED:
        StepVerifier.create(response)
                .expectNextMatches(serverResponse -> serverResponse.statusCode().equals(HttpStatus.CREATED))
                .verifyComplete();
    }
}
