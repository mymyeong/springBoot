package com.mymyeong.springboot.user;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = { "passwd" })
@JsonFilter("UserInfoV2")
@EqualsAndHashCode(callSuper=false)
public class UserV2 extends User {

	private String grade;
}