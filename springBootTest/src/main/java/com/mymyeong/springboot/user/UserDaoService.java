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
		users.add(new User(1, "name1", new Date()));
		users.add(new User(2, "name2", new Date()));
		users.add(new User(3, "name3", new Date()));
	}

	public List<User> findUsers() {
		return users;
	}

	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(users.stream().max(Comparator.comparingInt(User::getId)).get().getId() + 1);
			user.setJoinDate(new Date());
		}

		users.add(user);
		
		return user;
	}

	public User findOne(int id) {
		return users.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
	}
}
