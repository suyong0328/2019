package QnAdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import QnAdto.Member;

public class MemberDao {
	private static MemberDao instance;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		if (instance == null)
			instance = new MemberDao();
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		DataSource ds = null;
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/* 회원 추가 */
	public int insertMember(Member member) {
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into pj_member values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			int i = 0;
			pstmt.setString(++i, member.getId());
			pstmt.setString(++i, member.getPassword());
			pstmt.setString(++i, member.getName());
			pstmt.setString(++i, member.getRrnum1());
			pstmt.setString(++i, member.getRrnum2());
			pstmt.setString(++i, member.getZipno());
			pstmt.setString(++i, member.getAddress1());
			pstmt.setString(++i, member.getAddress2());
			pstmt.setString(++i, member.getTel1());
			pstmt.setString(++i, member.getTel2());
			pstmt.setString(++i, member.getTel3());
			pstmt.setString(++i, member.getEmail());
			pstmt.setDate(++i, member.getRegdate());
			pstmt.setString(++i, String.valueOf(member.getUse_flag()));
			pstmt.setString(++i, String.valueOf(member.getManager_flag()));

			result = pstmt.executeUpdate();
			conn.commit();
			conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.setAutoCommit(true);
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	/*
	 * 회원 확인 public int memberCheck(String id, String password) { int result = -1;
	 * try { conn = getConnection(); sql =
	 * "select password from pj_member where id = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, id); rs =
	 * pstmt.executeQuery(); if (rs.next()) { if (rs.getString(1).equals(password))
	 * // 확인 result = 1; } } catch (Exception e) {
	 * System.out.println(e.getMessage()); } finally { try { if (rs != null)
	 * rs.close(); if (pstmt != null) pstmt.close(); if (conn != null) conn.close();
	 * } catch (Exception e) { System.out.println(e.getMessage()); } } return
	 * result; }
	 * 
	 * 매니저 확인 public boolean isManager(String id) { boolean isTrue = false; try {
	 * conn = getConnection(); sql =
	 * "select MANAGER_FLAG from pj_member where id = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, id); rs =
	 * pstmt.executeQuery(); if (rs.next()) { if
	 * (rs.getString("MANAGER_FLAG").equals("T")) // 확인 isTrue = true; } } catch
	 * (Exception e) { System.out.println(e.getMessage()); } finally { try { if (rs
	 * != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn != null)
	 * conn.close(); } catch (Exception e) { System.out.println(e.getMessage()); } }
	 * return isTrue; }
	 */
}
