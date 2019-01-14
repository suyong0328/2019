package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDao;
import controller.CommandProcess;
import dao.BoardDao;
import dto.Board;
import dto.Item;

public class MainForm implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// hot한 글
		BoardDao dao = BoardDao.getInstance();
		Board board = dao.getMaxRead(); // hot 보드
		ItemDao idao = ItemDao.getInstance(); 
		Item item = idao.getItem(board.getNum()); // hot 보드
		request.setAttribute("board", board);
		request.setAttribute("item", item);
		// 최신글
		Board nBoard = dao.getNewBoard();
		Item nItem = idao.getItem(nBoard.getNum());
		request.setAttribute("nboard", nBoard);
		request.setAttribute("nitem", nItem);
		return "mainForm.jsp";
	}
}
