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
		<td><input type="text" id="title"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea id="content" cols="20" rows="20"></textarea></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><input type="text" id="writer"></td>
	</tr>
</table>
<button onclick="insertBoard()">등록</button>
<script>
function insertBoard() {
	const param = {
			title : document.querySelector('#title').value,
			content : document.querySelector('#content').value,
			writer : document.querySelector('#writer').value
	}
	fetch('/boards', {
		method : 'POST',
		headers : {
			'Content-type' : 'application/json'
		},
		body : JSON.stringify(param)
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data === 1) {
			alert('등록 성공');
			location.href = '/views/board/list';
		}else{
			alert('등록 실패');
		}
	})
}
</script>
</body>
</html>