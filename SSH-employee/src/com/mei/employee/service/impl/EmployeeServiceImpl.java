package com.mei.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mei.employee.dao.EmployeeDao;
import com.mei.employee.domain.Employee;
import com.mei.employee.domain.PageBean;
import com.mei.employee.service.EmployeeService;

//Ա�������ҵ���ʵ����
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	//ҵ���ĵ�½����
	public Employee login(Employee employee) {
		Employee existEmployee=employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}
	
	
	//ҵ����ҳ��ѯԱ�ķ���
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean=new PageBean<Employee>();
		//��װ��ǰ��ҳ��
		pageBean.setCurrPage(currPage);
		//��װÿҳ��ʾ�ļ�¼
		int pageSize=3;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount=employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc=totalCount;
		Double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ������
		int begin=(currPage-1)*pageSize;
		List<Employee> list=employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	//ҵ��㱣��Ժ�ķ���
	public void save(Employee employee) {
		employeeDao.save(employee);
	}
	
	//ҵ������ԭ��IDȥ��ѯԱ���ķ���
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}
	
	//ҵ����޸�Ա���ķ���
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	//ҵ���ɾ��Ա���ķ���
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
}
