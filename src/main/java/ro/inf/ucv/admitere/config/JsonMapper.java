package ro.inf.ucv.admitere.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonMapper extends ObjectMapper {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonMapper() {
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		this.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	}
}
