<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>제목</th>
		<td data-col="title"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea id="content" cols="20" rows="20" readonly></textarea></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td data-col="writer"></td>
	</tr>
	<tr>
		<th>작성날짜</th>
		<td data-col="credat"></td>
	</tr>
</table>
<button onclick="location.href='/views/board/update?no=${param.no}'">수정</button>
<button onclick="deleteBoard()">삭제</button>
<script>
window.onload = function() {
	fetch('/board/${param.no}')
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		const tdCols = document.querySelectorAll('td[data-col]');
		for(const tdCol of tdCols) {
			tdCol.innerHTML = data[tdCol.getAttribute('data-col')];
		}
		document.querySelector('#content').value = data.content;
	})
}

function deleteBoard() {
	fetch('/board/${param.no}' , {
		method : 'DELETE'
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data === 1) {
			alert('삭제 성공');
			location.href = '/views/board/list';
		}else{
			alert('삭제 실패');
		}
	})
}
</script>
</body>
</html>