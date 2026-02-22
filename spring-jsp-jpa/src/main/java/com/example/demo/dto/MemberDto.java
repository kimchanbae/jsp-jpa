package com.example.demo.dto;

import com.example.demo.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
	
	private Long id; // 있으면 update, 없으면 insert
	private String name;	// 유니크키
	private String sex;
	private String age;
	
}
