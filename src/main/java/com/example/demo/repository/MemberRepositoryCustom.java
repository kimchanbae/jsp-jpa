package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Member;

public interface MemberRepositoryCustom {

//	List<Member> search(String name, String sex);
	Page<Member> search(String name, String sex, Pageable pageable);
	
}
