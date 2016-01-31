package gol;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {
	String result = "";
	InputStream inputStream;

	private int x = 0;
	private int y = 0;
	private int ratio = 0;
	private int speed = 0;

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getRatio() {
		return this.ratio;
	}

	public int getSpeed() {
		return this.speed;
	}

	public String getPropValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			String Strx = prop.getProperty("x");
			String Stry = prop.getProperty("y");
			String StrRatio = prop.getProperty("ratio");
			String StrSpeed = prop.getProperty("speed");
			System.out.println("x : " + Strx + " y: " + Stry + " ratio: " + StrRatio + " speed: " + StrSpeed);

			// get the property value and print it out
			x = Integer.parseInt(Strx);
			y = Integer.parseInt(Stry);
			ratio = Integer.parseInt(StrRatio);
			speed = Integer.parseInt(StrSpeed);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}