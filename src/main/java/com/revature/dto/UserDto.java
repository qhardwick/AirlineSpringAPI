package com.revature.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.beans.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.revature.constants.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = { "password" }, allowSetters = true)
public class UserDto {

	@NotEmpty
	private String username;

	@NotEmpty
	@Size(min = 2, max = 30)
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 30)
	private String lastName;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Size(min = 8, max = 20)
	private String password;


	private UserType type;

	private int frequentFlyerMiles;

	private List<String> reservations;

	public UserDto() {
		this.type = UserType.PASSENGER;
		this.reservations = new ArrayList<>();
	}

	// Contructor to convert from User to UserDto
	public UserDto(User user) {
		this();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.type = user.getType();
		this.frequentFlyerMiles = user.getFrequentFlyerMiles();;
		this.reservations = user.getReservations();
	}

	// Convert from UserDto to User
	@JsonIgnore
	public User getUser() {
		User user = new User();
		user.setUsername(this.username);
		user.setFirstName(this.firstName);
		user.setLastName(this.lastName);
		user.setEmail(this.email);
		user.setType(this.type);
		user.setFrequentFlyerMiles(this.frequentFlyerMiles);
		user.setReservations(this.reservations);

		return user;
	}

}
