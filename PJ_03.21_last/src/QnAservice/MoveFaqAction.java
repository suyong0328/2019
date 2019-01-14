package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QnAdao.QnaBoardDao;
import controller.CommandProcess;

public class MoveFaqAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		QnaBoardDao dao = QnaBoardDao.getInstance();
		int result = dao.managerCheck(id, password); // 정상 1, 비정상 -1
		/*
		 * String view = ""; String error = "";
		 */
		if (result == -1) {
			/* request.setAttribute("pageNum", pageNum); */
			request.setAttribute("error", "관리자ID와 비밀번호를 확인해주세요.");
			/*
			 * error = "관리자ID와 비밀번호를 확인해주세요."; view = "moveFaqForm.do";
			 */
		} else if (result == 1) {
			result = dao.moveFaq(num);
			if (result > 0) {

			} else {
				/* request.setAttribute("pageNum", pageNum); */
				request.setAttribute("error", "이동 실패");
				/*
				 * error = "이동 실패"; view = "moveFaqForm.do";
				 */
			}
		}

		request.setAttribute("num", num);

		return "QnAboard/moveFaqPro.jsp";
	}

}
