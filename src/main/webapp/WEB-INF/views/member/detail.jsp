<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../top.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#id").focus(); 

		const sex = "${member.sex}";
		$("#sex").val(sex);
	})

	function fnSave() {
		const id = $("#id").val();
		if (!id) {
			alert("아이디를 입력해 주세요.");
			$("#id").focus();
			return;
		}

		if (confirm("저장하시겠습니까?")) {
			const member = {
				seq : $("#seq").val(),
				id : $("#id").val(),
				name : $("#name").val(),
				age : $("#age").val(),
				sex : $("#sex").val(),
			};

			$.ajax({
				url : "/member/save",
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify(member),
				success : function(response) {
					const seq = $("#seq").val();
					if (seq == 0) {
						alert("회원가입 성공! ID: " + response.id);
					} else {
						alert("회원정보 수정! ID: " + response.id);
					}
				},
				error : function() {
					alert("회원가입 실패");
				}
			});
		}
	}
</script>

<body>
	<%@ include file="../navbar.jsp"%>

	<div class="container mt-5">
		<h2>회원 상세</h2>
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-body p-4">

						<form method="post" id="myForm">
							<input type="hidden" class="form-control" id="seq" value="${member.seq}" /> <label class="form-label">id</label>
							<div class="mb-3">
								<c:if test="${empty member.id}">
									<input type="text" class="form-control" id="id" name="id" value="" required />
								</c:if>
								<c:if test="${not empty member.id}">
									<input type="text" class="form-control" id="id" name="id" value="${member.id}" disabled="disabled" required />
								</c:if>
							</div>
							<div class="mb-3">
								<label class="form-label">이름</label> <input type="text" id="name" name="name" class="form-control" value="${member.name}" required placeholder="이름을 입력해주세요." />
							</div>
							<div class="mb-3 col-sm-4">
								<label class="form-label">나이</label> <input type="text" id="age" name="age" class="form-control form-control-sm" value="${member.age}" />
							</div>
							<div class="mb-3">
								<label for="select" class="form-label">성별</label> <select class="form-select" id="sex" name="sex">
									<option value="" selected>선택하세요</option>
									<option value="M">남자</option>
									<option value="W">여자</option>
								</select>
							</div>

							<div class="text-center">
								<a class="btn btn-primary" onclick="fnSave()">저장</a> <a href="/member" class="btn btn-secondary">목록</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>