package freedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import freedto.Board;
import freedto.Rent;

public class RentDao {
	private static RentDao instance;

	private RentDao() {
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	public static RentDao getInstance() {
		if (instance == null)
			instance = new RentDao();
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

	public int insert(Rent rent) {
		int result = 0;
		int num = rent.getRent_id();
		int number = 0;
		int oldItem_count = 0;
		int count = 0;
		int newItem_count = 0;
		String sql1 = "select count(*)+1 from pj_rent";// rent번호
		String sql2 = "insert into pj_rent(rent_id,rent_seller,rent_buyer,rent_count,item_id,item_kind,"
				+ "item_name,item_price,startday,endday,trans_type,deliveryname,deliverytel,deliveryaddress,reg_date)"
				+ "values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'),to_date(?,'yyyy-MM-dd HH24:MI:SS'),?,?,?,?,sysdate)";// rent
																																	// insert
		String sql3 = "update pj_item set item_count=? where item_id=?"; // Item 수량 수정
		String sql4 = "select item_count from pj_item where item_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
			} else
				number = 1;
			pstmt.close();

			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, number);
			pstmt.setString(2, rent.getRent_seller());
			pstmt.setString(3, rent.getRent_buyer());
			pstmt.setInt(4, rent.getRent_count());
			pstmt.setInt(5, rent.getItem_id());
			pstmt.setString(6, rent.getItem_kind());
			pstmt.setString(7, rent.getItem_name());
			pstmt.setInt(8, rent.getItem_price());
			pstmt.setString(9, rent.getStartday());
			pstmt.setString(10, rent.getEndday());
			pstmt.setString(11, rent.getTrans_type());
			pstmt.setString(12, rent.getDeliveryname());
			pstmt.setString(13, rent.getDeliverytel());
			pstmt.setString(14, rent.getDeliveryaddress());
			count = rent.getRent_count();
			result = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("c");
			pstmt = conn.prepareStatement(sql4);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				oldItem_count = rs.getInt(1);
				newItem_count = oldItem_count - count;
			}
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, newItem_count);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			pstmt.close();
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

		return result;
	}
}
