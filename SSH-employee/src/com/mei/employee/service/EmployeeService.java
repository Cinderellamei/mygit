package com.mei.employee.service;

import com.mei.employee.domain.Employee;
import com.mei.employee.domain.PageBean;

//员工管理的业务层的接口
public interface EmployeeService {
	Employee login(Employee employee);
	
	PageBean<Employee> findByPage(Integer currPage);
	
	void save(Employee employee);
	
	Employee findById(Integer eid);
	
	void update(Employee employee);
	
	void delete(Employee employee);
}
