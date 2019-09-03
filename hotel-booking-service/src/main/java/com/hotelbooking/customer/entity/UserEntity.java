package com.hotelbooking.customer.entity;

//@Entity
//@Table(name = "customerdetails")
public class UserEntity {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@NotBlank
	private String userName;
	//@NotBlank
	private String userId;
	
	private int bonusPointsBalance;
	

	public UserEntity(Long id, String userName, String userId, int bonusPointsBalance) {
		super();
		this.id = id;
		this.userName = userName;
		this.userId = userId;
		this.bonusPointsBalance = bonusPointsBalance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBonusPointsBalance() {
		return bonusPointsBalance;
	}

	public void setBonusPointsBalance(int bonusPointsBalance) {
		this.bonusPointsBalance = bonusPointsBalance;
	}

	
	

}
