package com.mymyeong.springboot.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;

	@Size(min = 2, message = "name은 2글자 이상 입력해 주세요")
	private String name;
	@Past
	private Date joinDate;
}