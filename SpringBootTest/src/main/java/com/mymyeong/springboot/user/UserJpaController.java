package com.mymyeong.springboot.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.persistence.PostRemove;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<EntityModel<User>> retrieveUser(@PathVariable int id) {
		User temp = userRepository.findById(id).orElse(null);

		if (temp == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}

		EntityModel<User> model = EntityModel.of(temp);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));

		return ResponseEntity.ok(model);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User temp = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()//
				.path("/{id}")//
				.buildAndExpand(temp.getId())//
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/user/{id}/post")
	public List<Post> retrieveAllPostByUser(@PathVariable int id) {
		User temp = userRepository.findById(id).orElse(null);

		if (temp == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}

		return temp.getPost();
	}

	@PostMapping("/user/{id}/post")
	public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {
		User user = userRepository.findById(id).orElse(null);

		if (user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}

		post.setUser(user);
		Post temp = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()//
				.path("/{id}")//
				.buildAndExpand(temp.getId())//
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
