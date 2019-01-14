package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnAdao.QnaBoardDao;
import QnAdao.MemberDao;
import QnAdto.Board;
import controller.CommandProcess;

public class MoveFaqFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		QnaBoardDao dao = QnaBoardDao.getInstance();
		System.out.println("num: " + num);
		boolean isFaq = dao.isFaq(num);
		if (isFaq) {
			request.setAttribute("error", "이미 FAQ 게시판에 있는 글입니다.");
		}

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		boolean result = QnaBoardDao.getInstance().isManager(id);
		System.out.println(result);
		if (!result) {
			request.setAttribute("error", "Manager 권한이 필요합니다.");
		}
		/* Board board = new Board(); */
		/* board = dao.getQnaBoard(num); */

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("id", id);

		return "QnAboard/moveFaqForm.jsp";
	}

}
