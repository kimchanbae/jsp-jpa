package com.example.demo.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {

	@Id // 테이블의 PK(Primary Key)와 매핑.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
	@Column(nullable = false)
	private int seq;
	@Column(nullable = false, length = 20, unique = true) // 컬럼 제약조건
//	private Long id;
	private String id;
	@Column(nullable = false, length = 100) // 컬럼 제약조건
	private String name;
	@Column(length = 1) // 컬럼 제약조건
	private String sex;
	@Column(length = 3) // 컬럼 제약조건
	private String age;
	@Column(length = 300) // 컬럼 제약조건
	private String email;
	@Column(name = "reg_date", nullable = false)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH24:mm:ss")
//	private Timestamp regDate;
	private LocalDateTime regDate; 
	@Formula("TO_CHAR(CAST(reg_date AS TIMESTAMP), 'YYYY-MM-DD HH24:MI:SS')")	// 읽기 전용 가상 컬럼(등록일시)
	private String formatRegdDate;
	@Column(name = "upd_date")
	private Timestamp upd_date;

//	@Column(name="formatted_date")
//	private String formattedDate;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	
//	public String getRegDate() {
//		String strFormatedDate = formatDate(regDate);
//		
//		return strFormatedDate;
		
//		return regDate;
//	}
	
	public String formatDate(String date) {
		if(date == null || date.isEmpty()) return null;
		// 입력 데이터의 포맷 정의 (마이크로초 포함)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
		// 출력하고 싶은 포맷 정의 (초까지만)
		DateTimeFormatter outupFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// 변환 수행: String -> LocalDateTime -> String
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		
		return dateTime.format(outupFormatter);
	}

//	public void setRegDate(String regDate) {
//		this.regDate = regDate;
//	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime now) {
		this.regDate = now;
	}

	public String getFormatRegdDate() {
		return formatRegdDate;
	}
	
	public void setFormatRegdDate(String formatRegdDate) {
		this.formatRegdDate = formatRegdDate;
	}

	public Timestamp getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(Timestamp upd_date) {
		this.upd_date = upd_date;
	}

	public void update() {

	}

}
