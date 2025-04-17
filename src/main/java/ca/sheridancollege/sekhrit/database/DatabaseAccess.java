package ca.sheridancollege.sekhrit.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sekhrit.beans.Product;
import ca.sheridancollege.sekhrit.beans.User;

@Repository
public class DatabaseAccess implements DatabaseAccessImp<Product> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public Product getOne(int id) {
		return getOne(new Product(id));
	}

	@Override
	public Product getOne(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "select * from product where id=:id";
		namedParameters.addValue("id", product.getId());
		try {
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Product>(Product.class)).get(0);
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "select * from product";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Product>(Product.class));
	}

	@Override
	public void postOne(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "insert into product (name, qty, category) values (:name, :qty, :category)";
		namedParameters.addValue("name", product.getName());
		namedParameters.addValue("qty", product.getQty());
		namedParameters.addValue("category", product.getCategory());
		jdbc.update(query, namedParameters);
	}

	@Override
	public void postCollection(List<Product> productList) {
		for (Product product : productList) {
			postOne(product);
		}
	}

	@Override
	public void putOne(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "update product set name=:name, qty=:qty, category=:category where id=:id";
		namedParameters.addValue("name", product.getName());
		namedParameters.addValue("qty", product.getQty());
		namedParameters.addValue("category", product.getCategory());
		namedParameters.addValue("id", product.getId());
		jdbc.update(query, namedParameters);
	}

	@Override
	public void putCollection(List<Product> productList) {
		for (Product product : productList) {
			putOne(product);
		}
	}

	@Override
	public void patchOne(Product product) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder query = new StringBuilder("update product set ");
		List<String> updates = new ArrayList<>();

		if (product.getName() != null) {
			updates.add("name=:name");
			params.addValue("name", product.getName());
		}
		if (product.getQty() != null) {
			updates.add("qty=:qty");
			params.addValue("qty", product.getQty());
		}
		if (product.getCategory() != null) {
			updates.add("category=:category");
			params.addValue("category", product.getCategory());
		}

		if (updates.isEmpty()) return;

		query.append(String.join(", ", updates));
		query.append(" where id=:id");
		params.addValue("id", product.getId());

		jdbc.update(query.toString(), params);
	}

	@Override
	public void patchCollection(List<Product> productList) {
		for (Product product : productList) {
			patchOne(product);
		}
	}

	@Override
	public void deleteOne(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "delete from product where id=:id";
		namedParameters.addValue("id", product.getId());
		jdbc.update(query, namedParameters);
	}

	@Override
	public void deleteCollection() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "delete from product";
		jdbc.update(query, namedParameters);
	}

	public List<String> getRolesById(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT sec_role.roleName FROM user_role, sec_role WHERE user_role.roleId = sec_role.roleId AND userId = :userId";
		namedParameters.addValue("userId", userId);
		return jdbc.queryForList(query, namedParameters, String.class);
	}

	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES (:email, :encryptedPassword, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
	}

	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId) values (:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		jdbc.update(query, namedParameters);
	}

	public User findUserAccount(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where email = :email";
		namedParameters.addValue("email", email);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
}
