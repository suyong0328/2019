package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QnAdao.QnaBoardDao;
import controller.CommandProcess;

public class DeleteQnaAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String password = request.getParameter("password");

		QnaBoardDao dao = QnaBoardDao.getInstance();
		int result = dao.useCheck(num, password); // 정상 1, 비정상 -1
		String view = "";
		String error = "";
		if (result == -1) {
			error = "비밀번호를 확인해주세요.";
			view = "deleteQnaForm.do";
		} else if (result == 1) {
			result = dao.deleteQnaBoard(num);
			if (result > 0) {
				view = "listQna.do";
			} else {
				error = "삭제 실패";
				view = "deleteQnaForm.do";
			}
		}

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("error", error);

		return view;
	}

}
