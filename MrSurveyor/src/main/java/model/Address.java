package model;

public class Address {

	public Address() {
		
	}
	
	public Address(String region, String province, String city, String street, int houseNumber, int cap) {
		
		this.region = region;
		this.province = province;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.cap = cap;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	private String region, province, city, street;
	private int houseNumber, cap;
}
