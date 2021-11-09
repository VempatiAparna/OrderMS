package com.infosys.infytel.customer.service;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.infosys.infytel.customer.entity.Orderdb;
import com.infosys.infytel.customer.repository.OrderRepository;
import com.infosys.infytel.order.dto.OrderDTO;
@Service
@Transactional
public class OrderService {
	@Autowired
	OrderRepository ordrepo;


	Integer amount = 0 ;
	Integer rewardpoints;
	Integer pos;
	Integer stock;
	@Value("${UserUrl}")
	String UserUrl;
	

	public Orderdb placeProduct(Integer buyerid, OrderDTO orderdto)  {
			Orderdb ord=new Orderdb();
			ord.setBuyerid(buyerid);
			ord.setAddress(orderdto.getAddress());
			String url1 =UserUrl+"api/buyer/login/{buyerid}/cart/amount";
			Integer pot=new RestTemplate().getForObject(url1, Integer.class,buyerid);
			rewardpoints=(pot/100);
			String url3= UserUrl+"api/buyer/login/{buyerId}/rewardpoints/{rewardpoints}";
			RestTemplate restTemplate1 = new RestTemplate();
			restTemplate1.put(url3,Integer.class,buyerid,rewardpoints);
			String url2= UserUrl+"api/buyer/login/{buyerId}/previlage/{rewardpoints}";
			RestTemplate restTemplate2 = new RestTemplate();
			restTemplate2.put(url2,Integer.class,buyerid,rewardpoints);
			ord.setAmount(pot);
     		ord.setDate(java.time.LocalDate.now());
			ord.setStatus("OrderPlaced");
			ordrepo.save(ord);
			return ord;
		}

	public Orderdb updatestatus(Integer orderid, String status) {
		Optional<Orderdb> optimal = ordrepo.findById(orderid);
		if(optimal.isPresent()) {
			Orderdb o = optimal.get();
			o.setStatus(status);
			ordrepo.save(o);
		return o;
	}
		return null;
	}

	public Orderdb changeamount(Integer orderid, Integer amount) {
		Optional<Orderdb> optimal = ordrepo.findById(orderid);
		if(optimal.isPresent()) {
			Orderdb o = optimal.get();
			o.setAmount(amount);;
			ordrepo.save(o);
		return o;
	}
		
		return null;
	}

	public Integer getamount(Integer orderid) {
		Optional<Orderdb> optimal = ordrepo.findById(orderid);
		if(optimal.isPresent()) {
			Orderdb o = optimal.get();
			amount=o.getAmount();		
		return amount;
	}
		return null;
}
}
	
