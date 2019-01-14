package freedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import freedto.Board;
import freedto.Item;

public class ItemDao {
	private static ItemDao instance;

	private ItemDao() {
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	public static ItemDao getInstance() {
		if (instance == null)
			instance = new ItemDao();
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

	public int insertItem(Item item, Board board) {
		int result = 0;
		int item_id = 0;
		int number = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql3 = "select nvl(count(*),0)+1 from pj_item";
		String sql2 = "select count(*) from pj_board where flag=2";
		String sql1 = "insert into pj_item values"
				+ " (?,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'),"
				+ " to_date(?,'yyyy-MM-dd HH24:MI:SS'),?,to_char(sysdate,'yyyy-MM-dd HH24:MI:SS'))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				item_id = rs.getInt(1);
			}
			pstmt.close();
			pstmt = conn.prepareStatement(sql3);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				number = rs.getInt(1);
			}
			pstmt = conn.prepareStatement(sql1);
			int i = 0;
			pstmt.setInt(++i, number);
			pstmt.setInt(++i, item_id);
			pstmt.setString(++i, item.getItem_kind());
			pstmt.setString(++i, item.getItem_name());
			pstmt.setInt(++i, item.getItem_price());
			pstmt.setInt(++i, item.getItem_count());
			pstmt.setString(++i, item.getItem_image());
			pstmt.setString(++i, item.getStartday());
			pstmt.setString(++i, item.getEndday());
			pstmt.setString(++i, item.getTrans_type());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {

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

	public Item getItem(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from pj_item where item_id=?";
		Item item = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				item = new Item();
				item.setNum(rs.getInt("num"));
				item.setItem_id(rs.getInt("item_id"));
				item.setItem_kind(rs.getString("item_kind"));
				item.setItem_name(rs.getString("item_name"));
				item.setItem_price(rs.getInt("item_price"));
				item.setItem_count(rs.getInt("item_count"));
				item.setItem_image(rs.getString("item_image"));
				item.setStartday(rs.getString("startday"));
				item.setEndday(rs.getString("endday"));
				item.setTrans_type(rs.getString("trans_type"));
				item.setReg_date(rs.getString("reg_date"));
			}
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
		return item;
	}
	public List<Item> getItemList(int startRow, int endRow) {
		List<Item> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.* from (select * from pj_item order by num )a)  where rn between ? and ?";
		Item item = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				item = new Item();
				item.setNum(rs.getInt("num"));
				item.setItem_id(rs.getInt("item_id"));
				item.setItem_kind(rs.getString("item_kind"));
				item.setItem_name(rs.getString("item_name"));
				item.setItem_price(rs.getInt("item_price"));
				item.setItem_count(rs.getInt("item_count"));
				item.setItem_image(rs.getString("item_image"));
				item.setStartday(rs.getString("startday"));
				item.setEndday(rs.getString("endday"));
				item.setTrans_type(rs.getString("trans_type"));
				item.setReg_date(rs.getString("reg_date"));
				
				list.add(item);
			}
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
		return list;
	}
	public int getTotal() {
		int total = 0;
		String sql = "select count(*) from pj_item";// 글번호
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return total;
	}
	
}
