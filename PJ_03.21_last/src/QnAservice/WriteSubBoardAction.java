package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnAdao.QnaBoardDao;
import QnAdao.MemberDao;
import QnAdao.SubBoardDao;
import QnAdto.SubBoard;
import controller.CommandProcess;

public class WriteSubBoardAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null || id.equals("")) {
			request.setAttribute("error", "댓글 작성은 회원만 가능합니다.");
		}

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String sub_writer = id;
		String sub_content = request.getParameter("sub_content");
		int ref = Integer.parseInt(request.getParameter("num"));
		String subPageNum = request.getParameter("subPageNum");

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("sub_writer", sub_writer);
		request.setAttribute("sub_content", sub_content);
		request.setAttribute("ref", ref);
		request.setAttribute("subPageNum", subPageNum);

		return "QnAboard/writeSubBoard.jsp";
	}

}
