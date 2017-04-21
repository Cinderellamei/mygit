package com.mei.ssh.action;

import com.mei.ssh.domain.Product;
import com.mei.ssh.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//��Ʒ�����Action��
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	//ģ��������Ҫʹ�õ���
	private Product product=new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	//Struts��Spring���Ϲ����а������Զ�ע��ҵ������
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService=productService;
	}
	
	//������Ʒִ�еķ�����save
	public String save() {
		System.out.println("Action�е�save����ִ���ˡ�������");
		productService.save(product);
		return NONE;
	}
}
