package model;

public class Address implements Cloneable {

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
	
	public String toString() {
		return getClass().getName()+"[region="+region+",province="+province+
				"city="+city+",street="+street+",houseNumber="+houseNumber+",cap="+cap+"]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		
		if(getClass() != otherObject.getClass())
			return false;
		
		Address other = (Address)otherObject;
		
		return region.equals(other.region) && province.equals(other.province) &&
				city.equals(other.city) && street.equals(other.street) &&
				Integer.compare(houseNumber, other.houseNumber) == 0 &&
				Integer.compare(cap, other.cap) == 0;
	}

	public Address clone() {
		try {
			return (Address)super.clone();
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String region, province, city, street;
	private int houseNumber, cap;
}
