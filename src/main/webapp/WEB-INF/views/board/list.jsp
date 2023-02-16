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
		<th>번호</th>
		<th>제목</th>
		<th>내용</th>
		<th>작성자</th>
		<th>작성날짜</th>	
	</tr>
	<tbody id="tBody"></tbody>
</table>
<script>
window.onload = function() {
	fetch('/boards')
	.then(function(res) {
		return res.json();
	})
	.then(function(boards) {
		let html = '';
		for(const board of boards) {
			html += '<tr style="cursor:pointer" onclick="location.href=\'/views/board/view?no=' + board.no + '\'">';
			html += '<td>' + board.no + '</td>';
			html += '<td>' + board.title + '</td>';
			html += '<td>' + board.content + '</td>';
			html += '<td>' + board.writer + '</td>';
			html += '<td>' + board.credat + '</td>';
			html += '</tr>';
		}
		document.querySelector('#tBody').innerHTML = html;
	})
}
</script>
</body>
</html>