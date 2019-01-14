package QnAdto;

import java.util.Date;

public class SubBoard {
	private int sub_num;
	private String sub_writer;
	private String sub_content;
	private int ref;
	private String reg_date;

	public int getSub_num() {
		return sub_num;
	}

	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}

	public String getSub_writer() {
		return sub_writer;
	}

	public void setSub_writer(String sub_writer) {
		this.sub_writer = sub_writer;
	}

	public String getSub_content() {
		return sub_content;
	}

	public void setSub_content(String sub_content) {
		this.sub_content = sub_content;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

}
