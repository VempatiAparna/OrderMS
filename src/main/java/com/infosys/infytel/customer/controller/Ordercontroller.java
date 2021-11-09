package com.infosys.infytel.customer.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.infosys.infytel.customer.entity.Orderdb;
import com.infosys.infytel.customer.entity.ProductOrder;
import com.infosys.infytel.customer.service.OrderService;
import com.infosys.infytel.customer.service.ProductOrderedService;
import com.infosys.infytel.order.dto.CartDTO;
import com.infosys.infytel.order.dto.OrderDTO;
import com.infosys.infytel.order.dto.ProductDTO;

@RestController
public class Ordercontroller {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Environment environment;
	@Value("${UserUrl}")
	String UserUrl;
	@Value("${ProdUrl}")
	String ProdUrl;
	@Autowired
	ProductOrderedService cartService;
	@Autowired
	OrderService ordservice;
	Integer amount;
	//adding product to cart
	@PostMapping(value = "/api/buyer/login/{buyerid}/addproduct/cart/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addProduct(@PathVariable Integer buyerid,@RequestBody CartDTO cartDTO) {
		String url = ProdUrl+"Product/{prodid}/{sellerid}";
		RestTemplate restTemplate = new RestTemplate();
		ProductDTO po = restTemplate.getForObject(url, ProductDTO.class, cartDTO.getProdid(),cartDTO.getSellerid());
		String url1 = UserUrl +"addproduct/{sellerid}/{prodid}/{buyerid}/{quantity}";
		restTemplate.put(url1, CartDTO.class,cartDTO.getSellerid(),cartDTO.getProdid(),buyerid,cartDTO.getQuantity());	
	}
	//deleting product from cart
	@DeleteMapping(value= "/api/buyer/login/{buyerid}/removeproduct/cart/{productid}")
	public void removeProduct(@PathVariable Integer buyerid,@PathVariable Integer productid ) {
		String url = UserUrl+"{buyerid}/removeproduct/{prodid}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url,buyerid,productid);
	}
	//for placing order
	@PostMapping(value = "/api/buyer/login/{buyerid}/cart/placeorder", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Orderdb> placeProduct(@PathVariable Integer buyerid,@RequestBody OrderDTO Orderdto) {
		Orderdb orderList = ordservice.placeProduct(buyerid,Orderdto);		
		String successMessage = environment.getProperty("Order_SUCCESS");
		return new ResponseEntity<>(orderList, HttpStatus.OK);

}
	//for storing the ordered items in productsordered table this is consumed in user
	@PutMapping(value = "{buyerid}/{prodid}/{sellerid}/{quantity}/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ProductOrder> getproducts(@PathVariable Integer buyerid,@PathVariable Integer prodid,@PathVariable Integer sellerid,@PathVariable Integer quantity) {
		ProductOrder productorder =cartService.addproduct(buyerid,prodid,sellerid,quantity);
		return new ResponseEntity<>(productorder, HttpStatus.OK);	
	}
	//after placing the order seller will change the status
	@PutMapping(value = "/api/seller/login/placedorder/{orderid}/updatestatus", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Orderdb> updatestatus(@PathVariable Integer orderid,@RequestBody String status) {
		Orderdb orderList = ordservice.updatestatus(orderid,status);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
	//for updating amount after using reward points
	@PutMapping(value = "/api/buyer/login/{buyerid}/order/{orderid}/userewards/updateamount/{amount}")
		public  ResponseEntity<Orderdb> changeamount(@PathVariable Integer buyerid,@PathVariable Integer orderid,@PathVariable Integer amount) {
			Orderdb orderList = ordservice.changeamount(orderid,amount);
			return new ResponseEntity<>(orderList, HttpStatus.OK);
		
}
	//for getting the amount for the specific order
	@GetMapping(value = "/api/buyer/login/{buyerid}/order/{orderid}/amount")
	public  Integer getamount(@PathVariable Integer buyerid, @PathVariable Integer orderid) {
		Integer amount = ordservice.getamount(orderid);
		return amount;
	
}

}
