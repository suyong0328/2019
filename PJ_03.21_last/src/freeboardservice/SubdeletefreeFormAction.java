package freeboardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.INTERNAL;

import controller.CommandProcess;
import freedao.BoardDao;
import freedao.Sub_boardDao;
import freedto.Board;
import freedto.Sub_board;

public class SubdeletefreeFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
	    String pageNum = request.getParameter("pageNum");
	    int subnum = Integer.parseInt(request.getParameter("subnum"));
	    Sub_board sub_board = new Sub_board();
	    Sub_boardDao dao = Sub_boardDao.getInstance();
	    sub_board = dao.getsubBoard(subnum);
	    
	    request.setAttribute("sub_board", sub_board);
	    request.setAttribute("num", num);
	    request.setAttribute("subnum", subnum);
	    request.setAttribute("pageNum", pageNum); 
		return "freeboard/subdeleteFrom.jsp";
	}
}
