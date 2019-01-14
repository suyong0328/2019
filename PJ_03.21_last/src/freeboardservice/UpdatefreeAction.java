package freeboardservice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import freedao.BoardDao;
import freedto.Board;

public class UpdatefreeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		/*
		 * int readcount = Integer.parseInt(request.getParameter("readcount")); int ref
		 * = Integer.parseInt(request.getParameter("ref")); int re_step =
		 * Integer.parseInt(request.getParameter("re_step")); int
		 * re_level=Integer.parseInt(request.getParameter("re_level")); String ip =
		 * request.getParameter("ip"); String reg_date =
		 * request.getParameter("reg_date");
		 */
		Board board = new Board();

		board.setNum(num);
		board.setSubject(subject);
		board.setEmail(email);
		board.setContent(content);
		PrintWriter out = response.getWriter();
		BoardDao dao = BoardDao.getInstance();
		int result = dao.useCheck(num, password);// 리턴값이 1이면 비번이 맞고, -1이면 비번이 틀림
		String view = "";

		if (result == 1) {// 패스워드가 맞으면 수정 처리
			result = dao.updateBoard(board);
			if (result > 0) {
				view = "listfree.do?";
			} else {
				view = "updatefreeForm.do?";
			} // update 처리
		} else if (result == -1) {// 패스워드가 다르면 되돌림.
			view = "updatefreeForm.do?";
		} // 패스워드 조회 처리

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);

		return view;
	}

}
