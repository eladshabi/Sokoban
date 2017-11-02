package model.data;


import java.io.BufferedWriter;
import java.io.IOException;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
/**
 * Data saver to text file by using location of {@link OutputStream} from {@link Level}.
 * 
 */



public class MyTextLevelSaver implements Level_Saver {

	public void saveLevel(OutputStream out, Level level) throws IOException, ClassNotFoundException {

		try {

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			
			
			int i, j;
			writer.write(level.getName());
			writer.write(",");
			writer.write(level.getDifficulty());
			writer.newLine();;
			for (i = 0; i < level.getConstant_element().size(); i++) {
				for (j = 0; j < level.getConstant_element().get(i).size(); j++) {
					switch (level.getConstant_element().get(i).get(j).getType()) {
					case "Floor":
						if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
							writer.write("@");
							break;
						} else if (level.getMoveble_element().get(i).get(j).getType() == "Floor") {
							writer.write(" ");
							break;
						} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
							writer.write("A");
							break;
						}
						break;

					case "Wall":
						writer.write("#");
						break;

					case "Target":
						if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
							writer.write("$");// Box on target
							break;
						} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
							writer.write("%");// player on target.
							break;
						}
						else if(level.getMoveble_element().get(i).get(j).getType()=="Floor"){
							writer.write("o");//only target.
							break;
						}

					case "Start Point":
						if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
							writer.write("@");
							break;
						} else if (level.getMoveble_element().get(i).get(j).getType() == "Floor") {
							writer.write(" ");
							break;
						} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
							writer.write("A");
							break;
						}

					default:
						break;
					}
				}
				writer.newLine();
			}
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
