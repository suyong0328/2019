package memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandProcess;
import dao.MemberDao;
import dto.Member;

public class subaction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		MemberDao dao = MemberDao.getInstance();
		Member member = new Member();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String rrnum1 = request.getParameter("rrnum1");
		String rrnum2 = request.getParameter("rrnum2");
		String zipno = request.getParameter("zipno");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String email = request.getParameter("email");
		
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setRrnum1(rrnum1);
		member.setRrnum2(rrnum2);
		member.setZipno(zipno);
		member.setAddress1(address1);
		member.setAddress2(address2);
		member.setTel1(tel1);
		member.setTel2(tel2);
		member.setTel3(tel3);
		member.setEmail(email);
		int result = dao.insertMember(member);
		  request.setAttribute("result", result);
		  request.setAttribute("member", member);
    
		return "member/subpro.jsp";
	}
}
