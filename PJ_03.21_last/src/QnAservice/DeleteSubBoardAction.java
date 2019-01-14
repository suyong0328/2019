package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnAdao.SubBoardDao;
import controller.CommandProcess;

public class DeleteSubBoardAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		int sub_num = Integer.parseInt(request.getParameter("sub_num"));
		String sub_password = request.getParameter("sub_password");
		String pageNum = request.getParameter("pageNum");
		String subPageNum = request.getParameter("subPageNum");

		SubBoardDao dao = SubBoardDao.getInstance();

		int result = dao.useCheck(sub_num, id, sub_password);
		String view = "";
		String error = null;
		if (result == -1) {
			request.setAttribute("error", "비밀번호를 확인해주세요.");
		} else if (result == 1) {
			result = dao.deleteSubBoard(sub_num);
			if (result > 0) {

			} else {
				request.setAttribute("error", "삭제 실패");
			}
		}

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("sub_num", sub_num);
		request.setAttribute("sub_password", sub_password);
		request.setAttribute("subPageNum", subPageNum);

		return "viewQna.do";
	}

}
