package kr.bnbd.bean;


public class LoginBean {
	/** 고유번호 */
	private int adminSeq = 0;
	/** 관리자 ID */
	private String adminId;
	/** 관리자 암호 */
	private String password;
	/** 관리자 이름 */
	private String adminName;
	
	public int getAdminSeq() {
		return adminSeq;
	}
	public void setAdminSeq(int adminSeq) {
		this.adminSeq = adminSeq;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}