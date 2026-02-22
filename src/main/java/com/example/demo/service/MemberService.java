package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 전체 회원 조회
	@Transactional(readOnly = true)
	public List<Member> findAll() {
//		return memberRepository.findAll();
//		return memberRepository.findAllFormatted();
		return memberRepository.findAllByOrderByRegDateDesc();
	}
	
	@Transactional(readOnly = true)
//	public List<Member> search(Member member){
//		return memberRepository.search(member.getName(), member.getSex());
	public Page<Member> search(Member member, Pageable pageable){
		return memberRepository.search(member.getName(), member.getSex(), pageable);
	}

	// 단건 조회
//	public Member findById(Long id) {
	public Member findById(String id) {
		return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원없음"));
	}

	public Member findByName(String name) {
		return memberRepository.findByName(name);
	}

	public Member save(Member dto) {
		// 1. 신규 저장
		if (dto.getSeq() == 0) {
			// 현재 시간 가져오기
			LocalDateTime now = LocalDateTime.now();
			dto.setRegDate(now);
			return memberRepository.save(dto);
		}

		// 2. 수정
		Member member = memberRepository.findById(dto.getId())
				.orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
		member.setName(dto.getName());
		member.setAge(dto.getAge());
		member.setSex(dto.getSex());

		return member;
	}

}
