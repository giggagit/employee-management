package com.gigagit.employee.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class Address {

	@NotEmpty
	private String address;

	@NotEmpty
	@Column(length = 50)
	private String subDistrict;

	@NotEmpty
	@Column(length = 50)
	private String district;

	@NotEmpty
	@Column(length = 50)
	private String province;

	@Column(length = 5)
	@Length(min = 5, max = 5)
	private String zipcode;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
