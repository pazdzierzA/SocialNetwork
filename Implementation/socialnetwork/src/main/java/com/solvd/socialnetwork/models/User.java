package com.solvd.socialnetwork.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User extends BaseEntity {

	
	private String login;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	
	@JsonIgnore
	private List<Post> posts = new ArrayList<Post>();

	public User(Long id, String login, String email, String password, String firstName, String lastName,
			LocalDate birthDate) {
		super(id);
		this.login = login;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public User(String login, String email, String password, String firstName, String lastName,
			LocalDate birthDate) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}
	public User() {
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public static UserBuilder builder() {
		return new UserBuilder(new User());
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", birthDate=" + birthDate +  "]";
	}

	public static class UserBuilder{
		private final User user;

		public UserBuilder(User user) {
			this.user = user;
		}
		public UserBuilder login(String login) {
			this.user.setLogin(login);
			return this;
		}	

		public UserBuilder email(String email) {
			this.user.setEmail(email);
			return this;
		}
		public UserBuilder password(String password) {
			this.user.setPassword(password);
			return this;
		}
		public UserBuilder firstName(String firstName) {
			this.user.setFirstName(firstName);
			return this;
		}
		public UserBuilder lastName(String lastName) {
			this.user.setLastName(lastName);
			return this;
		}
		public UserBuilder birthDate(LocalDate birthDate) {
			this.user.setBirthDate(birthDate);
			return this;
		}
		public User build() {
			return this.user;
		}

	}

}
