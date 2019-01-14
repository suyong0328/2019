package memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;

import controller.CommandProcess;
import dao.MemberDao;
import dto.Member;

public class update implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		
  		Member member = dao.selectMemberInfo(id);
  		request.setAttribute("member", member);
	   
		return "member/updateform.jsp";
	}

}
