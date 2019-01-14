package QnAdto;

import java.sql.Date;

public class Member {
	private String id;
	private String password;
	private String name;
	private String rrnum1;
	private String rrnum2;
	private String zipno;
	private String address1;
	private String address2;
	private String tel1;
	private String tel2;
	private String tel3;
	private String email;
	private Date regdate;
	private char use_flag;
	private char manager_flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRrnum1() {
		return rrnum1;
	}

	public void setRrnum1(String rrnum1) {
		this.rrnum1 = rrnum1;
	}

	public String getRrnum2() {
		return rrnum2;
	}

	public void setRrnum2(String rrnum2) {
		this.rrnum2 = rrnum2;
	}

	public String getZipno() {
		return zipno;
	}

	public void setZipno(String zipno) {
		this.zipno = zipno;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public char getUse_flag() {
		return use_flag;
	}

	public void setUse_flag(char use_flag) {
		this.use_flag = use_flag;
	}

	public char getManager_flag() {
		return manager_flag;
	}

	public void setManager_flag(char manager_flag) {
		this.manager_flag = manager_flag;
	}

}
