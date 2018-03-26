package ro.inf.ucv.admitere.service.utils;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtils {

	protected BCryptPasswordEncoder encoder = null;

	public SecurityUtils() {
		this.encoder = new BCryptPasswordEncoder(6, new SecureRandom());
	}

	public String getRandomString() {
		return RandomStringUtils.random(256, true, true);
	}

	public String getUsernameCNPString() {
		return RandomStringUtils.random(13, false, true);
	}

	public String getPhoneNumberString() {
		return RandomStringUtils.random(10, false, true);
	}

	public String getSmallRandomString() {
		return RandomStringUtils.random(10, true, false);
	}

	public String getEncodedRandomString() {
		return this.encoder.encode(getRandomString());
	}

	public String encode(String toEncode) {
		return encoder.encode(toEncode);
	}

	public static void main(String[] args) {
		SecurityUtils securityUtils = new SecurityUtils();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			securityUtils.getEncodedRandomString();
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}