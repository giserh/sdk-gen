package pl.sointeractive.isaacloud.data;

public class User {
	private String email;
	private String lastName;
	private String firstName;
	private String birthDate;
	private String id;
	private String gainedAchievements;
	private String level;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGainedAchievements() {
		return gainedAchievements;
	}
	public void setGainedAchievements(String gainedAchievements) {
		this.gainedAchievements = gainedAchievements;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
