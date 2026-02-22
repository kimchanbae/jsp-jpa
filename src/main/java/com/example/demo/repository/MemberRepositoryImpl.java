package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.demo.entity.Member;
import com.example.demo.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	public MemberRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
//	public List<Member> search(String name, String sex) {
	public Page<Member> search(String name, String sex, Pageable pageable) {
		QMember member = QMember.member;

		List<Member> content = queryFactory.selectFrom(member).where(nameLike(name), sexEq(sex))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(member.regDate.desc()).fetch();
		Long total = queryFactory.select(member.count()).from(member).where(nameLike(name), sexEq(sex)).fetchOne();
		
		return new PageImpl<>(content, pageable, total);
		
//		return queryFactory.selectFrom(member).where(nameLike(name), sexEq(sex)).orderBy(member.regDate.desc()).fetch();
	}

	private BooleanExpression nameLike(String name) {
		return StringUtils.hasText(name) ? QMember.member.name.contains(name) : null;
	}

	private BooleanExpression sexEq(String sex) {
		return StringUtils.hasText(sex) ? QMember.member.sex.eq(sex) : null;
	}

}
