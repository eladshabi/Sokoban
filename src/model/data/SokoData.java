package model.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity(name="SokoData")
public class SokoData{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int GameSerial;
	
	@Column(name="UserName")
	private String user_name;
	
	@Column(name="LevelName")
	private String level_name;
	
	@Column(name="Steps")
	private int steps;
	
	@Column(name="TimeEx")
	private int timeEx;
	
	@Column(name="Score")
	private int score;
	
	public SokoData(String user_name, String level_name, int steps, int timeEx, int score) {
		super();
		this.user_name = user_name;
		this.level_name = level_name;
		this.steps = steps;
		this.timeEx = timeEx;
		this.score = score;
	}

	public SokoData() {}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getTimeEx() {
		return timeEx;
	}

	public void setTimeEx(int timeEx) {
		this.timeEx = timeEx;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getGameSerial() {
		return GameSerial;
	}

	@Override
	public String toString() {
		return "SokoData [GameSerial=" + GameSerial + ", user_name=" + user_name + ", level_name=" + level_name
				+ ", steps=" + steps + ", timeEx=" + timeEx + ", score=" + score + "]";
	}

	
	
}