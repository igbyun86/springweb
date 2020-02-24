package com.springweb.comm.vo;

/**
 * 사용자 정보
 * @author big
 *
 */
public class UserInfo {

	// 사번
	String EMPNO;

	// 성명
	String EMPNO_NM;

	// 부서코드
	String DEPT_CD;

	// 접속 IP
	String ACCESS_IP;


	public String getEMPNO() {
		return EMPNO;
	}

	public void setEMPNO(String eMPNO) {
		EMPNO = eMPNO;
	}

	public String getEMPNO_NM() {
		return EMPNO_NM;
	}

	public void setEMPNO_NM(String eMPNO_NM) {
		EMPNO_NM = eMPNO_NM;
	}

	public String getDEPT_CD() {
		return DEPT_CD;
	}

	public void setDEPT_CD(String dEPT_CD) {
		DEPT_CD = dEPT_CD;
	}

	public String getACCESS_IP() {
		return ACCESS_IP;
	}

	public void setACCESS_IP(String aCCESS_IP) {
		ACCESS_IP = aCCESS_IP;
	}


}
