package com.springweb.api.model;

import java.io.Serializable;

public class EmpVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6036731877271761712L;

	/**
	 * 사번
	 */
	private String empno;

	/**
	 * 성명
	 */
	private String empno_nm;

	/**
	 * 부서코드
	 */
	private String dept_cd;

	/**
	 * 부서명
	 */
	private String dept_nm;

	/**
	 * 급여
	 */
	private String pay_amt;


	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEmpno_nm() {
		return empno_nm;
	}

	public void setEmpno_nm(String empno_nm) {
		this.empno_nm = empno_nm;
	}

	public String getDept_cd() {
		return dept_cd;
	}

	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}

	public String getDept_nm() {
		return dept_nm;
	}

	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}

	public String getPay_amt() {
		return pay_amt;
	}

	public void setPay_amt(String pay_amt) {
		this.pay_amt = pay_amt;
	}




}
