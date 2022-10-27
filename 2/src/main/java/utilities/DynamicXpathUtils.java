package utilities;

public final class DynamicXpathUtils {

	public static String getXpathForString(String xpath, String value) {
		return String.format(xpath, value);
	}

	public static String getXpathForString(String xpath, String value1, String value2) {
		return String.format(xpath, value1, value2);
	}

	public static String getXpathForString(String xpath, String value1, String value2, String value3) {
		return String.format(xpath, value1, value2, value3);
	}

	public static String getXpathForEnum(String xpath, Enum<?> value) {
		return String.format(xpath, value.toString().replace("_", " "));
	}

	public static String getXpathForEnum(String xpath, Enum<?> value1, Enum<?> value2) {
		return String.format(xpath, value1.toString().replace("_", " "), value2.toString().replace("_", " "));
	}

	public static String getXpathForEnum(String xpath, Enum<?> value1, Enum<?> value2, Enum<?> value3) {
		return String.format(xpath, value1.toString().replace("_", " "), value2.toString().replace("_", " "),
				value3.toString().replace("_", " "));
	}

}
