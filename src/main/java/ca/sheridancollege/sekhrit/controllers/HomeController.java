package ca.sheridancollege.sekhrit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.sekhrit.beans.Product;
import ca.sheridancollege.sekhrit.database.DatabaseAccess;


@Controller	
public class HomeController {
	final String REST_URL = "http://localhost:8080/api/v1/products";
	@Autowired
	private DatabaseAccess da;

	
	@GetMapping("/")
	public String getIndex(Model model,RestTemplate restTemplate) {
		ResponseEntity<Product[]> productListResponse = restTemplate.getForEntity(REST_URL, Product[].class);
		model.addAttribute("productList", productListResponse.getBody());
		Product p = new Product();
		p.setQty(1);
		model.addAttribute("product",p);
		model.addAttribute("categories", Product.categories);

		return "index";
	}
	
	@PostMapping("/insert")
	public String postInsertParkedCar(@ModelAttribute Product product,RestTemplate restTemplate) {
	    ResponseEntity<Void> insertResponse = restTemplate.postForEntity(
	            REST_URL,
	            product,
	            Void.class
	        );
		return "redirect:/";
	}
	
	@GetMapping("update/{id}")
	public String prePut(@PathVariable int id, RestTemplate restTemplate, Model model) {
		ResponseEntity<Product> productListResponse = restTemplate.getForEntity(REST_URL+"/"+id, Product.class);
		model.addAttribute("product",productListResponse.getBody());
		model.addAttribute("categories", Product.categories);

		return "update";
	}
	@PostMapping("update/{id}")
	public String put(@PathVariable int id, @ModelAttribute Product product, RestTemplate restTemplate) {
		restTemplate.put(REST_URL+"/"+id, product);
		return "redirect:/"; 
	}
	
	@GetMapping("delete/{id}")
	public String delete(RestTemplate restTemplate, @PathVariable int id) {
		restTemplate.delete(REST_URL+"/"+id);

		return "redirect:/"; 
 
	}
	
	@GetMapping("permission-denied")
	public String de() {
		return "error/permission-denied";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
		da.addUser(username, password);
		Long userId = da.findUserAccount(username).getUserId();
		da.addRole(userId, Long.valueOf(1));
		return "redirect:/";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
