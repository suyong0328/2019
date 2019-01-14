package freeboardservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import freedao.BoardDao;
import freedto.Board;
import util.PageBean;
import util.Paging;

public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 request.setCharacterEncoding("utf-8");
		
		BoardDao dao = BoardDao.getInstance();
		int total = dao.getTotal();
		Paging pg = new Paging();
		PageBean pb = pg.getPaging(request, total);
		List<Board> list = dao.selectList(pb.getStartRow(),pb.getEndRow());
		
		request.setAttribute("list", list);
		request.setAttribute("pb", pb);
		request.setAttribute("total", total);
		System.out.println(list);
		return "freeboard/listfree.jsp";
	}

}
