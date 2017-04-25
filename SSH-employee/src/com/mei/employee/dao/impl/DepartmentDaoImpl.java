package com.mei.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mei.employee.dao.DepartmentDao;
import com.mei.employee.domain.Department;

//���Ź����DAO��ʵ����
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {
	//ͳ�Ƹ���
	public int findCount() {
		String hql="select count(*) from Department";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//��ҳ��ѯ����
	public List<Department> findByPage(int begin,int pageSize) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Department.class);
		List<Department> list=this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
		return list;
	}
	
	//���沿�ŵķ���
	public void save(Department department) {
		this.getHibernateTemplate().save(department);
	}
	
	//DAO�и�������ID��ѯ���ŵķ���
	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}
	
	//DAO���޸Ĳ��ŵķ���
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
	}
	
	//DAO��ɾ�����ŵķ���
	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
	}
	
	//DAO�в�ѯ���в��ŵķ���
	public List<Department> findAll() {
		return this.getHibernateTemplate().find("from Department");
	}
}
