package com.mymyeong.springboot.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = { "passwd" })
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {
	private Integer id;

	@Size(min = 2, message = "name은 2글자 이상 입력해 주세요")
	@ApiModelProperty(notes = "사용자 이름")
	private String name;

	@Past
	@ApiModelProperty(notes = "사용자 등록일")
	private Date joinDate;

//	@JsonIgnore
	@ApiModelProperty(notes = "사용자 비밀번호")
	private String passwd;
//	@JsonIgnore
	@ApiModelProperty(notes = "사용자 생년월일")
	private String ssn;
}