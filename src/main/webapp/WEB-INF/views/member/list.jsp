<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../top.jsp"%>

<body>

	<%@ include file="../navbar.jsp"%>

	<div class="container mt-5">
		<div class="row mb-4">
			<div class="col">
				<h2>회원 목록</h2>
			</div>
			<div class="col ms-auto text-end">총:${result.totalElements}건</div>
		</div>

		<div class="card shadow">
			<div class="card-body">
				<form method="post" action="member">
					<div class="row g-3">
						<div class="col col-md-3">
							<input type="text" name="name" class="form-control" placeholder="이름" value="${condition.name}">
						</div>
						<div class="col col-md-3">
							<select class="form-select" name="sex">
								<option value="" selected>선택하세요</option>
								<option value="M" ${condition.sex == 'M' ? 'selected' : ''}>남자</option>
								<option value="W" ${condition.sex == 'W' ? 'selected' : ''}>여자</option>
							</select>
						</div>
						<div class="col col-md-1 ms-auto text-end">
							<button type="submit" class="btn btn-primary w-100">검색</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<table border="1" class="table table-striped mt-3">
			<thead class="table-dark">
				<tr class="text-center">
					<th>이름</th>
					<th>성별</th>
					<th>나이</th>
					<th>이메일</th>
					<th>등록일시</th>
					<th>수정일시</th>
					<th>수정</th>
				</tr>
			</thead>
			<c:if test="${result.totalElements == 0}">
				<tr>
					<td colspan="7" class="text-center">조회된 데이터가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${result.totalElements > 0}">
				<c:forEach var="member" items="${result.content}">
					<tr>
						<td>${member.name}</td>
						<td class="text-center"><c:if test="${member.sex eq 'M'}">남자</c:if> <c:if test="${member.sex eq 'W'}">여자</c:if></td>
						<td class="text-center">${member.age}</td>
						<td></td>
						<td class="text-center">${member.formatRegdDate}</td>
						<td class="text-center">${member.upd_date}</td>
						<td class="text-center"><a href="/member/edit/${member.id}" class="btn btn-sm btn-warning">수정</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<nav class="d-flex justify-content-center mt-4">
			<ul class="pagination">
				<c:if test="${result.totalElements > 0}">
					<!-- 이전 버튼 -->
					<li class="page-item ${result.first ? 'disabled' : ''}"><a class="page-link" href="?page=${result.number - 1}&name=${condition.name}&sex=${condition.sex}"> 이전 </a></li>

					<!-- 페이지 번호 -->
					<c:forEach begin="0" end="${result.totalPages - 1}" var="i">
						<c:url var="pageUrl" value="/member">
							<c:param name="page" value="${i}" />
							<c:param name="name" value="${condition.name}" />
							<c:param name="sex" value="${condition.sex}" />
						</c:url>
						<li class="page-item ${i == result.number ? 'active' : ''}"><a class="page-link" href="${pageUrl}"> ${i + 1} </a></li>
					</c:forEach>

					<!-- 다음 버튼 -->
					<li class="page-item ${result.last ? 'disabled' : ''}"><a class="page-link" href="?page=${result.number + 1}&name=${condition.name}&sex=${condition.sex}"> 다음 </a></li>
				</c:if>
			</ul>
		</nav>

		<div class="text-center">
			<div class="text-center mt-20">
				<a href="/member/edit" class="btn btn-primary">등록</a>
			</div>
		</div>
	</div>

</body>
</html>