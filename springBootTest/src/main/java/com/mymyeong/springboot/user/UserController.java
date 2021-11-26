package com.mymyeong.springboot.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	private UserDaoService service;

	public UserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrivewAllUsers() {
		return service.findUsers();
	}

	@GetMapping("/user/{id}")
	public User retriveUser(@PathVariable int id) {
		User user = service.findOne(id);

		if (user == null) {
			throw new UserNotFoundException(String.format("id [%s] not found", id));
		}

		return user;
	}

	@PostMapping("/user")
	public ResponseEntity<Object> insertUser(@RequestBody User user) {
		user = service.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()//
				.path("/{id}")//
				.buildAndExpand(user.getId())//
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
