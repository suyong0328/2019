function ask() {
	if (delForm.password.value == "") {
		alert("비밀번호를 입력하세요.");
		delForm.password.focus();
		return false;
	}
	return true;
	/*
	 * else { var isTrue = false; window.open("", "MsgWindow",
	 * "width=200,height=100").document .write("<p>정말 글을 삭제하시겠습니까?</p><input
	 * type='button' value='확인' onclick='yes()'><script>function yes()
	 * {isTrue.value = True;location.href='deleteQnaPro.do';window.close();}</script>");
	 * return isTrue; }
	 * 
	 * var num = document.getElementById('num').value; var pageNum =
	 * document.getElementById('pageNum').value;
	 * 
	 * return false;
	 */
}