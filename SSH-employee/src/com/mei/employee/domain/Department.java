package com.mei.employee.domain;

import java.util.HashSet;
import java.util.Set;

//���ŵ�ʵ��

public class Department {
	private Integer did;
	private String dname;
	private String ddesc;
	
	//Ա���ļ���
	private Set<Employee> employees=new HashSet<Employee>();
	public void setDid(Integer did) {
		this.did=did;
	}
	public Integer getDid() {
		return did;
	}
	public void setDname(String dname) {
		this.dname=dname;
	}
	public String getDname() {
		return dname;
	}
	public void setDdesc(String ddesc) {
		this.ddesc=ddesc;
	}
	public String getDdesc() {
		return ddesc;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
} 
