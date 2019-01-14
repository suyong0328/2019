package memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import dao.MemberDao;

public class Idcheck implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		String view = "";
		
		MemberDao dao = MemberDao.getInstance();
		int result = dao.userCheck2(id);
		System.out.println(result);
		if(result==2) {
			request.setAttribute("id", id);
			view = "member/idFalse.jsp?num=2";
		}else {
			view = "member/idFalse.jsp?num=1";
		}
		return view;
	}

}
