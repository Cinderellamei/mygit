package com.mei.employee.service;

import java.util.List;

import com.mei.employee.domain.Department;
import com.mei.employee.domain.PageBean;

//部门管理业务层的接口
public interface DepartmentService {
	PageBean<Department> findByPage(Integer currPage);
	
	void save(Department department);
	
	Department findById(Integer did);
	
	void update(Department department);
	
	void delete(Department department);
	
	List<Department> findAll();
}
