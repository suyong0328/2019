package QnAdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import QnAdto.Board;

public class QnaBoardDao {
	private static QnaBoardDao instance;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	private QnaBoardDao() {

	}

	public static QnaBoardDao getInstance() {
		if (instance == null)
			instance = new QnaBoardDao();
		return instance;
	}

	private Connection getConnection() {
		Context init = null;
		DataSource ds = null;
		try {
			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OraDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/* Q&A 글 추가 */
	public int insertQna(Board board) {
		int result = 0;
		int number = 0; // 글 번호
		int num = board.getNum(); // 글 존재 확인
		try {
			conn = getConnection();
			sql = "select max(num) from pj_QnAboard";
			// 게시판에서 가장 큰 글번호
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
				number += 1; // 새로운 글번호
				pstmt.close();
				if (num != 0) { // 답변글일 경우
					String id = board.getWriter();
					boolean isTrue = isManager(id);
					/*
					 * String password = board.getPassword(); result = managerCheck(id, password);
					 */
					if (isTrue) {
						conn = getConnection();
						sql = "update pj_QnAboard set re_step = re_step + 1 where ref = ? and re_step > ?";
						// 답변 + 1
						pstmt = conn.prepareStatement(sql);
						int i = 0;
						pstmt.setInt(++i, board.getRef());
						pstmt.setInt(++i, board.getRe_step());
						pstmt.executeUpdate();
						pstmt.close();
						board.setRe_level(board.getRe_level() + 1);
						board.setRe_step(board.getRe_step() + 1);
					} else {
						return result = 0;
					}
				} else if (num == 0) { // 답변글이 아닐 경우
					board.setRef(number);
				}
				sql = "insert into pj_QnAboard(num, flag, writer, subject, content, email, filename, readcount, ref, re_step, re_level, ip, reg_date) "
						+ "values(?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?, to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";
				pstmt = conn.prepareStatement(sql);
				int i = 0;
				pstmt.setInt(++i, number);
				pstmt.setInt(++i, board.getFlag());
				pstmt.setString(++i, board.getWriter());
				pstmt.setString(++i, board.getSubject());
				pstmt.setString(++i, board.getContent());
				pstmt.setString(++i, board.getEmail());
				pstmt.setString(++i, board.getFilename());
				pstmt.setInt(++i, board.getRef());
				pstmt.setInt(++i, board.getRe_step());
				pstmt.setInt(++i, board.getRe_level());
				pstmt.setString(++i, board.getIp());
				result = pstmt.executeUpdate();
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
		return result;
	}

	/* Q&A 게시판 글 수 */
	public int getQnaTotal() {
		int total = 0;
		try {
			conn = getConnection();
			sql = "select count(*) from pj_QnAboard where flag like '1%'";
			// 카테고리가 1로 시작하고 지워지지 않은 글 선택
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	/* FAQ 게시판 글 수 */
	public int getFaqTotal() {
		int total = 0;
		try {
			conn = getConnection();
			sql = "select count(*) from pj_QnAboard where (flag like '11') or (flag like '1%' and readcount > 20)";
			// FAQ로 옮겨진 게시글이거나 Q&A 글 중 조회수가 20 이상인 글 선택
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	/* Q&A 게시판 */
	public List<Board> selectQnaList(int startRow, int endRow) {
		List<Board> list = new ArrayList<>();
		try {
			conn = getConnection();
			sql = "select * from (select rownum rn, a.* from (select * from pj_QnAboard where flag like '1%' order by ref desc, re_step) a) where rn between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				int i = 1;
				b.setNum(rs.getInt(++i));
				b.setFlag(rs.getInt(++i));
				b.setWriter(rs.getString(++i));
				b.setSubject(rs.getString(++i));
				b.setContent(rs.getString(++i));
				b.setEmail(rs.getString(++i));
				b.setFilename(rs.getString(++i));
				b.setReadcount(rs.getInt(++i));
				b.setRef(rs.getInt(++i));
				b.setRe_step(rs.getInt(++i));
				b.setRe_level(rs.getInt(++i));
				b.setIp(rs.getString(++i));
				b.setReg_date(rs.getString(++i));

				list.add(b);
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

	/* Q&A 게시판 검색 */
	public List<Board> selectQnaListWith(int startRow, int endRow, String searchCondition, String searchKeyword) {
		List<Board> list = new ArrayList<>();
		try {
			conn = getConnection();
			searchKeyword = "%" + searchKeyword + "%";
			if (searchCondition.equals("all")) {
				sql = "select * from (select rownum rn, a.* from (select * from pj_QnAboard where flag like '1%' and (subject like ? or content like ? or writer like ?) order by ref desc, re_step) a) where rn between ? and ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchKeyword);
				pstmt.setString(2, searchKeyword);
				pstmt.setString(3, searchKeyword);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
			} else if (searchCondition.equals("title")) {
				sql = "select * from (select rownum rn, a.* from (select * from pj_QnAboard where flag like '1%' and subject like ? order by ref desc, re_step) a) where rn between ? and ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchKeyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else if (searchCondition.equals("content")) {
				sql = "select * from (select rownum rn, a.* from (select * from pj_QnAboard where flag like '1%' and content like ? order by ref desc, re_step) a) where rn between ? and ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchKeyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else if (searchCondition.equals("writer")) {
				sql = "select * from (select rownum rn, a.* from (select * from pj_QnAboard where flag like '1%' and writer like ? order by ref desc, re_step) a) where rn between ? and ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchKeyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				int i = 1;
				b.setNum(rs.getInt(++i));
				b.setFlag(rs.getInt(++i));
				b.setWriter(rs.getString(++i));
				b.setSubject(rs.getString(++i));
				b.setContent(rs.getString(++i));
				b.setEmail(rs.getString(++i));
				b.setFilename(rs.getString(++i));
				b.setReadcount(rs.getInt(++i));
				b.setRef(rs.getInt(++i));
				b.setRe_step(rs.getInt(++i));
				b.setRe_level(rs.getInt(++i));
				b.setIp(rs.getString(++i));
				b.setReg_date(rs.getString(++i));

				list.add(b);
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

	/* FAQ 게시판 */
	public List<Board> selectFaqList(int startRow, int endRow) {
		List<Board> list = new ArrayList<>();
		try {
			conn = getConnection();
			sql = "select * from (select rownum rn, a.* from (select * from pj_QnAboard where (flag like '11') or (flag like '1%' and readcount > 20) order by readcount desc, reg_date desc) a) where rn between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				int i = 1;
				b.setNum(rs.getInt(++i));
				b.setFlag(rs.getInt(++i));
				b.setWriter(rs.getString(++i));
				b.setSubject(rs.getString(++i));
				b.setContent(rs.getString(++i));
				b.setEmail(rs.getString(++i));
				b.setFilename(rs.getString(++i));
				b.setReadcount(rs.getInt(++i));
				b.setRef(rs.getInt(++i));
				b.setRe_step(rs.getInt(++i));
				b.setRe_level(rs.getInt(++i));
				b.setIp(rs.getString(++i));
				b.setReg_date(rs.getString(++i));

				list.add(b);
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

	/* 조회수 */
	public void updateReadCount(int num) {
		try {
			conn = getConnection();
			sql = "update pj_QnAboard set readcount = readcount + 1 where num = ?";
			// 조회수 + 1
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
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
	}

	/* Q&A 게시글 얻기 */
	public Board getQnaBoard(int num) {
		Board board = new Board();
		try {
			conn = getConnection();
			sql = "select * from (select rownum rn, a.* from (select * from pj_QnAboard where flag like '1%' order by num asc) a) where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int i = 1;
				board.setNum(rs.getInt(++i));
				board.setFlag(rs.getInt(++i));
				board.setWriter(rs.getString(++i));
				board.setSubject(rs.getString(++i));
				board.setContent(rs.getString(++i));
				board.setEmail(rs.getString(++i));
				board.setFilename(rs.getString(++i));
				board.setReadcount(rs.getInt(++i));
				board.setRef(rs.getInt(++i));
				board.setRe_step(rs.getInt(++i));
				board.setRe_level(rs.getInt(++i));
				board.setIp(rs.getString(++i));
				board.setReg_date(rs.getString(++i));
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

	/* Q&A 게시글 수정 */
	public int updateQnaBoard(Board board) {
		int result = 0;
		try {
			conn = getConnection();
			sql = "update pj_QnAboard set subject = ?, content = ?, email = ?, filename = ? where num = ?";
			// 제목 이메일 내용 수정
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getEmail());
			pstmt.setString(4, board.getFilename());
			pstmt.setInt(5, board.getNum());

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

	/* FAQ 게시판으로 글 옮기기 */
	public int moveFaq(int num) {
		int result = 0;
		try {
			conn = getConnection();
			sql = "update pj_QnAboard set flag = 11 where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
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

	/* 글쓴이 확인 */
	public int useCheck(int num, String id) {
		int result = -1;
		try {
			conn = getConnection();
			sql = "select writer from pj_QnAboard where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(id)) // 확인
					result = 1;
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
		return result;
	}

	/* 댓글 글쓴이 확인 */
	public int useCheckSub(int num, String id) {
		int result = -1;
		try {
			conn = getConnection();
			sql = "select sub_writer from pj_sub_QnAboard where ref = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(id);
				if (rs.getString(1).equals(id)) // 확인
					result = 1;
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
		return result;
	}

	/* 회원 확인 */
	public int memberCheck(String id, String password) {
		int result = -1;
		try {
			conn = getConnection();
			sql = "select password from pj_member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(password)) // 확인
					result = 1;
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
		return result;
	}

	/* 관리자 확인 */
	public boolean isManager(String id) {
		boolean isTrue = false;
		System.out.println(id);
		try {
			conn = getConnection();
			sql = "select count(*) from pj_member where id = ? and manager_flag = '1'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt(1));
				if (rs.getInt(1) > 0) {// 확인
					isTrue = true;
				}
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
		return isTrue;
	}

	/* 관리자 확인 */
	public int managerCheck(String id, String password) {
		int result = -1;
		try {
			conn = getConnection();
			sql = "select password from pj_member where id = ? and manager_flag = '1'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(password))
					result = 1;
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
		return result;
	}

	/* Q&A 글 삭제 */
	public int deleteQnaBoard(int num) {
		int result = 0;
		try {
			conn = getConnection();
			sql = "delete pj_QnAboard where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
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

	/* 댓글 존재 확인 */
	public boolean isReply(int num) {
		boolean isTrue = false;
		try {
			conn = getConnection();
			sql = "select count(*) from pj_QnAboard where ref = ? and num != ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					isTrue = true; // 댓글 존재
				}
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
		return isTrue;
	}

	/* 이메일 얻기 */
	public String getEmail(String id) {
		String email = "";
		try {
			conn = getConnection();
			sql = "select email from pj_member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				email = rs.getString(1);
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
		return email;
	}

	/* FAQ 체크 */
	public boolean isFaq(int num) {
		boolean isFaq = false;
		try {
			conn = getConnection();
			sql = "select count(*) from pj_qnaboard where (flag = '11' or readcount > 20) and ref = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					isFaq = true;
				}
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
		return isFaq;
	}
}
