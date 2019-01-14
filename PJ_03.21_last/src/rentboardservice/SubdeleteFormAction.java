package rentboardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.INTERNAL;

import controller.CommandProcess;
import dao.BoardDao;
import dao.Sub_boardDao;
import dto.Board;
import dto.Sub_board;

public class SubdeleteFormAction implements CommandProcess {

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
		return "board/subdeleteFrom.jsp";
	}
}
