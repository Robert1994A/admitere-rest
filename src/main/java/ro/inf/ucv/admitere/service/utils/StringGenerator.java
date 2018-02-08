package ro.inf.ucv.admitere.service.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringGenerator {

	public String getRandomString() {
		return RandomStringUtils.random(256, true, true);
	}

	public String getUsernameString() {
		return RandomStringUtils.random(13, false, true);
	}

}