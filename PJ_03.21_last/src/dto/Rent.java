package dto;

import java.util.Date;

public class Rent {
	private int rent_id;
	private String rent_seller;
	private String rent_buyer;
	private int rent_count;
	private int item_id;
	private String item_kind;
	private String item_name;
	private int item_price;
	private String startday;
	private String endday;
	private String trans_type;
	private String deliveryname;
	private String deliverytel;
	private String deliveryaddress;
	private String sanction;
	private Date reg_date;
	
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getRent_id() {
		return rent_id;
	}
	public void setRent_id(int rent_id) {
		this.rent_id = rent_id;
	}
	public String getRent_seller() {
		return rent_seller;
	}
	public void setRent_seller(String rent_seller) {
		this.rent_seller = rent_seller;
	}
	public String getRent_buyer() {
		return rent_buyer;
	}
	public void setRent_buyer(String rent_buyer) {
		this.rent_buyer = rent_buyer;
	}
	public int getRent_count() {
		return rent_count;
	}
	public void setRent_count(int rent_count) {
		this.rent_count = rent_count;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_kind() {
		return item_kind;
	}
	public void setItem_kind(String item_kind) {
		this.item_kind = item_kind;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public String getDeliveryname() {
		return deliveryname;
	}
	public void setDeliveryname(String deliveryname) {
		this.deliveryname = deliveryname;
	}
	public String getDeliverytel() {
		return deliverytel;
	}
	public void setDeliverytel(String deliverytel) {
		this.deliverytel = deliverytel;
	}
	public String getDeliveryaddress() {
		return deliveryaddress;
	}
	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}
	public String getSanction() {
		return sanction;
	}
	public void setSanction(String sanction) {
		this.sanction = sanction;
	}

	
	
	
}
