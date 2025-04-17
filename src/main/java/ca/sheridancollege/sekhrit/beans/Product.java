package ca.sheridancollege.sekhrit.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private int id;
	private String name;
	private Integer qty;
	private String category;
	public static final String[] categories = {"tech", "cars", "clothes", "food"};
	
	public Product(int id) {
		this.id = id;
	}
}
