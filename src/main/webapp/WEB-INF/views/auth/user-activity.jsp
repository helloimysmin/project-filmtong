<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="html">
<meta name="viewport" content="width=device-width, initial-scale=1.0 maximum-scale=1, minimum-scale=1">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>나의 활동 내역</title>
<%@ include file="/WEB-INF/views/common/css_coomon.jsp"%>
<script type="text/ecmascript" src="/test/js/jquery-3.3.1.min.js"></script> 
<script type="text/ecmascript" src="/test/js/trirand/i18n/grid.locale-kr.js"></script>
<script type="text/ecmascript" src="/test/js/trirand/jquery.jqGrid.min.js"></script>
<link rel="stylesheet" href="/test/css/trirand/bootstrap.min.css"> <!-- userActivity.jsp에 적용 되어야 하는 css -->
<link rel="stylesheet" href="/test/css/trirand/_nav.scss"> <!-- 적용안되는 중 -->
<link rel="stylesheet" type="text/css" media="screen" href="/test/css/trirand/ui.jqgrid-bootstrap.css" />
</head>
<style type="text/css">
.ui-jqgrid tr.jqgrow td { 
	white-space: normal !important;
	height:auto;
	vertical-align:middle;
	padding-top:3px;
	padding-bottom:3px
}
</style>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<section class="normal-breadcrumb set-bg" data-setbg="img/normal-breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                <c:if test="${userInfo ne null}">
                    <div class="normal__breadcrumb__text">
                        <h2>${userInfo.uiNickname} 님의 활동 내역입니다.</h2>
                        <p></p>
                    </div>
                </c:if>
                <c:if test="${userInfo eq null}">
                    <div class="normal__breadcrumb__text">
                        <h2>로그인이 필요합니다.</h2>
                        <p></p>
                    </div>
                </c:if>
                </div>
            </div>
        </div>
    </section>
    <div class="container" style="margin-bottom:20%">
	    <ul class="nav nav-tabs"  role="tab">
		  <li class="nav-item" >
		    <a class="nav-link" aria-current="page" onclick="getReviewTable()" style="cursor:pointer;">내가 쓴 리뷰</a>
		  </li>
		  <li class="nav-item" >
		    <a class="nav-link" onclick="getThumbsUpTable()" style="cursor:pointer;">좋아요 한 영화</a>
		  </li>
		  <li class="nav-item" >
		    <a class="nav-link" onclick="getActorThumbsUpTable()" style="cursor:pointer;">좋아요 한 배우</a>
		  </li>
		</ul>
	<div class="card text-center">
		<div class="card-body">
			<table id="jqGrid" class="table table-hover" style="background-color:white;border-radius:5px;color:#000"></table>
			<div id="gqGridPager">
		     </div>
		</div>
    </div>
  </div>
  
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<%@include file="/WEB-INF/views/common/js_auth.jsp" %>
<script>
window.onload = function() {
	searchMovies();
	getReviewTable();
}
$(window).on('resize.jqGrid', function(){
	jQuery("#jqGrid").jqGrid('setGridWidth',$(".card-body").width());
});

function getReviewTable() {
	document.querySelector('.nav li:nth-child(2)').classList.remove('active');
	document.querySelector('.nav li:nth-child(3)').classList.remove('active');
	document.querySelector('.nav li:nth-child(1)').classList.add('active');
	const config = {
			url:'/movie-reviews/page?uiNum=${param.uiNum}',
			mtype:'GET',
			styleUI : 'Bootstrap',
			datatype:'json',
			pager:'#gqGridPager',
			colModel : [
				{label:'영화아이디', name:'id', key: true, hidden: true},
				{label:'닉네임', name:'uiNickname', hidden: true},
				{label:'영화제목', name:'title', width: 260},
				{label:'리뷰내용', name:'mrContent', width: 375},
				{label:'작성일시', name:'credat', width: 260,formatter: dateFormatter},
				{label:'작성시간', name:'cretim', hidden:true},
				{label:'내 별점', name:'mrRating', width: 220, formatter: thumbsUp}
			],
			jsonReader:{
				root:'list',
				page:'pageNum',
				total:'pages'
			},
			width:1115,
			autowidth: true,
			height:'auto',
			rowNum:10,
			onSelectRow: function(id) {
				$(location).attr('href', '/views/movie-trailer?id=' + id )
			}
	}
	$.jgrid.gridUnload('#jqGrid');
	$('#jqGrid').jqGrid(config);
}

function getThumbsUpTable() {
	document.querySelector('.nav li:nth-child(1)').classList.remove('active');
	document.querySelector('.nav li:nth-child(3)').classList.remove('active');
	document.querySelector('.nav li:nth-child(2)').classList.add('active');
	const config = {
			url:'/user-evaluation/page?uiNum=${param.uiNum}',
			mtype:'GET',
			styleUI : 'Bootstrap',
			datatype:'json',
			pager:'#gqGridPager',	
			colModel : [
				{label:'영화아이디', name:'id', key: true, hidden: true},
				{label:'영화제목', name:'title'},
				{label:'원제', name:'originalTitle'},
				{label:'영화평점', name:'voteAverage'},
				{label:'개봉일자', name:'releaseDate'}
			],
			jsonReader:{
				root:'list',
				page:'pageNum',
				total:'pages'
			},
			width:1115,
			autowidth: true,
			height:'auto',
			rowNum:10,
			onSelectRow: function(id) {
				$(location).attr('href', '/views/movie-trailer?id=' + id )
			}
	}
	$.jgrid.gridUnload('#jqGrid');
	$('#jqGrid').jqGrid(config);
}

function getActorThumbsUpTable() {
	document.querySelector('.nav li:nth-child(1)').classList.remove('active');
	document.querySelector('.nav li:nth-child(2)').classList.remove('active');
	document.querySelector('.nav li:nth-child(3)').classList.add('active');
	const config = {
			url:'/actor-evaluation/page?uiNum=${param.uiNum}',
			mtype:'GET',
			styleUI : 'Bootstrap',
			datatype:'json',
			pager:'#gqGridPager',	
			colModel : [
				{label:'인물아이디', name:'piId', key: true, hidden: true},
				{label:'이름', name:'piName'},
				{label:'홈페이지', name:'piHomepage', formatter: hasHomepage},
				{label:'생년월일', name:'piBirthday',},
				{label:'출생지', name:'piPlaceOfBirth'},
				{label:'성별', name:'piGender', formatter: genderFormatter}
			],
			jsonReader:{
				root:'list',
				page:'pageNum',
				total:'pages'
			},
			width:1115,
			autowidth: true,
			height:'auto',
			rowNum:10,
			onSelectRow: function(piId) {
				$(location).attr('href', '/views/person-info?personId=' + piId )
			}
	}
	$.jgrid.gridUnload('#jqGrid');
	$('#jqGrid').jqGrid(config);
}

function dateFormatter(cellvalue, options, rowObject) {
     return cellvalue + ' ' + rowObject.cretim;
}
function thumbsUp(cellvalue, options, rowObject) {
	let html = '';
	for(let i=0; i<cellvalue; i++) {
		html += '<span style="color: rgba(250, 208, 0, 0.99); font-size: 1.5rem">★<span>';
	}
	   return html;
}

function genderFormatter(cellvalue, options, rowObject) {
	let html = '';
	if(cellvalue === '2') {
		html = '남자';
	}else if(cellvalue === '1') {
		html = '여자';
	}
	return html;
}

function hasHomepage(cellvalue, options, rowObject) {
	let html = '';
	if(!cellvalue) {
		html = '등록된 홈페이지가 없습니다.';
	}
	return html;
}
</script>
</body>
</html>