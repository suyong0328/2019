package freeboardservice;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandProcess;
import freedao.BoardDao;
import freedao.ItemDao;
import freedto.Board;
import freedto.Item;


public class WritefreeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String realFolder = "";
		String filename = "";
		MultipartRequest imageUp = null;
		String saveFolder = "/imageFile";
		String encType = "utf-8";
		int maxSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);

		imageUp = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		/* 다중 파일 업로드 처리 */
		Enumeration files = imageUp.getFileNames();
		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();
			filename = imageUp.getFilesystemName(name);
		}
		
		// 게시판

		Board board = new Board();
		String pageNum = request.getParameter("pageNum");
		board.setIp(request.getRemoteAddr());
		BoardDao bdao = BoardDao.getInstance();
		String num = imageUp.getParameter("num");
		//String num =request.getParameter("num");

		String writer = imageUp.getParameter("writer");
		String subject = imageUp.getParameter("subject");
		String content = imageUp.getParameter("content");
		String email = imageUp.getParameter("email");
		String password = imageUp.getParameter("password");
		int ref = Integer.parseInt(imageUp.getParameter("ref"));
		int re_step = Integer.parseInt(imageUp.getParameter("re_step"));
		int re_level = Integer.parseInt(imageUp.getParameter("re_level"));

		board.setNum(Integer.parseInt(num));
		board.setWriter(writer);
		board.setSubject(subject);
		board.setContent(content);
		board.setEmail(email);
		board.setPassword(password);
		board.setRef(ref);
		board.setRe_step(re_step);
		board.setRe_level(re_level);
		
		int result = bdao.insert(board);

		
		request.setAttribute("result", result);
		request.setAttribute("board", board);
		// 아이템
		
		
		/*Item item = new Item();

		String item_kind = imageUp.getParameter("item_kind");
		String item_name = imageUp.getParameter("item_name");
		String item_price = imageUp.getParameter("item_price");
		String item_count = imageUp.getParameter("item_count");
		String startday = imageUp.getParameter("startday") + imageUp.getParameter("startTime");
		String endday = imageUp.getParameter("endday")  + imageUp.getParameter("endTime");
		String trans_type = imageUp.getParameter("trans_type");
		

		item.setItem_kind(item_kind);
		item.setItem_name(item_name);
		item.setItem_price(Integer.parseInt(item_price));
		item.setItem_count(Integer.parseInt(item_count));
		item.setItem_image(filename);
		item.setStartday(startday);
		item.setEndday(endday);
		item.setTrans_type(trans_type);
		ItemDao idao = ItemDao.getInstance();
		
		int itemresult = idao.insertItem(item,board);

		request.setAttribute("item", item);
		request.setAttribute("itemresult", itemresult);*/
		request.setAttribute("pageNum", pageNum);

		return "freeboard/writePro.jsp";
	}

}
