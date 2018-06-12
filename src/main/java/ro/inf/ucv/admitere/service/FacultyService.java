package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Faculty;
import ro.inf.ucv.admitere.repository.FacultyRepository;
import ro.inf.ucv.admitere.service.utils.PaginationUtils;
import ro.inf.ucv.admitere.service.utils.RepositoryUtil;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Service
@Transactional
public class FacultyService {

	private static final Logger logger = Logger.getLogger(FacultyService.class);

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private PaginationUtils paginationUtils;

	@Autowired
	private RepositoryUtil repositoryUtil;

	public Page<Faculty> findAll(Pageable pageable) {
		Page<Faculty> faculties = null;
		try {
			faculties = this.facultyRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Find universities(pageable): ", e);
		}
		return faculties;
	}

	public List<Faculty> findAll() {
		List<Faculty> faculties = null;
		try {
			faculties = this.facultyRepository.findAll();
		} catch (Exception e) {
			logger.error("Find universities(pageable): ", e);
		}
		return faculties;
	}

	public Faculty save(Faculty university, boolean flush) {
		Faculty faculties = null;
		try {
			if (university != null) {
				if (flush) {
					faculties = this.facultyRepository.saveAndFlush(university);
				} else {
					faculties = this.facultyRepository.save(university);
				}
			}
		} catch (Exception e) {
			logger.error("Save university: " + university, e);
			throw e;
		}
		return faculties;
	}

	public Page<Faculty> inteligentPagination(SearchModel searchModel) {
		Page<Faculty> faculties = null;
		try {
			if (searchModel != null) {
				Pageable pageable = this.paginationUtils.getPageRequest(new Faculty(), searchModel);
				if (StringUtils.isNotBlank(searchModel.getSearch())) {
					faculties = pagination(searchModel.getSearch(), pageable);
				} else {
					faculties = findAll(pageable);
				}
			}
		} catch (Exception e) {
			logger.error("Find universities: " + searchModel, e);
		}

		return faculties;
	}

	public Page<Faculty> pagination(String search, Pageable pageable) {
		return this.facultyRepository.findByNameOrUrlOrDescriptionAllIgnoreCaseContaining(search, search, pageable);
	}

	public Faculty findOne(Integer id) {
		Faculty faculty = null;
		if (PrimitiveUtils.isValid(id)) {
			try {
				faculty = this.facultyRepository.findById(id).get();
			} catch (Exception e) {
				logger.error("Find faculty by id: " + id, e);
			}
		}

		return faculty;
	}

	public List<String> deleteByIds(List<Integer> facultyIds) {
		return this.repositoryUtil.deleteByIds(facultyIds, this.facultyRepository);
	}
}
