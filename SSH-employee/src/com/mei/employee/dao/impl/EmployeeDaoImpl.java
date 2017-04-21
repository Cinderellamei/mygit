package com.mei.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mei.employee.dao.EmployeeDao;
import com.mei.employee.domain.Employee;

//Ա�������DAO��ʵ����
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
	
	//DAO�и����û����������ѯ�û��ķ���
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql="from Employee where username=? and password=?";
		List<Employee> list=this.getHibernateTemplate().find(hql,employee.getUsername(),employee.getPassword());
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	//DAO��ͳ��Ա�������ķ���
	public int findCount() {
		String hql="select count(*) from Employee";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//DAO�з�ҳ��ѯԱ���ķ���
	public List<Employee> findByPage(int begin,int pageSize) {
		DetachedCriteria  criteria=DetachedCriteria.forClass(Employee.class);
		List<Employee> list=this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
		return list;
	}
	
	//DAO�б���Ա���ķ���
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
	}
	
	//DAO�и���Ա��ID��ѯԱ���ķ���
	public Employee findById(Integer eid) {
		return this.getHibernateTemplate().get(Employee.class, eid);
	}
	
	
	//DAO���޸�Ա���ķ���
	public void update(Employee employee) {
		System.out.println(employee.toString());
		this.getHibernateTemplate().update(employee);//����������
	}
	
	//DAO��ɾ��Ա���ķ���
	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);
	}
}
