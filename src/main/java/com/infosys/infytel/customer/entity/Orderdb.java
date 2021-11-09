
	package com.infosys.infytel.customer.entity;

	import java.time.LocalDate;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	@Entity
	public class Orderdb {
		    @Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
			Integer orderid;
			Integer buyerid;
			Integer amount;
			LocalDate date;
			@Column(length = 100)
			String address;
			String status;
			public Integer getOrderid() {
				return orderid;
			}
			public void setOrderid(Integer orderid) {
				this.orderid = orderid;
			}
			public Integer getBuyerid() {
				return buyerid;
			}
			public void setBuyerid(Integer buyerid) {
				this.buyerid = buyerid;
			}
			public Integer getAmount() {
				return amount;
			}
			public void setAmount(Integer amount) {
				this.amount = amount;
			}
			public LocalDate getDate() {
				return date;
			}
			public void setDate(LocalDate date) {
				this.date = date;
			}
			
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			
			@Override
			public String toString() {
				return "Orderdto[orderid="+orderid+" ,buyerid="+buyerid+" , amount="+amount+" ,date="+
						date+",address="+address+",status="+status+"]";
			}	

		}






