package com.example.demo.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

@Controller
//@RestController
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 회원 목록
	@GetMapping
//	public String list(Model model) {
//		model.addAttribute("members", memberService.findAll());
	public String list(Member condition, @PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Member> result = memberService.search(condition, pageable);
		
		model.addAttribute("result", result);
//		model.addAttribute("totalCount", result.getTotalElements());	// 총건수
		model.addAttribute("condition", condition);						// 검색조건
		return "member/list";
	}

	// 회원 목록
	@PostMapping
	public String search(Member condition, @PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Member> result = memberService.search(condition, pageable);
		
		model.addAttribute("result", result);
//		model.addAttribute("totalCount", result.getTotalElements());	// 총건수
		model.addAttribute("condition", condition);						// 검색조건
		return "member/list";
	}

	// 회원 상세
	@GetMapping("/edit/{id}")
//    public String detail(@PathVariable String name, Model model) {
//	public String detail(@PathVariable Long id, Model model) {
	public String detail(@PathVariable String id, Model model) {
//    public String detail(@RequestParam String name, Model model) {
		Member member = memberService.findById(id);
		model.addAttribute("member", member);

//    	List<Member> member = memberService.findByName(name);
//    	model.addAttribute("member", memberService.findByName(name));

		return "member/detail";
	}

	// 회원 상세
	@GetMapping("/edit")
	public String detail(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "member/detail";
	}

	@PostMapping("/save")
	@ResponseBody
	public Member save(@RequestBody Member member, Model model) {
		return memberService.save(member);

//		return "member/list";
	}

}
