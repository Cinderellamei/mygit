package com.mei.ssh.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mei.ssh.domain.Product;

//商品管理的DAO类
public class ProductDao extends HibernateDaoSupport{

	//DAO中保存商品的方法
	public void save(Product product) {
		System.out.println("DAO中的保存商品的save方法执行了。。。。");
		this.getHibernateTemplate().save(product);
	}
}
