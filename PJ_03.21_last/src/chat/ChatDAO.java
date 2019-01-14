package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChatDAO {
	private static ChatDAO instance;

	ChatDAO() {
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	public static ChatDAO getInstance() {
		if (instance == null)
			instance = new ChatDAO();
		return instance;
	}

	public Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OraDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}// getConnection()
	
	public ArrayList<ChatDTO> getChatListByID(String fromID, String toID, String chatID){
		ArrayList<ChatDTO> list = null;
		String sql = "select * from chat where ((fromID = ? AND toID = ?) "
				+ " OR (fromID = ? AND toID = ?) AND chatID > ? order by chatTime";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			if(rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID"));
				chat.setToID(rs.getString("toID"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;")
				.replaceAll("\n", "<br>").replaceAll(">", "&gt;").replaceAll("<", "&lt;"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
				chat.setChatTime(sdf.format(rs.getDate("chatTime")));
				list.add(chat);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<ChatDTO> getChatListByRecent(String fromID, String toID, int number){
		ArrayList<ChatDTO> list = null;
		String sql = "select * from chat where ((fromID = ? AND toID = ?) "
				+ " OR (fromID = ? AND toID = ?) AND chatID > (select max(chatID) - ? from chat) order by chatTime";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			if(rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID"));
				chat.setToID(rs.getString("toID"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;")
				.replaceAll("\n", "<br>").replaceAll(">", "&gt;").replaceAll("<", "&lt;"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
				chat.setChatTime(sdf.format(rs.getDate("chatTime")));
				list.add(chat);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int submit(String fromID, String toID, String chatContent){
		String sql = "insert into chat(chatID,fromID,toID,chatContent) values(chat_seq.nextval,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate(); // 정상리턴 1
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // 오류 -1
	}
}
