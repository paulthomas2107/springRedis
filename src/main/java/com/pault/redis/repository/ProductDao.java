package com.pault.redis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.pault.redis.entity.Product;

@Repository
public class ProductDao {
	
	public static final String HASH_KEY = "Product";
	
	@Autowired
	private RedisTemplate<String, Object> template;
	
	public Product save(Product product) {
		
		template.opsForHash().put(HASH_KEY, product.getId(), product);
		
		return product;
		
	}
	
	public List<Object> findAll() {
		
		return template.opsForHash().values(HASH_KEY);
	}

	public Product findProductById(int id) {
		
		return (Product) template.opsForHash().get(HASH_KEY, id);
		
	}
	
	public String deleteProduct(int id) {
		
		template.opsForHash().delete(HASH_KEY, id);
		
		return "Product removed: " + id;
	}
}
