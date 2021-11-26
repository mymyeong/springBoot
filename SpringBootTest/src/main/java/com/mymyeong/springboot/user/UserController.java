package com.mymyeong.springboot.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public User newUser(@RequestBody User user) {
		return service.save(user);
	}

}