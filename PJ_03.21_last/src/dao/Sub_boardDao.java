package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Board;
import dto.Sub_board;

public class Sub_boardDao {
	private static Sub_boardDao instance;

	private Sub_boardDao() {
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	public static Sub_boardDao getInstance() {
		if (instance == null)
			instance = new Sub_boardDao();
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
	
	
	public int insertSubboard(Sub_board sub_board) {
		int result = 0;
		int ref = sub_board.getSub_num();
		int number = 0;
		String sql1 = "select nvl(max(sub_num),0)+1 from pj_sub_board";// 글번호
		String sql2 = "insert into pj_sub_board(sub_num,sub_writer,sub_content,sub_password,ref,reg_date) "
				+ "   values(?,?,?,?,?,to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";// 글입력
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
			}
			pstmt.close();
			// 답변글경우
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, number);
			pstmt.setString(2, sub_board.getSub_writer());
			pstmt.setString(3, sub_board.getSub_content());
			pstmt.setString(4, sub_board.getSub_password());
			pstmt.setInt(5, ref);
			
			result = pstmt.executeUpdate();

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
	
	public List<Sub_board> getSubboard(int num) {
		
		List<Sub_board> list = new ArrayList<>();
		String sql = "select * from pj_sub_board where ref=? order by sub_num";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Sub_board sub_board = new Sub_board();
				sub_board.setSub_num(rs.getInt("sub_num"));
				sub_board.setSub_writer(rs.getString("sub_writer"));
				sub_board.setSub_password(rs.getString("sub_password"));
				sub_board.setSub_content(rs.getString("sub_content"));
				sub_board.setRef(rs.getInt("ref"));
				sub_board.setReg_date(rs.getString("reg_date"));
				list.add(sub_board);
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
	
	public Sub_board getsubBoard(int subnum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from pj_sub_board where sub_num=?";
		Sub_board sub_board = new Sub_board();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sub_board.setSub_num(subnum);
				sub_board.setSub_writer(rs.getString("sub_writer"));
				sub_board.setSub_password(rs.getString("sub_password"));
				sub_board.setSub_content(rs.getString("sub_content"));
				sub_board.setRef(rs.getInt("ref"));
				sub_board.setReg_date(rs.getString("reg_date"));
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
		return sub_board;
	}
	
	public int useCheck(int num, String password) {
		int result = -1;// 패스워드가 맞지않으면 -1
		String pw = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sub_password from pj_sub_board where sub_num=?";
		try {
			conn = getConnection();// DB연결맺기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pw = rs.getString(1);

				if (pw.equals(password))
					result = 1;// 패스워드가 맞으면 1
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
			}
		} // finally끝.
		return result;
	}// useCheck()메소드 끝.
	
	public int deletesubBoard(int num) {
		int result=0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from pj_sub_board where sub_num =?";
		try {
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1, num);
			  result = pstmt.executeUpdate();
			  
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {				
			}
		}//finally 끝.
		return result;
	}//deleteBoard()메소드 끝.
	public int getTotal(int num) {
		int total = 0;
		String sql = "select count(*) from pj_sub_board where ref=?";// 글번호
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
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
	
	public List<Sub_board> selectList(int startRow, int endRow,int num) {
		List<Sub_board> list = new ArrayList<>();
		String sql = "select * from (select rownum rn, a.* from "
				+ "  (select * from pj_sub_board where ref=? order by sub_num desc)a)  where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Sub_board sub_board = new Sub_board();

				sub_board.setSub_num(rs.getInt("sub_num"));
				sub_board.setSub_writer(rs.getString("sub_writer"));
				sub_board.setSub_content(rs.getString("sub_content"));
				sub_board.setSub_password(rs.getString("sub_password"));
				sub_board.setRef(rs.getInt("ref"));
				sub_board.setReg_date(rs.getString("reg_date"));

				list.add(sub_board);
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
		return list;
	}
	
	
}
