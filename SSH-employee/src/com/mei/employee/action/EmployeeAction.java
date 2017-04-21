package com.mei.employee.action;

import java.util.List;

import com.mei.employee.domain.Department;
import com.mei.employee.domain.Employee;
import com.mei.employee.domain.PageBean;
import com.mei.employee.service.DepartmentService;
import com.mei.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//Ա�������Action��
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {
	//ģ��������Ҫʹ�õĶ���
	private Employee employee=new Employee();
	//���ղ���
	public Employee getModel() {
		return employee;
	}
	
	//ע��ҵ������
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//���յ�ǰҳ��
	private Integer currPage=1;


	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	//��½ִ�еķ���
	public String login() {
		System.out.println("login�ķ���ִ���ˡ�������");
		//����ҵ������
		Employee existEmployee=employeeService.login(employee);
		if(existEmployee==null){
			//��¼ʧ��
			this.addActionError("�û������������");
			return INPUT;
		}else {
			//��½�ɹ�
			ActionContext.getContext().getSession().put("existEmployee",existEmployee);
			return SUCCESS;
		}
	}
	
	//��ҳ��ѯԱ����ִ�еķ���
	public String findAll() {
		PageBean<Employee> pageBean=employeeService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	//��ת�����Ա��ҳ��ִ�еķ���
	public String saveUI() {
		//��ѯ���в���
		List<Department> list=departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list",list);
		return "saveUI";
	}
	
	//����Ա��ִ�еķ���
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	//�༭Ա����ִ�еķ���
	public String edit() {
		//����Ա��ID��ѯԱ��
		employee=employeeService.findById(employee.getEid());
		//��ѯ���в���
		List<Department> list=departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list",list);
		return "editSuccess";
	}
	
	//�޸�Ա����ִ�еķ���
	public String update() {
		System.out.println(employee.toString());
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	//ɾ��Ա��ִ�еķ���
	public String delete() {
		employee=employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
}
