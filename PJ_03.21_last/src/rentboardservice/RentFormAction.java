package rentboardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import dao.BoardDao;
import dao.ItemDao;
import dto.Board;
import dto.Item;

public class RentFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDao bdao = BoardDao.getInstance();
		bdao.updateReadCount(num);
		Board board = bdao.getBoard(num);
		
		ItemDao idao = ItemDao.getInstance();
		Item item = idao.getItem(num);
		
		
		
		request.setAttribute("item", item);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		return "board/rentForm.jsp";
	}

}
