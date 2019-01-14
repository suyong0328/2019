package rentboardservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import dao.BoardDao;
import dto.Board;
import util.PageBean;
import util.Paging;

public class SearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String search = (String)request.getParameter("search");
		BoardDao dao = BoardDao.getInstance();
		int total = dao.getTotal();
		Paging pg = new Paging();
		PageBean pb = pg.getPaging(request, total);
		List<Board> list = dao.searchList(pb.getStartRow(), pb.getEndRow(),search);
		request.setAttribute("list", list);
		request.setAttribute("pb", pb);
		request.setAttribute("total", total);
		return "board/list.jsp";
	}

}
