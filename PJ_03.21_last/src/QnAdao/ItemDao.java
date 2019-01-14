package QnAdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import QnAdto.Board;
import QnAdto.Item;

public class ItemDao {
	private static ItemDao instance;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	private ItemDao() {

	}

	public static ItemDao getInstance() {
		if (instance == null)
			instance = new ItemDao();
		return instance;
	}

	private Connection getConnection() {
		Context init = null;
		DataSource ds = null;
		try {
			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int insert(Item item) {
		int result = 0;
		try {
			conn = getConnection();
			sql = "insert into pj_item values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			int i = 0;
			pstmt.setInt(++i, item.getNum());
			pstmt.setInt(++i, item.getItem_id());
			pstmt.setString(++i, item.getItem_kind());
			pstmt.setString(++i, item.getItem_name());
			pstmt.setInt(++i, item.getItem_price());
			pstmt.setInt(++i, item.getItem_count());
			pstmt.setString(++i, item.getItem_image());
			pstmt.setDate(++i, item.getStartday());
			pstmt.setDate(++i, item.getEndday());
			pstmt.setString(++i, item.getTrans_type());
			pstmt.setDate(++i, item.getReg_date());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
