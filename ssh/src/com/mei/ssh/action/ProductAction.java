package com.mei.ssh.action;

import com.mei.ssh.domain.Product;
import com.mei.ssh.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//商品管理的Action类
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	//模型驱动需要使用的类
	private Product product=new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	//Struts和Spring整合过程中按名称自动注入业务层的类
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService=productService;
	}
	
	//保存商品执行的方法：save
	public String save() {
		System.out.println("Action中的save方法执行了。。。。");
		productService.save(product);
		return NONE;
	}
}
