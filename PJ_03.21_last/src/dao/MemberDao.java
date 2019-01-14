package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Member;

public class MemberDao {
	private static MemberDao instance;

	private MemberDao() {
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	public static MemberDao getInstance() {
		if (instance == null)
			instance = new MemberDao();
		return instance;
	}

	public Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OraDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int userCheck(String id, String password) {
		int result = 2;
		PreparedStatement pstmt = null;
		String sql = "select password from pj_member where id=? and use_flag='0'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpass = rs.getString(1);
				if (password.equals(dbpass))
					result = 1;
				else
					result = 0;
			} else
				result = 2;
			conn.close();
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

	public int userCheck2(String id) {
		int result = 2;
		PreparedStatement pstmt = null;
		String sql = "select count(*) from pj_member where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt(1);

				if (cnt > 0)
					result = 1;
				else
					result = 2;
			}
			conn.close();
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

	public int insertMember(Member member) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into pj_member(id, password, name, rrnum1, rrnum2, zipno,"
				+ "                            address1, address2, tel1,tel2,tel3, email,regdate)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";
		try {
			conn = getConnection();
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

			result = pstmt.executeUpdate();//
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update pj_member " + " set password=?,name=?,rrnum1=?,rrnum2=?,zipno=?,"
				+ " address1=?,address2=?,tel1=?,tel2=?,tel3=?," + " email=?" + " where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			int i = 0;
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
			pstmt.setString(++i, member.getId());

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
			}
		}
		System.out.println("result=" + result);
		return result;
	}

	public Member selectMemberInfo(String id) {
		Member member = new Member();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from pj_member where id =? and use_flag='0'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int i = 0;
				member.setId(rs.getString(++i));
				member.setPassword(rs.getString(++i));
				member.setName(rs.getString(++i));
				member.setRrnum1(rs.getString(++i));
				member.setRrnum2(rs.getString(++i));
				member.setZipno(rs.getString(++i));
				member.setAddress1(rs.getString(++i));
				member.setAddress2(rs.getString(++i));
				member.setTel1(rs.getString(++i));
				member.setTel2(rs.getString(++i));
				member.setTel3(rs.getString(++i));
				member.setEmail(rs.getString(++i));
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
		}
		return member;
	}

	public int deletMember(String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "update pj_member set use_flag = '1' where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
			}
		}

		return result;
	}

	/* ID찾기 */
	public String searchId(String name, String rrnum1, String rrnum2) {
		String id = "";
		System.out.println(name + rrnum1 + rrnum2);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from pj_member where name like ? and rrnum1 like ? and rrnum2 like ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, rrnum1);
			pstmt.setString(3, rrnum2);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		System.out.println("id: " + id);
		return id;
	}

	/* 비밀번호 찾기 */
	public String searchPass(String name, String rrnum1, String rrnum2, String id) {
		String password = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select password from pj_member where name like ? and rrnum1 like ? and rrnum2 like ? and id like ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, rrnum1);
			pstmt.setString(3, rrnum2);
			pstmt.setString(4, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return password;
	}

}
