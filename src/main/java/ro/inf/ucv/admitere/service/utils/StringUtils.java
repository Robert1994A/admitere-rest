package ro.inf.ucv.admitere.service.utils;

public class StringUtils {

	public static String removeNewLineTabsAndEnter(String string) {
		if (string == null) {
			return null;
		}

		return string.replace("\n", "").replace("\t", "").replace("\r", "");
	}
}
