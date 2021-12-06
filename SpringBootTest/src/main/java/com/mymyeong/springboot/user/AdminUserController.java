package com.mymyeong.springboot.user;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
	private UserDaoService service;

	public AdminUserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public MappingJacksonValue retrieveAllUsers() {
		List<User> list = service.findAll();

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter//
				.filterOutAllExcept("id", "name", "joinDate", "ssn");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/v1/user/{id}")
	public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
		User temp = service.findOne(id);
		if (temp == null) {
			throw new UserNotFoundException(String.format("USER ID [%s] NOT Found", id));
		} else {
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter//
					.filterOutAllExcept("id", "name", "joinDate", "ssn");
			FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

			MappingJacksonValue mapping = new MappingJacksonValue(temp);
			mapping.setFilters(filters);

			return mapping;
		}
	}

	@GetMapping("/v2/user/{id}")
	public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
		User temp = service.findOne(id);
		if (temp == null) {
			throw new UserNotFoundException(String.format("USER ID [%s] NOT Found", id));
		} else {

			UserV2 userV2 = new UserV2();

			BeanUtils.copyProperties(temp, userV2);
			userV2.setGrade("VIP");

			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter//
					.filterOutAllExcept("id", "name", "joinDate", "grade");
			FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

			MappingJacksonValue mapping = new MappingJacksonValue(userV2);
			mapping.setFilters(filters);

			return mapping;
		}
	}
}