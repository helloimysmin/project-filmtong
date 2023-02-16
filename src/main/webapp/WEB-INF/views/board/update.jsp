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
		<td><input type="text" id="writer" readonly></td>
	</tr>
	<tr>
		<th>작성날짜</th>
		<td><input type="text" id="credat" readonly></td>
	</tr>
</table>
<button onclick="updateBoard()">수정</button>
</body>
<script>
window.onload = function() {
	fetch('/board/${param.no}')
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		document.querySelector('#title').value = data.title;
		document.querySelector('#content').value = data.content;
		document.querySelector('#writer').value = data.writer;
		document.querySelector('#credat').value = data.credat;
	})
}

function updateBoard() {
	const param = {
			title : document.querySelector('#title').value,
			content : document.querySelector('#content').value
	}
	fetch('/board/${param.no}', {
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
			alert('수정 성공');
			location.href='/views/board/list';
		}else{
			alert('수정 실패');
		}
	})
}
</script>
</html>