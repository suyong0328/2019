package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QnAdao.QnaBoardDao;
import QnAdao.MemberDao;
import QnAdao.SubBoardDao;
import QnAdto.SubBoard;
import controller.CommandProcess;

public class WriteSubBoardProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String sub_writer = request.getParameter("sub_writer");
		String sub_content = request.getParameter("sub_content");
		int ref = Integer.parseInt(request.getParameter("num"));
		String subPageNum = request.getParameter("subPageNum");
		int result = 0;

		String error = null;

		SubBoardDao sub = SubBoardDao.getInstance();
		SubBoard subBoard = new SubBoard();
		subBoard.setSub_writer(sub_writer);
		subBoard.setSub_content(sub_content);
		subBoard.setRef(ref);

		result = sub.insertSubBoard(subBoard);

		if (result == 1) {

		} else {
			error = "댓글 입력 실패";
		}

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("subPageNum", subPageNum);
		request.setAttribute("result", result);
		request.setAttribute("error", error);

		return "QnAboard/writeSubBoardPro.jsp";
	}

}
