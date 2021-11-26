package com.mymyeong.springboot.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Kenneth", new Date()));
		users.add(new User(2, "Alice", new Date()));
		users.add(new User(3, "Elena", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		int id = users.stream().max(Comparator.comparingInt(User::getId)).get().getId() + 1;
		user.setId(id);
		users.add(user);

		return user;
	}

	public User findOne(int id) {
		return users.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
	}
}