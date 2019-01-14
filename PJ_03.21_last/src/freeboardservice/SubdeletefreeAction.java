package freeboardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import freedao.BoardDao;
import freedao.Sub_boardDao;

public class SubdeletefreeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//String num = request.getParameter("num");
		int num = Integer.parseInt(request.getParameter("num"));
		int subnum = Integer.parseInt(request.getParameter("subnum"));
		String pageNum = request.getParameter("pageNum");
		String password = request.getParameter("password");
		
		Sub_boardDao dao = Sub_boardDao.getInstance();
		int result = dao.useCheck(subnum, password);
		String view = "";
		if(result ==-1) {
			
		}else if(result ==1) {
			result = dao.deletesubBoard(subnum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);
			view = "freeboard/subBoardDeletePro.jsp?";
		}
		
		return view;
	}

}
