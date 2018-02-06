package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.repository.RoleRepository;
import ro.inf.ucv.admitere.service.utils.PaginationUtils;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Service
@Transactional
public class RoleService {

	private static final Logger logger = Logger.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PaginationUtils paginationUtils;

	public void save(Role role) throws Exception {
		if (roleRepository.countByName(role.getName()) == 1) {
			throw new Exception("Role with name " + role.getName() + " already exist!");
		}
		roleRepository.save(role);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
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

	public Page<Role> findAll(PageRequest pageRequest) {
		return roleRepository.findAll(pageRequest);
	}

	public Page<Role> search(SearchModel searchModel) {
		Pageable pageable = paginationUtils.getPageRequest(new Role(), searchModel);
		return roleRepository.findAll(pageable);
	}

	public void deleteById(Long id) {
		if (id != null && id >= 0) {
			roleRepository.deleteById(id);
		}
	}

	public Role findById(Long id) {
		Role role = null;
		if (id != null) {
			role = roleRepository.findById(id).get();
		}

		return role;
	}
}
