package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.FrameworkConstants;
import customExceptions.ResourceFileException;

public final class JSONUtils {

	public static Map<String, String> readJsonData(String folderName, String fileName) {
		String filePath = FrameworkConstants.getJsonPath() + folderName + File.separator + fileName + ".json";
		Map<String, String> testDetails = new HashMap<>();
		try {
			testDetails = new ObjectMapper().readValue(new File(filePath),
					new TypeReference<HashMap<String, String>>() {
					});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ResourceFileException("Unable to Interact with " + fileName + " as the JSON File is not Found in "
					+ folderName + " Folder.");

		} catch (IOException e) {
			e.printStackTrace();
			throw new ResourceFileException("Error Occured during Interacting with the JSON File");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceFileException("Unable to Read Test Data from JSON. " + e.getMessage());
		}

		return testDetails;

	}

}
