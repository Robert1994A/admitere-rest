package ro.inf.ucv.admitere.captcha;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class AttemptsService {

	@Value("${attempts.maxAttempts}")
	private Integer MAX_ATTEMPT;

	@Value("${attempts.interval}")
	private Integer INTERVAL;

	private LoadingCache<String, Integer> attemptsCache;

	public AttemptsService() {
		super();
		attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(final String key) {
						return 0;
					}
				});
	}

	public void reCaptchaSucceeded(final String key) {
		attemptsCache.invalidate(key);
	}

	public void reCaptchaFailed(final String key) {
		int attempts = attemptsCache.getUnchecked(key);
		attempts++;
		attemptsCache.put(key, attempts);
	}

	public boolean isBlocked(final String key) {
		return attemptsCache.getUnchecked(key) >= MAX_ATTEMPT;
	}

	public LoadingCache<String, Integer> getAttemptsCache() {
		return attemptsCache;
	}

	public void setAttemptsCache(LoadingCache<String, Integer> attemptsCache) {
		this.attemptsCache = attemptsCache;
	}

	public int getMAX_ATTEMPT() {
		return MAX_ATTEMPT;
	}

}
