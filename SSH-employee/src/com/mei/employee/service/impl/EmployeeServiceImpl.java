package com.mei.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mei.employee.dao.EmployeeDao;
import com.mei.employee.domain.Employee;
import com.mei.employee.domain.PageBean;
import com.mei.employee.service.EmployeeService;

//员工管理的业务层实现类
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	//业务层的登陆方法
	public Employee login(Employee employee) {
		Employee existEmployee=employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}
	
	
	//业务层分页查询员的方法
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean=new PageBean<Employee>();
		//封装当前的页数
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录
		int pageSize=3;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount=employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc=totalCount;
		Double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据
		int begin=(currPage-1)*pageSize;
		List<Employee> list=employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	//业务层保存院的方法
	public void save(Employee employee) {
		employeeDao.save(employee);
	}
	
	//业务层根据原的ID去查询员工的方法
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}
	
	//业务层修改员工的方法
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	//业务层删除员工的方法
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
}
