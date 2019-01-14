package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnAdao.QnaBoardDao;
import QnAdto.Board;
import controller.CommandProcess;

public class DeleteQnaFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		QnaBoardDao dao = QnaBoardDao.getInstance();
		int result = dao.useCheck(num, id);
		if (result == -1) {
			request.setAttribute("error", "글 작성자만 삭제 가능합니다.");
		}
		/*
		 * Board board = new Board(); BoardDao dao = BoardDao.getInstance(); board =
		 * dao.getQnaBoard(num);
		 */

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);

		return "QnAboard/deleteQnaForm.jsp";
	}

}
