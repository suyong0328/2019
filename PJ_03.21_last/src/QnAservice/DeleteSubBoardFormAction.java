package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnAdao.QnaBoardDao;
import controller.CommandProcess;

public class DeleteSubBoardFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));

		QnaBoardDao dao = QnaBoardDao.getInstance();
		int result = dao.useCheckSub(num, id);

		int sub_num = Integer.parseInt(request.getParameter("sub_num"));
		String pageNum = request.getParameter("pageNum");
		String subPageNum = request.getParameter("subPageNum");

		request.setAttribute("num", num);
		request.setAttribute("sub_num", sub_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("subPageNum", subPageNum);
		request.setAttribute("result", result);

		return "QnAboard/deleteSubBoardForm.jsp";
	}

}
