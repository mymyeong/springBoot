package com.mymyeong.springboot.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
	private static List<User> users = new ArrayList<>();

//	static {
//		users.add(new User(1, "Kenneth", new Date(), "pass1", "991231-1111111"));
//		users.add(new User(2, "Alice", new Date(), "pass2", "881231-1111111"));
//		users.add(new User(3, "Elena", new Date(), "pass3", "771231-1111111"));
//	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		int id = users.stream().max(Comparator.comparingInt(User::getId)).get().getId() + 1;
		user.setId(id);
		user.setJoinDate(new Date());
		users.add(user);

		return user;
	}

	public User findOne(int id) {
		return users.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
	}

	public User deleteById(int id) {
		User temp = users.stream().filter(v -> v.getId() == id).findFirst().orElse(null);

		if (temp != null) {
			users.remove(temp);
		}

		return temp;
	}

	public User updateUser(User user) {
		User temp = users.stream().filter(v -> v.getId() == user.getId()).findFirst().orElse(null);

		if (temp != null) {
			users.remove(temp);
			users.add(user);
		}

		return temp;
	}
}