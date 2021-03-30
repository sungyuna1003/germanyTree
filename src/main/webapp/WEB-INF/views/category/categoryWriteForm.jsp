<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<div class="container">
	<form method="get" action="categoryInsert">
		<select class="custom-select" name="ctGubun1">
			<option value="1">Apartment 아파트</option>
			<option value="2">House 주택</option>
			<option value="3">Share room 쉐어룸</option>
			<option value="4">short term accommodation 단기방</option>
		</select> 
		<input type="text" class="form-control" name="ctGubun2" placeholder="강의명을 입력하세요." required>
		<input type="submit" class="form-control foode-btn" value="생성">
	</form>
</div>
<%@include file="../include/footer.jsp"%>
</body>
</html>

