package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Properties;
import constants.FrameworkConstants;

public class RuntimePropertyFileUtils {

	public static void createRunTimePropFile()  {
		File newFile;
		try {
			newFile = new File(FrameworkConstants.getRuntimePropertyFilePath());
			if (newFile.createNewFile()) {
				System.out.println("RunTime Property File Created");
			} else {
				System.out.println("RunTime Property File already Exists");
			}
		} catch (Exception e) {
			throw new RuntimeException("Unable to Create RunTime Property File. " + e.getMessage());
		}
	}

	public static void appendToPropFile(String key, String value) throws Exception {
		Properties configProperty;
		FileOutputStream fileOut = null;
		try (FileInputStream fileIn = new FileInputStream(new File(FrameworkConstants.getRuntimePropertyFilePath()))) {
			configProperty = new Properties();
			configProperty.load(fileIn);
			configProperty.setProperty(key, value);
			fileOut = new FileOutputStream(new File(FrameworkConstants.getRuntimePropertyFilePath()));
			configProperty.store(fileOut, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Append Data to Property File");
		} finally {
			fileOut.close();
		}
	}

	public static String getRunTimeProperty(String key) throws Exception {
		Properties configProperty = null;
		File file = new File(FrameworkConstants.getRuntimePropertyFilePath());
		try (FileInputStream fis = new FileInputStream(file)) {
			configProperty = new Properties();
			configProperty.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to Get Data to RunTimeProperty File");
		}
		return configProperty.getProperty(key);
	}

	public static void deleteRuntimePropFile()  {
		try {
			File file = new File(FrameworkConstants.getRuntimePropertyFilePath());
			Files.deleteIfExists(file.toPath());
			System.out.println("Runtime Property File Deleted");
		} catch (Exception e) {
			throw new RuntimeException("Unable to Delete RunTime Property File. " + e.getMessage());
		}
	}

}
