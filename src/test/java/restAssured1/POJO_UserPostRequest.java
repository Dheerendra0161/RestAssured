package restAssured1;

import java.util.List;

public class POJO_UserPostRequest {
	private String name;
	private int age;
	private String email;
	private List<String> hobbies;

	// Getters and setters
	// You can generate them using your IDE or manually
	// Here's an example of manually written getters and setters:

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
}
