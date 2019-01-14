package memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import dao.MemberDao;
import dto.Member;

public class idchk implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");

		MemberDao dao = MemberDao.getInstance();
		int result = dao.userCheck2(id);
		
		request.setAttribute("result", result);
	    request.setAttribute("id", id);
		return "member/idFalse2.jsp";
	}

}
