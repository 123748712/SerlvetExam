<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
<form name="listForm">
	<input type="hidden" name="pageNo">
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
<%
	int memSize = memList.size();
	if(memSize > 0) {
		for(int i = 0; i < memSize; i++){
%>
		<tr>
			<td><%=memList.get(i).getMemId() %></td>
			<td><%=memList.get(i).getMemName() %></td>
			<td><%=memList.get(i).getMemTel() %></td>
			<td><%=memList.get(i).getMemAddr() %></td>
		</tr>
<% // for문만큼 <tr> 구문이 반복된다. 즉, 회원수만큼 <tr>이 생성된다.
		} // for end
	} // if end
%>
	<tr align="center">
		<td colspan="4"><a href="insert.do">[회원 등록]</a></td>
	</tr>
	</table>
</form>
</body>
</html>