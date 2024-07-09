package co.yedam.vo;

import java.sql.Date;

public class StudentVO {
	private String stdNo; // std_no
	private String stdName;
	private String stdPhone;
	private String address;
	private String birthdate; // 1999-01-01
	private Date creationDate;

	public String getStdNo() {
		return stdNo;
	}

	public void setStdNo(String stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getStdPhone() {
		return stdPhone;
	}

	public void setStdPhone(String stdPhone) {
		this.stdPhone = stdPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "StudentVO [stdNo=" + stdNo + ", stdName=" + stdName + ", stdPhone=" + stdPhone + ", address=" + address
				+ ", birthdate=" + birthdate + ", creationDate=" + creationDate + "]";
	}

	public String briefShow() {

		return stdNo + " " + stdName + " " + stdPhone;
	}
}