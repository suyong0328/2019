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

public class WriteQnaAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		MultipartRequest mr = null;
		String realFile = request.getServletContext().getRealPath("/refFiles");
		int maxSize = 30 * 1024 * 1024;
		mr = new MultipartRequest(request, realFile, maxSize, "utf-8", new DefaultFileRenamePolicy());
		Enumeration files = mr.getFileNames();
		String fileName = "";
		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();
			fileName = mr.getFilesystemName(name);
		}
		File file = new File(realFile + "/" + fileName);

		QnaBoardDao dao = QnaBoardDao.getInstance();
		String pageNum = mr.getParameter("pageNum");
		Board board = new Board();
		board.setIp(request.getRemoteAddr());

		int num = Integer.parseInt(mr.getParameter("num"));
		int flag = 1;
		String writer = mr.getParameter("writer");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String email = mr.getParameter("email");
		int ref = Integer.parseInt(mr.getParameter("ref"));
		int re_level = Integer.parseInt(mr.getParameter("re_level"));
		int re_step = Integer.parseInt(mr.getParameter("re_step"));

		board.setNum(num);
		board.setFlag(flag);
		board.setWriter(writer);
		board.setSubject(subject);
		board.setContent(content);
		board.setEmail(email);
		board.setFilename(fileName);
		board.setRef(ref);
		board.setRe_step(re_step);
		board.setRe_level(re_level);

		int result = dao.insertQna(board);

		request.setAttribute("result", result);
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);

		return "QnAboard/writeQnaPro.jsp";
	}

}
