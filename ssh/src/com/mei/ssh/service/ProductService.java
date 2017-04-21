package com.mei.ssh.service;

import org.springframework.transaction.annotation.Transactional;

import com.mei.ssh.dao.ProductDao;
import com.mei.ssh.domain.Product;

//商品管理的业务层类
@Transactional
public class ProductService {
	//业务层注入 DAO的类
	 private ProductDao productDao;
	 public void setProductDao(ProductDao productDao) {
		 this.productDao=productDao;
	 }
	 
	 //业务层保存商品的方法
	 public void save(Product product) {
		 System.out.println("Service中的save方法执行了。。。。");
		 productDao.save(product);
	 }
}
