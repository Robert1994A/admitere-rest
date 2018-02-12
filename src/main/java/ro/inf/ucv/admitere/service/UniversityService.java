package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.University;

import ro.inf.ucv.admitere.repository.UniversityRepository;
import ro.inf.ucv.admitere.service.utils.PaginationUtils;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Service
@Transactional
public class UniversityService {
	private static final Logger logger = Logger.getLogger(UniversityService.class);

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private PaginationUtils paginationUtils;

	public Page<University> findAll(Pageable pageable) {
		Page<University> universities = null;
		try {
			universities = universityRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Find universities(pageable): ", e);
		}
		return universities;
	}

	public List<University> findAll() {
		List<University> universities = null;
		try {
			universities = universityRepository.findAll();
		} catch (Exception e) {
			logger.error("Find universities(pageable): ", e);
		}
		return universities;
	}

	public University save(University university, boolean flush) {
		University savedUniversity = null;
		try {
			if (university != null) {
				if (flush) {
					savedUniversity = universityRepository.saveAndFlush(university);
				} else {
					savedUniversity = universityRepository.save(university);
				}
			}
		} catch (Exception e) {
			logger.error("Save university: " + university, e);
		}
		return savedUniversity;
	}

	public Page<University> inteligentPagination(SearchModel searchModel) {
		Page<University> universities = null;
		try {
			if (searchModel != null) {
				Pageable pageable = paginationUtils.getPageRequest(new University(), searchModel);
				if (StringUtils.isNotBlank(searchModel.getSearch())) {
					universities = pagination(searchModel.getSearch(), pageable);
				} else {
					universities = findAll(pageable);
				}
			}
		} catch (Exception e) {
			logger.error("Find universities: " + searchModel, e);
		}

		return universities;
	}

	public Page<University> pagination(String search, Pageable pageable) {
		return universityRepository.findByNameOrUrlOrDescriptionAllIgnoreCaseContaining(search, search, pageable);
	}

	public void saveUniversities(List<University> universitiesList, boolean flush) {
		if (universitiesList != null && !universitiesList.isEmpty()) {
			for (University university : universitiesList) {
				this.save(university, flush);
			}
		}
	}
}
