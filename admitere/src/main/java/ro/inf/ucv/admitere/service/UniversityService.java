package ro.inf.ucv.admitere.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
		return universityRepository.findAll(pageable);
	}

	public Page<University> findAll(PageRequest pageRequest) {
		return universityRepository.findAll(pageRequest);
	}

	public University save(University university) {
		return universityRepository.save(university);
	}

	public Page<University> inteligentPagination(SearchModel searchModel) {
		Page<University> universities = null;
		try {
			if (searchModel != null) {
				Pageable pageable = paginationUtils.getPageRequest(new University(), searchModel);
				if (searchModel.getSearch() != null && searchModel.getSearch().trim().length() > 0) {
					universities = pagination(searchModel.getSearch(), pageable);
				} else {
					universities = findAll(pageable);
				}
			}
		} catch (Exception e) {
			logger.error("Find universities: ", e);
		}

		return universities;
	}

	public Page<University> pagination(String search, Pageable pageable) {
		return universityRepository.findByNameOrUrlAllIgnoreCaseContaining(search, search, pageable);
	}
}
