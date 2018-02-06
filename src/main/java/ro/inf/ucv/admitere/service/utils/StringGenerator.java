package ro.inf.ucv.admitere.service.utils;

import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringGenerator {

	public String getRandomString() {
		return RandomStringUtils.random(128, true, true);
	}

	public String getUsernameString() {
		return RandomStringUtils.random(13, false, true);
	}

	public String getRandomNumberForPageTitle() {
		return RandomStringUtils.random(6, false, true);
	}

	public static String getRandomCityNames() {
		return RandomStringUtils.random(8, true, false).toUpperCase(Locale.ROOT);
	}
}