package rentboardservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import dao.BoardDao;
import dao.ItemDao;
import dto.Board;
import dto.Item;
import util.PageBean;
import util.Paging;

public class ListAction2 implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BoardDao dao = BoardDao.getInstance();
		ItemDao idao = ItemDao.getInstance();
		int itemtotal = idao.getTotal();
		int boardtotal = dao.getTotal();
		Paging pg = new Paging();
		PageBean boardpb = pg.getPaging(request, boardtotal);
		PageBean itempb = pg.getPaging(request, itemtotal);
		List<Board> list = dao.selectList(boardpb.getStartRow(), boardpb.getEndRow());
		List<Item> item = idao.getItemList(itempb.getStartRow(), itempb.getEndRow());
		System.out.println(list.size());
		
				
		request.setAttribute("item", item);
		request.setAttribute("list", list);
		request.setAttribute("boardpb", boardpb);
		request.setAttribute("itempb", itempb);
		request.setAttribute("boardtotal", boardtotal);
		request.setAttribute("itemtotal", itemtotal);
		request.setAttribute("listSize", list.size());
		return "board/list2.jsp";
	}

}
