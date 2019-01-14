package QnAservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QnAdao.QnaBoardDao;
import QnAdto.Board;
import controller.CommandProcess;
import util.PageBean;
import util.Paging;

public class SearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		QnaBoardDao dao = QnaBoardDao.getInstance();
		int total = dao.getQnaTotal();
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.println(searchCondition);
		Paging pg = new Paging();
		PageBean pb = pg.getPaging(request, total);
		List<Board> list = dao.selectQnaListWith(pb.getStartRow(), pb.getEndRow(), searchCondition, searchKeyword);

		request.setAttribute("total", total);
		request.setAttribute("list", list);
		request.setAttribute("pb", pb);
		request.setAttribute("searchCondition", searchCondition);
		request.setAttribute("searchKeyword", searchKeyword);

		return "QnAboard/listQna.jsp";
	}

}
