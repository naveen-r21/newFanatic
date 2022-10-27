package Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class propertiesLoad {
	
	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public InputStream input;
	
	@BeforeSuite
	public void loadpropertiesFile() throws IOException {

		f = new File("./src/main/java/Properties/data.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
	}

}
