package Models;

//import Util.UserRegistryUtil;

public class WizardStudent {
	//instance variables
	private int id, wizardYear, housePoints;
	private String name, houseName;
	private User currentUser;
	//private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	public WizardStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WizardStudent(int id, String name, int wizardYear, int housePoints, String houseName) {
		super();
		this.id = id;
		this.name = name;
		this.wizardYear = wizardYear;
		this.housePoints = housePoints;
		this.houseName = houseName;
		//this.userName = userName;
	}
	public WizardStudent(int id2, String name2, int year, int housePoints2, String house, User currentUser) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id2;
		this.name = name2;
		this.wizardYear = year;
		this.housePoints = housePoints2;
		this.houseName = house;
		this.currentUser = currentUser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWizardYear() {
		return wizardYear;
	}
	public void setWizardYear(int wizardYear) {
		this.wizardYear = wizardYear;
	}
	public int getHousePoints() {
		return housePoints;
	}
	public void setHousePoints(int housePoints) {
		this.housePoints = housePoints;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public User getUser() {
		return currentUser;
	}
	public void setUserName(User currentUser) {
		this.currentUser = currentUser;
	}
	@Override
	//, userName =" + "userName
	public String toString() {
		return "WizardStudent [id=" + id + ", wizardYear=" + wizardYear + ", housePoints=" + housePoints + ", name="
				+ name + ", houseName=" + houseName + ", currentUser =" + currentUser + "]";
	}
	
	
}
