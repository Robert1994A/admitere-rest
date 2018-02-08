package ro.inf.ucv.admitere.utils;

public class PrimitiveUtils {

	public static boolean isValid(Long value) {
		return (value != null && value >= 0);
	}
	
	public static boolean isValid(Integer value) {
		return (value != null && value >= 0);
	}
}
