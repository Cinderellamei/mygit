package com.mei.ssh.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mei.ssh.domain.Product;

//��Ʒ�����DAO��
public class ProductDao extends HibernateDaoSupport{

	//DAO�б�����Ʒ�ķ���
	public void save(Product product) {
		System.out.println("DAO�еı�����Ʒ��save����ִ���ˡ�������");
		this.getHibernateTemplate().save(product);
	}
}
