package freeboardservice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandProcess;
import freedao.BoardDao;
import freedao.ItemDao;
import freedao.Sub_boardDao;
import freedto.Board;
import freedto.Item;
import freedto.Sub_board;

public class Sub_writefreeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null || id.equals("")) {
			writer.print("<html><script>");
			writer.print("alert('댓글은 회원만 쓸 수 있습니다.');");
			writer.print("location.href='start.do';");
			writer.print("</script></html>");
			writer.flush();
			writer.close();
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String sub_writer = id;
		String sub_content = request.getParameter("sub_content");
		if(sub_content == null || sub_content.equals("")) {
			writer.print("<html><script>");
			writer.print("alert('댓글 내용을 입력해주세요.');");
			writer.print("history.back();");
			writer.print("</script></html>");
			writer.flush();
			writer.close();
		} 
		String sub_password = (String)session.getAttribute("password");

		BoardDao bdao = BoardDao.getInstance();
		bdao.updateReadCount(num);
		Board board = bdao.getBoard(num);

		ItemDao idao = ItemDao.getInstance();
		Item item = idao.getItem(num);

		Sub_boardDao subdao = Sub_boardDao.getInstance();
		Sub_board sub_board = new Sub_board();

		sub_board.setSub_num(num);
		sub_board.setSub_writer(sub_writer);
		sub_board.setSub_content(sub_content);
		sub_board.setSub_password(sub_password);
		sub_board.setRef(board.getNum());
		int result = subdao.insertSubboard(sub_board);

		request.setAttribute("result", result);
		request.setAttribute("item", item);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		
		return "freeboard/subBoardPro.jsp";
		
	}

}
