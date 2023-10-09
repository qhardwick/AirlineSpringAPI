package com.revature.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Table("Passenger")
public class User {

	@PrimaryKey
	private String username;

	@Column("first_name")
	private String firstName;

	@Column("last_name")
	private String lastName;

	@NotEmpty
	private String email;

	private String password;

	@Column("user_type")
	private UserType type;

	@Column("frequent_flyer_miles")
	private int frequentFlyerMiles;

	private List<Reservation> reservations;

	public User() {
		super();
		this.type = UserType.USER;
		this.reservations = new ArrayList<>();
	}

	public User(String username, String firstName, String lastName, String email) {
		this();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
}
