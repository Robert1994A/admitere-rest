package ro.inf.ucv.admitere.utils;

import java.util.List;

public class ListUtils {

	public static boolean isEmpty(List<?> list) {
		if (list == null) {
			return true;
		}

		return list.isEmpty();
	}
}
