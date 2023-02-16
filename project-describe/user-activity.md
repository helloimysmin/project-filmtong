# 유저들 활동내역을 확인 할 수 있는 페이지 구성
> pageHelper와 jqGrid를 사용하여 구성

- **유저 리뷰 확인**
```javascript 
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
```

<img src="https://user-images.githubusercontent.com/115143371/216807906-3ac4fb2d-0e79-472a-b617-9ac92091c001.png">



- **영화 좋아요 확인**
```javascript
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
```

<img src="https://user-images.githubusercontent.com/115143371/216807910-71e17fed-46d3-48f1-8201-3dd00950c633.png">

- **배우에 대한 좋아요 확인**
```javascript
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
```

<img src="https://user-images.githubusercontent.com/115143371/216807903-ca55485c-cde0-4d02-bf45-e62aebcf2560.png">


**formatter**
```javascript
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
```
