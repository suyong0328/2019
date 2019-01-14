package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.sql.DataSource;

import dto.Board;
import oracle.jdbc.OracleConnection.CommitOption;

public class BoardDao {
	private static BoardDao instance;

	private BoardDao() {
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDao();
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

	public int insert(Board board) {
		int result = 0;
		int num = board.getNum();
		int number = 0;
		String sql1 = "select nvl(count(*),0)+1 from pj_board";// 글번호
		String sql2 = "insert into pj_board(num,flag,writer,subject,content,email,readcount,password,ref,re_step,re_level,ip,reg_date) "
				+ "   values(?,2,?,?,?,?,0,?,?,?,?,?,?)";// 글입력
		String sql3 = "update pj_board set re_step = re_step+1 where ref=? and re_step > ? and flag=2";// 답변글update
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
			}
			pstmt.close();
			// 답변글경우
			if (num != 0) {
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, board.getRef());
				pstmt.setInt(2, board.getRe_step());
				pstmt.executeUpdate();
				pstmt.close();
				board.setRe_level(board.getRe_level() + 1);
				board.setRe_step(board.getRe_step() + 1);
				pstmt.close();
			} else if(num==0){
				board.setRef(number);
			}
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getEmail());
			pstmt.setString(6, board.getPassword());
			pstmt.setInt(7, board.getRef());
			pstmt.setInt(8, board.getRe_step());
			pstmt.setInt(9, board.getRe_level());
			pstmt.setString(10, board.getIp());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			pstmt.setString(11, sdf.format(System.currentTimeMillis()));

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

	public int getTotal() {
		int total = 0;
		String sql = "select count(*) from pj_board where flag=2";// 글번호
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

	public List<Board> selectList(int startRow, int endRow) {
		List<Board> list = new ArrayList<>();
		String sql = "select * from (select rownum rn, a.* from "
				+ "  (select * from pj_board where flag = 2 order by ref desc,re_step)a)  where rn between ? and ?";
		String sql2 = "select content from pj_board where flag=2";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();

				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setPassword(rs.getString("password"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getString("reg_date"));

				list.add(board);
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

	public Board updateReadCount(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = getBoard(num);
		String sql1 = "update pj_board set readcount = readcount +1 where num = ? and flag = 2";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			// pstmt.close();

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
		return board;
	}

	public Board getBoard(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.* from (select * from pj_board where flag like '2%' order by num asc) a) where num = ?";
		Board board = new Board();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setPassword(rs.getString("password"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getString("reg_date"));
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
		return board;
	}

	public int useCheck(int num, String password) {
		int result = -1;// 패스워드가 맞지않으면 -1
		String pw = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select password from pj_board where num=? and flag = 2";
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

	public int updateBoard(Board board) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = board.getNum();

		String sql = "update pj_board set subject=?,email=?,  " 
		+ "  reg_date=sysdate,content=? where num =? and flag = 2";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, num);
			 pstmt.executeUpdate();
			 result = 1;

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
	public int deleteBoard(int num) {
		int result=0;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from pj_board where num = ?";
		String sql2 = "delete from pj_item where item_id = ? ";
		try {
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1, num);
			  result = pstmt.executeUpdate();
			  pstmt.close();
			  pstmt = conn.prepareStatement(sql2);
			  pstmt.setInt(1, num);
			  pstmt.executeUpdate();
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
	
	
	public List<Board> searchList(int startRow, int endRow,String search) {
		List<Board> list = new ArrayList<>();
		String sql = "select * from (select rownum rn, a.* from "
				+ "  (select * from pj_board where flag = 2 order by ref desc,re_step)a)  where rn between ? and ? and content like ? or writer like ? or subject like ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, "%"+search+"%");
			pstmt.setString(4, "%"+search+"%");
			pstmt.setString(5, "%"+search+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();

				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setPassword(rs.getString("password"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getString("reg_date"));

				list.add(board);
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
	public Board getMaxRead() {
		Board board = new Board();
		String sql = "select * from (select * from pj_board order by readcount desc) where rownum = 1 and flag = 2";
		try {
			  conn = getConnection();
			  pstmt = conn.prepareStatement(sql);
			  rs = pstmt.executeQuery();
			  if(rs.next()) {
					board.setNum(rs.getInt("num"));
					board.setWriter(rs.getString("writer"));
					board.setSubject(rs.getString("subject"));
					board.setContent(rs.getString("content"));
					board.setEmail(rs.getString("email"));
					board.setReadcount(rs.getInt("readcount"));
					board.setPassword(rs.getString("password"));
					board.setRef(rs.getInt("ref"));
					board.setRe_step(rs.getInt("re_step"));
					board.setRe_level(rs.getInt("re_level"));
					board.setIp(rs.getString("ip"));
					board.setReg_date(rs.getString("reg_date"));
			  }
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {		
				System.out.println(e.getMessage());
			}
		}//finally 끝.
		return board;
	}
	public Board getNewBoard() {
		Board board = new Board();
		String sql = "select * from (select * from pj_board order by num desc) where rownum = 1 and flag = 2";
		try {
			  conn = getConnection();
			  pstmt = conn.prepareStatement(sql);
			  rs = pstmt.executeQuery();
			  if(rs.next()) {
					board.setNum(rs.getInt("num"));
					board.setWriter(rs.getString("writer"));
					board.setSubject(rs.getString("subject"));
					board.setContent(rs.getString("content"));
					board.setEmail(rs.getString("email"));
					board.setReadcount(rs.getInt("readcount"));
					board.setPassword(rs.getString("password"));
					board.setRef(rs.getInt("ref"));
					board.setRe_step(rs.getInt("re_step"));
					board.setRe_level(rs.getInt("re_level"));
					board.setIp(rs.getString("ip"));
					board.setReg_date(rs.getString("reg_date"));
			  }
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {		
				System.out.println(e.getMessage());
			}
		}//finally 끝.
		return board;
	}
	
}
