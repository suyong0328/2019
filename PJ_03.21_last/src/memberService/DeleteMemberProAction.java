package memberService;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandProcess;
import dao.MemberDao;

public class DeleteMemberProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		MemberDao dao = MemberDao.getInstance();
		int result = dao.userCheck(id, password);
		System.out.println("id: " + id);
		System.out.println("pass: " + password);
		if (result == 1) {
			result = dao.deletMember(id);
			HttpSession session = request.getSession();
			session.invalidate();
		}

		request.setAttribute("result", result);
		return "member/deletePro.jsp";
	}

}
