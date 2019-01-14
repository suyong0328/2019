package freeboardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import freedto.Board;
import freedto.Item;

public class RefwritefreeFormAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws Throwable {
		Board board = new Board();
		Item item = new Item();
		int num = 0, ref = 0, re_level = 0, re_step = 0;
		String pageNum = request.getParameter("pageNum");
		if (request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
			ref = Integer.parseInt(request.getParameter("ref"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
			re_step = Integer.parseInt(request.getParameter("re_step"));

		} else {
			num = 0;
			ref = 0;
			re_level = 0;
			re_step = 0;
		}
		board.setNum(num);
		board.setRef(ref);
		board.setRe_step(re_step);
		board.setRe_level(re_level);
		
		item.setNum(num);
		request.setAttribute("item", item);
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		
	         //이동할 경로 
		return "freeboard/refwritefreeForm.jsp";
	}

}
