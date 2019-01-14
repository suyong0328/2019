package memberService;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandProcess;

public class DeleteMemberAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		if (id == null || id.equals("")) {
			PrintWriter out = response.getWriter();
			out.println("<html><script>");
			out.println("alert('로그인이 필요한 서비스입니다.');");
			out.println("history.back();");
			out.println("</script></html>");
			out.flush();
			out.close();
		}
		request.setAttribute("id", id);
		return "member/deleteForm.jsp";
	}

}
