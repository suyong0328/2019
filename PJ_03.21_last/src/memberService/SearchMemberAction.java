package memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import dao.MemberDao;

public class SearchMemberAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String rrnum1 = request.getParameter("rrnum1");
		String rrnum2 = request.getParameter("rrnum2");
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		if (id == null || id.equals("")) { // 아이디 찾기
			id = dao.searchId(name, rrnum1, rrnum2);
			if (id == null || id.equals("")) {
				request.setAttribute("error", "일치하는 아이디가 없습니다.");
			} else {
				request.setAttribute("id", id);
			}
		} else { // 비밀번호 찾기
			String password = dao.searchPass(name, rrnum1, rrnum2, id);
			if (password == null || password.equals("")) {
				request.setAttribute("error", "일치하는 정보가 없습니다.");
			} else {
				request.setAttribute("password", password);
			}
		}

		return "member/searchMember.jsp";
	}

}
