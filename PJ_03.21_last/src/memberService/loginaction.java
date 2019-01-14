package memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandProcess;
import dao.MemberDao;

public class loginaction implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 String id = request.getParameter("id");
	     String password = request.getParameter("password");
	     String view = "";
	     HttpSession session = request.getSession();
	     MemberDao dao = MemberDao.getInstance();
	      int result = dao.userCheck(id, password);
	     if(result ==1){//정상이면
	    		 session.setAttribute("id", id);
	             session.setAttribute("password", password);
	             view = "main.do";
	     } else if(result == 2) { // 아이디 없음
	    	 view ="member/loginFalse.jsp?num=2";
	     } else { // 비밀번호 틀림
	    	 view = "member/loginFalse.jsp?num=1";
	     }
		return view;
	}
}
