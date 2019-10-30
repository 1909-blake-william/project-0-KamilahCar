package Models;

public class WizardStudent {
	//instance variables
	private int id, wizardYear, housePoints;
	private String name, houseName;//, userName
	public WizardStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WizardStudent(int id, String name, int wizardYear, int housePoints, String houseName/*,String userName*/) {
		super();
		this.id = id;
		this.name = name;
		this.wizardYear = wizardYear;
		this.housePoints = housePoints;
		this.houseName = houseName;
		//this.userName = userName;
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
	/*public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}*/
	@Override
	//, userName =" + "userName
	public String toString() {
		return "WizardStudent [id=" + id + ", wizardYear=" + wizardYear + ", housePoints=" + housePoints + ", name="
				+ name + ", houseName=" + houseName + "]";
	}
	
	
}
