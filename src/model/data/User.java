package model.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "Users")
public class User {
	@Id
	private String UserName;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="UserName")
	private List<SokoData> sokodata;
	
	
	public User() {
	}

	public User(String userName) {
		this.UserName = userName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	@Override
	public String toString() {
		return "User [userName=" + UserName + "]";
	}

}
