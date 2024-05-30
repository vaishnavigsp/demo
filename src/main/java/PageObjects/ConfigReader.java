package PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

private static Properties properties;
	
	static {
	
			try {
				FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties");
			
			properties = new Properties();
			properties.load(inputStream);
		}
		catch (IOException e) {
			throw new RuntimeException("unable to load config.properties file:" + e.getMessage());
		}
	}
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
