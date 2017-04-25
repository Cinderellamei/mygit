package com.mei.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mei.employee.dao.DepartmentDao;
import com.mei.employee.domain.Department;

//部门管理的DAO的实现类
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {
	//统计个数
	public int findCount() {
		String hql="select count(*) from Department";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询部门
	public List<Department> findByPage(int begin,int pageSize) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Department.class);
		List<Department> list=this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
		return list;
	}
	
	//保存部门的方法
	public void save(Department department) {
		this.getHibernateTemplate().save(department);
	}
	
	//DAO中根部部门ID查询部门的方法
	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}
	
	//DAO中修改部门的方法
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
	}
	
	//DAO中删除部门的方法
	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
	}
	
	//DAO中查询所有部门的方法
	public List<Department> findAll() {
		return this.getHibernateTemplate().find("from Department");
	}
}
