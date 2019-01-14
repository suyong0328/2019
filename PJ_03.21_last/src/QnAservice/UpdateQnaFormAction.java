package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import QnAdao.QnaBoardDao;
import QnAdto.Board;
import controller.CommandProcess;

public class UpdateQnaFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		QnaBoardDao dao = QnaBoardDao.getInstance();
		int result = dao.useCheck(num, id);
		if (result == -1) {
			request.setAttribute("error", "글 작성자만 수정 가능합니다.");
		}

		Board board = new Board();
		board = dao.getQnaBoard(num);

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);

		return "QnAboard/updateQnaForm.jsp";
	}

}
