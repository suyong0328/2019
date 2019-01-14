function chk(){
	var id = document.getElementById('id');
	var pwd = document.getElementById('password');
	if(!id.value){
		alert("id를 입력하세요.");
		id.focus();
		return false;
	}
	if(!pwd.value){
		alert("password를 입력하세요.");
		pwd.focus();
		return false;
	}
	return true;
}
function app(){
	window.location.href="sub.do";
}
function app2(){
	window.location.href="idpass.do";
}
function writeSave() {
	if (document.writeForm.writer.value == "") {
		alert('작성자를 입력하세요.');
		document.writeForm.writer.focus();
		return false;
	}
	if (document.writeForm.subject.value == "") {
		alert('제목을 입력하세요.');
		document.writeForm.subject.focus();
		return false;
	}
	if (document.writeForm.content.value == "") {
		alert('내용을 입력하세요.');
		document.writeForm.content.focus();
		return false;
	}
	if (document.writeForm.password.value == "") {
		alert('비밀번호를 입력하세요.');
		document.writeForm.password.focus();
		return false;
	}
	return true;
}
function chk2(){
	var id = document.getElementById('id');
	var pwd = document.getElementById('password');
	var pwd2 = document.getElementById('password2');
	var name = document.getElementById('name');
	var rrnum1 = document.getElementById('rrnum1');
	var rrnum2 = document.getElementById('rrnum2');
	var zipno = document.getElementById('zipno');
	var address1 = document.getElementById('address1');
	var address2 = document.getElementById('address2');
	var tel2 = document.getElementById('tel2');
	var tel3 = document.getElementById('tel3');
	var email = document.getElementById('email');
	if(!id.value){
		alert("id를 입력하세요");
		id.focus();
		return false;
	}
	if(!pwd.value){
		alert("password를 입력하세요.");
		pwd.focus();
		return false;
	}
	if(!pwd2.value){
		alert("password를 입력하세요.");
		pwd2.focus();
		return false;
	}
	if(pwd.value!=pwd2.value){
		 alert("PW가 서로 다릅니다.");
		 pwd.value="";
		 pwd2.value="";
		 pwd.focus();
		 return false;
	 }
	if(!name.value){
		alert("이름을 입력하세요.");
		name.focus();
		return false;
	}
	if(!rrnum1.value){
		alert("주민번호를 입력하세요.");
		rrnum1.focus();
		return false;
	}
	if(!rrnum2.value){
		alert("주민번호를 입력하세요.");
		rrnum2.focus();
		return false;
	}
	if(!zipno.value){
		alert("우편번호를 입력하세요.");
		zipno.focus();
		return false;
	}
	if(!address1.value){
		alert("주소를 입력하세요.");
		address1.focus();
		return false;
	}
	if(!address2.value){
		alert("주소를 입력하세요.");
		address2.focus();
		return false;
	}
	if(!tel2.value){
		alert("휴대전화 번호를 입력하세요.");
		tel2.focus();
		return false;
	}
	if(!tel3.value){
		alert("휴대전화 번호를 입력하세요.");
		tel3.focus();
		return false;
	}
	if(!email.value){
		alert("email를 입력하세요.");
		email.focus();
		return false;
	}
	return true;
}
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipno').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('address1').value = fullRoadAddr;

            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                //예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
}/* 우편번호 script끝 */

