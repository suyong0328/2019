package freeboardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import freedao.BoardDao;

public class DeletefreeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		String password = request.getParameter("password");
		
		BoardDao dao = BoardDao.getInstance();
		int result = dao.useCheck(Integer.parseInt(num), password);
		String view = "";
		if(result ==-1) {
			view = "listfree.do";
		}else if(result ==1) {
			result = dao.deleteBoard(Integer.parseInt(num));
			request.setAttribute("pageNum", pageNum);
			view = "listfree.do";
		}
		
		return view;
	}

}
