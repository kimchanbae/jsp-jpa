package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

//	@Query(value = "SELECT A.id, A.name, A.seq, A.sex, A.age, A.reg_date, A.upd_date, TO_CHAR(CAST(A.reg_date AS TIMESTAMP), 'YYYY-MM-DD HH24:MI:SS') as formatted_date FROM member A", nativeQuery = true)
//	List<Member> findAllFormatted();
//	List<Member> findAll();
	List<Member> findAllByOrderByRegDateDesc();
	
//	List<Member> search(String name, String sex);
	Page<Member> search(String name, String sex, Pageable pageable);
	
	 // 단건 조회 (기본 제공)
//	Optional<Member> findById(Long id);
	Optional<Member> findById(String id);
	
	// 조건 조회 예시
	Member findByName(String name);
	
}

