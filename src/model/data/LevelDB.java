package model.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "Levels")
public class LevelDB {
	@Id
	private String LevelName;

	@OneToMany
	@JoinColumn(name="LevelName")
	List<SokoData> sokodata;
	
	public LevelDB() {

	}

	public LevelDB(String levelname) {
		this.LevelName = levelname;
	}

	public String getLevelName() {
		return LevelName;
	}

	public void setLevelName(String levelName) {
		LevelName = levelName;
	}

	@Override
	public String toString() {
		return "LevelDB [LevelName=" + LevelName + "]";
	}

}
