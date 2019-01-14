package memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandProcess;
import dao.MemberDao;
import dto.Member;

public class sub implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "member/sub.jsp";
	}
}
