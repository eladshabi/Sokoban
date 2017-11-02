package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class sData {
	private SimpleStringProperty user_name;
	private SimpleStringProperty level_name;
	private SimpleIntegerProperty steps;
	private SimpleIntegerProperty timeEx;
	private SimpleIntegerProperty score;
	
	public sData(String user_name, String level_name, int steps, int timeEx, int score) {
		super();
		this.user_name = new SimpleStringProperty(user_name);
		this.level_name = new SimpleStringProperty(level_name);
		this.steps = new SimpleIntegerProperty(steps);
		this.timeEx = new SimpleIntegerProperty(timeEx);
		this.score = new SimpleIntegerProperty(score);
	}

	public sData() {}

	public String getUser_name() {
		return user_name.get();
	}

	public void setUser_name(String user_name) {
		this.user_name.set(user_name);
	}

	public String getLevel_name() {
		return level_name.get();
	}

	public void setLevel_name(String level_name) {
		this.level_name.set(level_name);
	}

	public int getSteps() {
		return steps.get();
	}

	public void setSteps(int steps) {
		this.steps.set(steps);
	}

	public int getTimeEx() {
		return timeEx.get();
	}

	public void setTimeEx(int timeEx) {
		this.timeEx.set(timeEx);
	}

	public int getScore() {
		return score.get();
	}

	public void setScore(int score) {
		this.score.set(score);
	}
}
