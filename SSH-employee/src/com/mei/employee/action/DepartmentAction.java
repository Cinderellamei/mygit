package com.mei.employee.action;

import com.mei.employee.domain.Department;
import com.mei.employee.domain.PageBean;
import com.mei.employee.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


//���Ź����Action��
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	
	
	//ģ������Ҫʹ�õĶ���
	private Department department=new Department();
	public Department getModel() {
		return department;
	}
	
	//���յ�ǰ��ҳ��
	private Integer currPage= 1;
	
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	
	//ע�벿�Ź����Service
	private DepartmentService departmentService;
	

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}


	//�ṩһ����ѯ�ķ���
	public String findAll() {
		PageBean<Department> pageBean=departmentService.findByPage(currPage);
		//��pageBean���뵽ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//��ת����Ӳ��ŵ�ҳ��ķ���
	public String saveUI() {
		return "saveUI";
	}
	
	//��Ӳ���ִ�еķ���
	public String save() {
		departmentService.save(department);
		return "saveSuccess";
	}
	//�༭����ִ�еķ���
	public String edit() {
		department=departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	//�޸Ĳ��ŵ�ִ�еķ���
	public String update() {
		departmentService.update(department);
		return "updateSuccess";
	}
	
	//ɾ�����ŵ�ִ�з���
	public String delete() {
		department=departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";
	}
}
