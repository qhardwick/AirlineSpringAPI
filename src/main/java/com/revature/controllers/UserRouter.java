package com.revature.controllers;

import com.revature.services.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.validation.Valid;

@Configuration("userRouter")
public class UserRouter {

    private UserHandler userHandler;

    @Autowired
    public UserRouter(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> userRouterFunction() {
        return RouterFunctions.route()

                // Register new User:
                .POST("/airline/users", request -> userHandler.addUser(request))

                // Find User by Username:
                .GET("/airline/users/{username}", request -> userHandler.findByUsername(request))

                // User login:
                .POST("/airline/users/login", request -> userHandler.login(request))

                // User logout:
                .POST("/airline/users/logout", request -> userHandler.logout(request))

                // Update User:
                .PUT("/airline/users/{username}", request -> userHandler.updateUser(request))

                // Delete User:
                .DELETE("/airline/users/{username}", request -> userHandler.deleteUser(request))

                .build();
    }
}
