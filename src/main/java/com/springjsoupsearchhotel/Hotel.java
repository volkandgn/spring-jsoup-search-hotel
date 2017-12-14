package com.springjsoupsearchhotel;

public class Hotel {

	private Long id;
	private String hotelName;
	private String hotelLocation;
	private String hotelScore;
	private String hotelImageUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelLocation() {
		return hotelLocation;
	}

	public void setHotelLocation(String hotelLocation) {
		this.hotelLocation = hotelLocation;
	}

	public String getHotelScore() {
		return hotelScore;
	}

	public void setHotelScore(String hotelScore) {
		this.hotelScore = hotelScore;
	}

	public String getHotelImageUrl() {
		return hotelImageUrl;
	}

	public void setHotelImageUrl(String hotelImageUrl) {
		this.hotelImageUrl = hotelImageUrl;
	}
}
