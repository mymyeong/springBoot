package com.mymyeong.springboot.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "helloworld")
	public String helloWorld() {
		return "hello World";
	}

	@GetMapping(path = "helloworld-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}

	@GetMapping(path = "helloworld-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world : %s" , name));
	}
}
