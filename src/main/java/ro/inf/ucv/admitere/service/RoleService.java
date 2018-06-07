package ro.inf.ucv.admitere.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.repository.RoleRepository;
import ro.inf.ucv.admitere.service.utils.PaginationUtils;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Service
@Transactional
public class RoleService {

	private static final Logger logger = Logger.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PaginationUtils paginationUtils;

	public Role save(Role role, boolean flush) {
		Role savedRole = null;
		try {
			if (role != null) {
				role.setId(null);
				role.setCreationDate(new Date());
				if (flush) {
					savedRole = roleRepository.saveAndFlush(role);
				} else {
					savedRole = roleRepository.save(role);
				}
			}
		} catch (Exception e) {
			logger.error("Save role: " + role, e);
			throw e;
		}

		return savedRole;
	}

	public List<Role> findAll() {
		List<Role> roles = null;
		try {
			roles = roleRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all roles: ", e);
		}
		return roles;
	}

	public Role findByName(String name) {
		Role role = null;
		try {
			if (StringUtils.isNotBlank(name)) {
				role = roleRepository.findByName(name);
			}
		} catch (Exception e) {
			logger.error("Find role by name: " + name, e);
		}

		return role;
	}

	public Page<Role> search(SearchModel searchModel) {
		Page<Role> roles = null;
		try {
			Pageable pageable = paginationUtils.getPageRequest(new Role(), searchModel);
			roles = roleRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Search roles: " + searchModel, e);
		}

		return roles;
	}

	public void deleteById(Integer id) {
		try {
			if (PrimitiveUtils.isValid(id)) {
				roleRepository.deleteById(id);
			}
		} catch (Exception e) {
			logger.error("Delete role by id: " + id, e);
		}

	}

	public Role findById(Integer id) {
		Role role = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				role = roleRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find role by id: " + id, e);
		}

		return role;
	}

	public List<Role> getUserRoles(Principal principal) {
		List<Role> roles = new ArrayList<>();
		try {
			if (principal != null) {
				User user = userService.findByUsername(principal.getName());
				if (user != null) {
					roles = user.getRoles();
				}
			}
		} catch (Exception e) {
			logger.error("Get user roles for " + principal, e);
		}

		return roles;
	}

	public List<String> deleteByIds(List<Integer> roleIds) {
		List<String> warningMessages = new ArrayList<String>();
		for (Integer roleId : roleIds) {
			if (roleId != null && roleId.intValue() > 0) {
				try {
					this.deleteById(roleId);
				} catch (Exception e) {
					logger.error("Cannot delete role with id: " + roleId);
					warningMessages.add("Cannot delete role with id: " + roleId);
				}
			} else {
				logger.error("Cannot delete role with id: " + roleId);
				warningMessages.add("Cannot role gender with id: " + roleId);
			}
		}

		return warningMessages;
	}
}
