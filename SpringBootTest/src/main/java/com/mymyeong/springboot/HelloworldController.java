package com.mymyeong.springboot;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hellow-world-internationalized")
	public String helloworldInternaltionalized(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("print.message", null, locale);
	}
}
