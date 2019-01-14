package chat;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandProcess;

public class ChatSubmit implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");
		if(fromID == null || fromID.equals("")
				|| toID == null || toID.equals("") 
				|| chatContent == null || chatContent.equals("")) {
			response.getWriter().write("0");
		} else {
			fromID = URLDecoder.decode(fromID, "UTF-8");
			response.getWriter().write(new ChatDAO().submit(fromID, toID, chatContent) + "");
		}
			
		return "chat.do";
	}
}
