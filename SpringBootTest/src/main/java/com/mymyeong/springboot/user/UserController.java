package com.mymyeong.springboot.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		User temp = service.findOne(id);
		if (temp == null) {
			throw new UserNotFoundException(String.format("USER ID [%s] NOT Found", id));
		} else {
			return temp;
		}
	}

	@PostMapping("/user")
	public ResponseEntity<Object> newUser(@Valid @RequestBody User user) {
		User temp = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()//
				.path("/{id}")//
				.buildAndExpand(temp.getId())//
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		User temp = service.deleteById(id);

		if (temp == null) {
			throw new UserNotFoundException(String.format("USER ID [%s] NOT Found", id));
		}
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		User temp = service.updateUser(user);

		if (temp == null) {
			throw new UserNotFoundException(String.format("USER ID [%s] NOT Found", user.getId()));
		}

		return temp;
	}

}