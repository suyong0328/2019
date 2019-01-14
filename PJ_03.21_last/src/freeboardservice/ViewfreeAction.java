package freeboardservice;



import java.io.PrintWriter;
import java.util.List;

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
import util.PageBean;
import util.Paging;

public class ViewfreeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null || id.equals("")) {
			PrintWriter writer = response.getWriter();
			writer.print("<html><script>");
			writer.print("alert('글은 회원만 볼 수 있습니다.');");
			writer.print("location.href='start.do';");
			writer.print("</script></html>");
			writer.flush();
			writer.close();
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDao bdao = BoardDao.getInstance();
		bdao.updateReadCount(num);
		Board board = bdao.getBoard(num);
		
		ItemDao idao = ItemDao.getInstance();
		Item item = idao.getItem(num);
		
		//댓글받아오기
		Sub_boardDao subdao = Sub_boardDao.getInstance();
		List<Sub_board> sub_board = subdao.getSubboard(num);
		//댓글 순서
		int total =subdao.getTotal(num);
		Paging pg = new Paging();
		
		PageBean pb = pg.getPaging(request, total);
		List<Sub_board> list = subdao.selectList(pb.getStartRow(),pb.getEndRow(),num);
		
		request.setAttribute("pb", pb);
		request.setAttribute("total", total);
		request.setAttribute("list", list);
		request.setAttribute("sub_board", sub_board);
		request.setAttribute("item", item);
		request.setAttribute("num", board.getNum());
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		request.setAttribute("rn", request.getParameter("rn"));
		return "freeboard/view.jsp?num=${num}&pageNum=${pageNum}";
	}

}
