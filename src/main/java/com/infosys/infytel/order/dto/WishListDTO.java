package com.infosys.infytel.order.dto;

import java.util.Objects;

public class WishListDTO {
Integer prodid;
	
	Integer buyerid;

	public Integer getProdid() {
		return prodid;
	}

	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}

	public Integer getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Integer prod) {
		this.buyerid = prod;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyerid, prodid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishListDTO other = (WishListDTO) obj;
		return Objects.equals(buyerid, other.buyerid) && Objects.equals(prodid, other.prodid);
	}


}
