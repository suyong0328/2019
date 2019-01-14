package QnAservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QnAdao.QnaBoardDao;
import controller.CommandProcess;

public class ViewProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id: " + id);
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String chk = request.getParameter("chk");

		if (id == null || id.equals("")) {
			request.setAttribute("error", "글은 회원만 볼 수 있습니다.");
		} else {
			QnaBoardDao dao = QnaBoardDao.getInstance();
			dao.updateReadCount(num);
		}

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("chk", chk);

		return "QnAboard/viewQnaPro.jsp";
	}

}
