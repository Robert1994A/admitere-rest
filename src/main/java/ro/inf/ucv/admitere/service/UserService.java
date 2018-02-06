package ro.inf.ucv.admitere.service;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Profile;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.repository.UserRepository;
import ro.inf.ucv.admitere.service.utils.PaginationUtils;
import ro.inf.ucv.admitere.utils.ListUtils;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Service
@Transactional
public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaginationUtils paginationUtils;

	public User save(User user) {
		User savedUser = null;
		try {
			if (user != null) {
				savedUser = userRepository.save(user);
			}
		} catch (Exception e) {
			logger.error("Save user: ", e);
		}

		return savedUser;
	}

	public User findOne(String id) {
		User user = null;
		try {
			if (StringUtils.isNotBlank(id)) {
				user = userRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find user by id: ", e);
		}

		return user;
	}

	public User findByUsername(String username) {
		User user = null;
		try {
			if (StringUtils.isNotBlank(username)) {
				user = userRepository.findByUsername(username.trim());
			}
		} catch (Exception e) {
			logger.error("Find user by username: ", e);
		}

		return user;
	}

	public Page<User> findAll(Pageable pageable) {
		Page<User> users = null;
		if (pageable != null) {
			users = userRepository.findAll(pageable);
		}
		return users;
	}

	public User findByRegisterToken(String registerToken) {
		User user = null;
		try {
			if (StringUtils.isNotBlank(registerToken)) {
				user = userRepository.findByRegisterToken(registerToken);
			}
		} catch (Exception e) {
			logger.error("Find user by register token: ", e);
		}

		return user;
	}

	public User findByUsernameOrEmail(String username, String email) {
		User user = null;
		try {
			if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(username)) {
				user = userRepository.findByUsernameOrEmail(username, email);
			}
		} catch (Exception e) {
			logger.error("Find user by username and email: ", e);
		}
		return user;
	}

	public User findByRecoverPaswordToken(String recoverToken) {
		User user = null;
		try {
			if (StringUtils.isNotBlank(recoverToken)) {
				user = userRepository.findByRecoverPaswordToken(recoverToken);
			}
		} catch (Exception e) {
			logger.error("Find user by recover token: ", e);
		}
		return user;
	}

	public Page<User> pagination(String search, Pageable pageable) {
		Page<User> users = null;
		try {
			if (StringUtils.isNotBlank(search) && pageable != null) {
				users = userRepository.findByUsernameOrEmailAllIgnoreCaseContaining(search, search, pageable);
			}
		} catch (Exception e) {
			logger.error("Paginate users: ", e);
		}
		return users;
	}

	public User findByEmail(String email) {
		User user = null;
		try {
			if (StringUtils.isNotBlank(email)) {
				user = userRepository.findByEmail(email);
			}
		} catch (Exception e) {
			logger.error("Find user by email: ", e);
		}
		return user;
	}

	public Page<User> inteligentPagination(SearchModel searchModel) {
		Page<User> users = null;
		try {
			if (searchModel != null) {
				Pageable pageable = paginationUtils.getPageRequest(new User(), searchModel);
				if (StringUtils.isNotBlank(searchModel.getSearch())) {
					users = pagination(searchModel.getSearch(), pageable);
				} else {
					users = findAll(pageable);
				}
			}
		} catch (Exception e) {
			logger.error("Find users: " + e);
		}

		return users;
	}

	public Profile getProfile(Principal principal) throws Exception {
		Profile profile = null;
		try {
			if (principal != null) {
				User user = findByUsername(principal.getName());
				if (user != null) {
					profile = user.getProfile();
				}
			}
		} catch (Exception e) {
			logger.error("Get user profile: ", e);
		}

		return profile;
	}

	public void deleteUsers(List<String> ids) {
		if (!ListUtils.isEmpty(ids)) {
			List<User> users = userRepository.findAllById(ids);
			if (!ListUtils.isEmpty(users)) {
				userRepository.deleteInBatch(users);
			}
		}
	}

	public long count() {
		return userRepository.count();
	}
}
