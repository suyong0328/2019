package QnAservice;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import QnAdao.QnaBoardDao;
import QnAdto.Board;
import controller.CommandProcess;

public class UpdateQnaAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		MultipartRequest mr = null;
		String realFile = request.getServletContext().getRealPath("/refFiles");
		int maxSize = 30 * 1024 * 1024;
		mr = new MultipartRequest(request, realFile, maxSize, "utf-8", new DefaultFileRenamePolicy());
		System.out.println(mr == null);
		Enumeration files = mr.getFileNames();
		String fileName = "";
		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();
			fileName = mr.getFilesystemName(name);
			System.out.println("fileName: " + fileName);
		}
		File file = new File(realFile + "/" + fileName);

		String pageNum = mr.getParameter("pageNum");
		int num = Integer.parseInt(mr.getParameter("num"));
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String email = mr.getParameter("email");
		/* String password = mr.getParameter("password"); */
		QnaBoardDao dao = QnaBoardDao.getInstance();
		Board board = new Board();
		/* int result = dao.useCheck(num, password); */
		/*
		 * String view = ""; String error = "";
		 */
		/* if (result == 1) { */
		board.setNum(num);
		board.setSubject(subject);
		board.setContent(content);
		board.setEmail(email);
		board.setFilename(fileName);
		int result = dao.updateQnaBoard(board);
		if (result > 0) {
			/* view = "listQna.do"; */
		} else {
			request.setAttribute("error", "업데이트 실패");
			/* error = "업데이트 실패"; */
			/* view = "updateQnaForm.do"; */
		}
		/* } else { */
		/*
		 * error = "비밀번호를 확인해주세요."; view = "updateQnaForm.do";
		 */
		/* } */

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		/* request.setAttribute("error", error); */

		return "listQna.do";
	}

}
