package rentboardservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;
import dao.BoardDao;
import dao.ItemDao;
import dao.RentDao;
import dto.Board;
import dto.Item;
import dto.Rent;

public class RentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		Board board = new Board();
		BoardDao bdao = BoardDao.getInstance();
		board = bdao.getBoard(num);
		
		Item item = new Item();
		ItemDao idao = ItemDao.getInstance();
		item = idao.getItem(num);
		
		Rent rent = new Rent();
		RentDao rdao = RentDao.getInstance();
		
		int rent_id = num;
		String rent_seller = board.getWriter();
		String rent_buyer = request.getParameter("rent_buyer");
		int rent_count = Integer.parseInt(request.getParameter("rent_count"));
		int item_id = item.getItem_id();
		String item_kind = item.getItem_kind();
		String item_name = item.getItem_name();
		int item_price = item.getItem_price();
		String startday = item.getStartday();
		String endday = item.getEndday();
		String trans_type = item.getTrans_type();
		String deliveryname = request.getParameter("deliveryname");
		String deliverytel = request.getParameter("deliverytel");
		String deliveryaddress = request.getParameter("deliveryaddress");
		
		rent.setRent_id(rent_id);
		rent.setRent_seller(rent_seller);
		rent.setRent_buyer(rent_buyer);
		rent.setRent_count(rent_count);
		rent.setItem_id(item_id);
		rent.setItem_kind(item_kind);
		rent.setItem_name(item_name);
		rent.setItem_price(item_price);
		rent.setStartday(startday);
		rent.setEndday(endday);
		rent.setTrans_type(trans_type);
		rent.setDeliveryname(deliveryname);
		rent.setDeliverytel(deliverytel);
		rent.setDeliveryaddress(deliveryaddress);
		
		
		int result = rdao.insert(rent);
		

		// 이동할 객체
		request.setAttribute("result", result);
		request.setAttribute("pageNum", pageNum);
		return "board/rentPro.jsp";
	}

}
