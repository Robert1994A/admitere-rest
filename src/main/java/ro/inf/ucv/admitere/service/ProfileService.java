package ro.inf.ucv.admitere.service;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.repository.ProfileRepository;

@Service
@Transactional
public class ProfileService {

	private static final Logger logger = Logger.getLogger(ProfileService.class);

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserService userService;

	public List<Profile> findAll() {
		List<Profile> profiles = null;
		try {
			profiles = profileRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all profiles: ", e);
		}
		return profiles;
	}

	/**
	 * Save profile.
	 * 
	 * @param profile
	 * @param principal
	 * @throws Exception
	 */
	public Profile save(Profile profile, Principal principal) throws Exception {
		Profile savedProfile = null;
		try {
			if (profile != null && principal != null) {
				User authenticatedUser = userService.findByUsername(principal.getName());
				if (authenticatedUser != null) {
					if (authenticatedUser.getProfile() == null) {
						savedProfile = profileRepository.save(profile);
						authenticatedUser.setProfile(savedProfile);
						userService.save(authenticatedUser, false);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Save profile: ", e);
		}

		return savedProfile;
	}
}
