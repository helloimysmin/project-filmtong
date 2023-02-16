# 마이페이지

- **비밀번호 확인 후에 회원 정보 수정이 가능하도록 구성**
```javascript
function getUserInfo() {
	fetch('/user-infos/' + ${param.uiNum})
	.then(function(res) {
		return res.text();
	})
	.then(function(data) {
		if(data) {
			data = JSON.parse(data);
			const inputObjs = document.querySelectorAll('#pwdConfirm input[id]');
			const uiPwd = document.querySelector('#uiPwd');
			for(const inputObj of inputObjs) {
				inputObj.value = data[inputObj.getAttribute('id')];
				uiPwd.value = '';
			}
		}else{
			swal('잘못된 접근.', '잘못된 접근입니다. 다시 시도하여주세요', 'error')
			.then(function() {
				location.href = '/';
			})
		}
		
	});
}
function passwordCheck(){
	const uiPwd = {
			uiPwd : document.querySelector('#uiPwdCheck').value
	}
	fetch('/user-infos/pwdCheck/' + ${param.uiNum}, {
		method : 'POST',
		headers : {
			'Content-type' : 'application/json'
		},
		body : JSON.stringify(uiPwd)
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data===true) {
			document.querySelector('#pwdConfirm').style.visibility='visible';
			swal('비밀번호 확인 완료', '회원 정보 수정 혹은 탈퇴가 가능합니다.', 'success');
		}else{
			swal('비밀번호를 확인해주세요', '잘못된 비밀번호입니다.' , 'error');
		}
	})
}
function userModify() {
	const uiNickname = document.querySelector('#uiNickname');
	if(uiNickname.value.trim().length < 1) {
		swal('이름 오류', '이름이 올바르지 않습니다.', 'warning');
		uiPwd.value = '';
		uiPwd.focus();
		return;
	}
	const uiPwd = document.querySelector('#uiPwd');
	if(uiPwd.value.trim().length < 6) {
		swal('비밀번호 오류', '비밀번호는 6글자 이상이어야합니다.', 'warning');
		uiPwd.value = '';
		uiPwd.focus();
		return;
	}
	const uiProfileImg = document.querySelector('#uiProfileImg');
	if(uiProfileImg.value == 0) {
		swal('프로필 사진', '프로필 사진을 선택해주세요', 'error');
		uiProfileImg.focus();
		return;
	}
	const param = {
			uiNickname : document.querySelector('#uiNickname').value,
			uiPwd : document.querySelector('#uiPwd').value,
			uiProfileImg : document.querySelector('#uiProfileImg').value
	}
	fetch('/user-infos/' + ${param.uiNum}, {
		method : 'PATCH',
		headers : {
			'Content-type' : 'application/json'
		},
		body : JSON.stringify(param)
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data===true) {
			swal('회원 정보 수정 완료', '수정이 완료되었습니다.', 'success')
			.then(function() {
				location.href = '';
			})
		}else{
			swal('잘못된 접근', '정보 수정을 다시 시도해주세요', 'error');
		}
	})
}
function userDelete() {
	fetch('/user-infos/' + ${param.uiNum}, {
		method : 'DELETE'
	})
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		if(data===true) {
			swal('회원 탈퇴 성공', '다음에 또 찾아주세요', 'success').
			then(function() {
				location.href = '/';
			})
		}else{
			swal('회원 탈퇴 실패', '다시 시도해주세요', 'error');
		}
	})
}
```

<img src="https://user-images.githubusercontent.com/115143371/216807439-f707bc6f-3fd8-4d46-be19-176201c50515.png">
<img src="https://user-images.githubusercontent.com/115143371/216807440-3a9522a7-a56f-4ffe-a156-fa700fc8d07f.png">
