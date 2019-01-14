package QnAservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import QnAdao.QnaBoardDao;
import QnAdao.SubBoardDao;
import QnAdto.Board;
import QnAdto.SubBoard;
import controller.CommandProcess;
import util.Paging;
import util.PageBean;

public class ViewQnaAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String chk = request.getParameter("chk");

		QnaBoardDao dao = QnaBoardDao.getInstance();
		Board board = dao.getQnaBoard(num);

		SubBoardDao subDao = SubBoardDao.getInstance();
		int total = subDao.getSubBoardTotal(num);
		Paging pg = new Paging();
		PageBean pb = pg.getSubPaging(request, total);
		List<SubBoard> subList = subDao.selectSubBoardList(num, pb.getStartRow(), pb.getEndRow());

		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		request.setAttribute("subList", subList);
		request.setAttribute("total", total);
		request.setAttribute("pb", pb);
		request.setAttribute("subPageNum", pb.getPageNum());
		request.setAttribute("faqChk", chk);

		return "QnAboard/viewQna.jsp";
	}

}
