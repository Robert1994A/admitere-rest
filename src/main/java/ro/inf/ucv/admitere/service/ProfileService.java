package ro.inf.ucv.admitere.service;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.NotAuthenticatedException;
import ro.inf.ucv.admitere.exceptions.NotFoundException;
import ro.inf.ucv.admitere.repository.ProfileRepository;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserService userService;

	public List<Profile> findAll() {
		return profileRepository.findAll();
	}

	public Page<Profile> findAll(PageRequest pageRequest) {
		return profileRepository.findAll(pageRequest);
	}

	/**
	 * Save profile.
	 * 
	 * @param profile
	 * @param principal
	 * @throws Exception
	 */
	public void save(Profile profile, Principal principal) throws Exception {
		if (principal == null) {
			throw new NotAuthenticatedException();
		}
		User authenticatedUser = userService.findByUsername(principal.getName());
		if (authenticatedUser == null) {
			throw new NotFoundException();
		}
		authenticatedUser.setProfile(profileRepository.save(profile));
		userService.save(authenticatedUser);
	}
}
