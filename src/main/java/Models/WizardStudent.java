package Models;

public class WizardStudent {
	//instance variables
	private int id, wizardYear, housePoints;
	private String name, houseName;
	public WizardStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WizardStudent(int id, String name, int wizardYear, int housePoints, String houseName) {
		super();
		this.id = id;
		this.wizardYear = wizardYear;
		this.housePoints = housePoints;
		this.name = name;
		this.houseName = houseName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	@Override
	public String toString() {
		return "WizardStudent [id=" + id + ", wizardYear=" + wizardYear + ", housePoints=" + housePoints + ", name="
				+ name + ", houseName=" + houseName + "]";
	}
	
	
}
