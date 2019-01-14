package QnAservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QnAdao.QnaBoardDao;
import QnAdao.SubBoardDao;
import QnAdto.Board;
import QnAdto.SubBoard;
import controller.CommandProcess;
import util.Paging;
import util.PageBean;

public class ListQnaAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		QnaBoardDao dao = QnaBoardDao.getInstance();
		int total = dao.getQnaTotal();
		Paging pg = new Paging();
		PageBean pb = pg.getPaging(request, total);
		List<Board> list = dao.selectQnaList(pb.getStartRow(), pb.getEndRow());

		request.setAttribute("total", total);
		request.setAttribute("list", list);
		request.setAttribute("pb", pb);

		return "QnAboard/listQna.jsp";
	}

}
