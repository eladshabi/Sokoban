package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Scanner;





public class MyTextLevelLoader implements Level_Loader {

	/**
	 * Data loader from Text file by using location of {@link IntputStream} to {@link Level}.
	 * 
	 */
	
	@Override
	public Level loadLevel(InputStream input) {

		try {
			int j = 0;
			int i;
			

			Level level = new Level();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			ArrayList<String> lines = new ArrayList<String>();
			Scanner myscanner = null;
			String line = reader.readLine();
			while (line != null) {
				if (line != "\n") {
					lines.add(line);

				}
				
				line = reader.readLine();

			}
			reader.close();
	
			
			for (i = 0; i < lines.size(); i++) {
				myscanner = new Scanner(lines.get(i));

				if (i == 0) {

					myscanner.useDelimiter(",");
					level.setName(myscanner.next());
					level.setDifficulty(myscanner.next());

				}

				else {
					level.getConstant_element().add(new ArrayList<Element>());
					level.getMoveble_element().add(new ArrayList<Element>());
					boolean flag = true;
					for (j = 0; j < lines.get(i).length(); j++) {
					
						if (flag) {
							
							switch (lines.get(i).charAt(j)) {

							case '#': {

								Wall w = new Wall();
								w.set_point(i - 1, j);
								level.getConstant_element().get(i - 1).add(w);
								Floor f = new Floor();
								f.set_point(i - 1, j);
								level.getMoveble_element().get(i - 1).add(f);
								break;
							}
							case ' ': {
								Floor f = new Floor();
								f.set_point(i - 1, j);
								level.getConstant_element().get(i - 1).add(f);
								level.getMoveble_element().get(i - 1).add(f);
								break;
							}
							case 'A': {
								Point start=new Point();
								start.setPoint(i-1, j);
								level.setStart_point(start);
								Player tplay=new Player();
								tplay.set_point(i-1, j);
								level.setPlayer(tplay);
								level.getMoveble_element().get(i - 1).add(level.getPlayer());
								Floor f = new Floor();
								f.set_point(i - 1, j);
								level.getConstant_element().get(i - 1).add(f);
								break;
							}
							case 'o': {
								Target t = new Target();
								t.set_point(i - 1, j);
								level.getConstant_element().get(i - 1).add(t);
								level.setNewGol(t);
								Floor f = new Floor();
								f.set_point(i - 1, j);
								level.getMoveble_element().get(i - 1).add(f);
								break;
							}
							case '@': {
								Box b = new Box();
								b.set_point(i - 1, j);
								level.getMoveble_element().get(i - 1).add(b);
								Floor f = new Floor();
								f.set_point(i - 1, j);
								level.getConstant_element().get(i - 1).add(f);
								break;

							}
							default:
								flag=false;
								break;
							

							

							}

						} else {
							break;
						}
					}
				}

			}
			return level;

		} catch (IOException e) {
			
		}
		return null;

	}
}
