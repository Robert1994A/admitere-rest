package ro.inf.ucv.admitere.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AdmissionSession;
import ro.inf.ucv.admitere.entity.Faculty;
import ro.inf.ucv.admitere.entity.FacultyDomainNomenclature;
import ro.inf.ucv.admitere.entity.FacultySpecializationNomenclature;
import ro.inf.ucv.admitere.entity.University;

import ro.inf.ucv.admitere.repository.UniversityRepository;
import ro.inf.ucv.admitere.service.utils.PaginationUtils;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;
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
			throw e;
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
		return universityRepository.findByNameOrUrlOrDescriptionAllIgnoreCaseContaining(search, search, search,
				pageable);
	}

	public void saveUniversities(List<University> universitiesList, boolean flush) {
		if (universitiesList != null && !universitiesList.isEmpty()) {
			for (University university : universitiesList) {
				this.save(university, flush);
			}
		} else {
			logger.error("No universities provided for save.");
		}
	}

	public List<Faculty> getFaculties(Integer universityId) {
		List<Faculty> faculties = null;
		try {
			if (PrimitiveUtils.isValid(universityId)) {
				University university = universityRepository.findById(universityId).get();
				if (university != null) {
					faculties = university.getFaculties();
				}
			}
		} catch (Exception e) {
			logger.error("Find faculties by university id: " + universityId, e);
		}

		return faculties;
	}

	public Faculty getFacultyByUniversityAndFacultyId(Integer universityId, Integer facultyId) {
		Faculty faculty = null;
		try {
			if (PrimitiveUtils.isValid(facultyId) && PrimitiveUtils.isValid(universityId)) {
				University university = universityRepository.findById(universityId).get();
				if (university != null) {
					List<Faculty> faculties = university.getFaculties();
					if (faculties != null && !faculties.isEmpty()) {
						faculty = faculties.stream()
								.filter(currentFaculty -> currentFaculty.getId().intValue() == facultyId.intValue())
								.findFirst().orElse(null);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Find faculty by university id " + universityId + " and faculty id: " + facultyId, e);
		}
		return faculty;
	}

	public University findById(Integer universityId) {
		University university = null;
		try {
			if (PrimitiveUtils.isValid(universityId)) {
				university = universityRepository.findById(universityId).get();
			}
		} catch (Exception e) {
			logger.error("Find university by id: " + universityId, e);
		}

		return university;
	}

	public List<FacultyDomainNomenclature> getFacultyDomains(Integer universityId, Integer facultyId) {
		List<FacultyDomainNomenclature> facultyDomainNomenclatures = null;
		Faculty faculty = getFacultyByUniversityAndFacultyId(universityId, facultyId);
		if (faculty != null) {
			facultyDomainNomenclatures = faculty.getFacultyDomainNomenclatures();
		}
		return facultyDomainNomenclatures;
	}

	public List<AdmissionSession> getFacultySessions(Integer universityId, Integer facultyId) {
		List<AdmissionSession> admissionSessions = null;
		Faculty faculty = getFacultyByUniversityAndFacultyId(universityId, facultyId);
		if (faculty != null) {
			admissionSessions = faculty.getAdmissionSessions();
		}

		return admissionSessions;
	}

	public List<FacultySpecializationNomenclature> getFacultySpecializations(Integer universityId, Integer facultyId) {
		List<FacultySpecializationNomenclature> facultySpecializationNomenclatures = null;
		List<FacultyDomainNomenclature> facultyDomainNomenclatures = getFacultyDomains(universityId, facultyId);
		if (facultyDomainNomenclatures != null && !facultyDomainNomenclatures.isEmpty()) {
			facultySpecializationNomenclatures = new ArrayList<>();
			for (FacultyDomainNomenclature facultyDomainNomenclature : facultyDomainNomenclatures) {
				List<FacultySpecializationNomenclature> facultySpecializationNomenclaturesForDomain = facultyDomainNomenclature
						.getFacultySpecializationNomenclatures();
				if (facultySpecializationNomenclaturesForDomain != null
						&& !facultySpecializationNomenclaturesForDomain.isEmpty()) {
					facultySpecializationNomenclatures.addAll(facultySpecializationNomenclaturesForDomain);
				}
			}
		}
		return facultySpecializationNomenclatures;
	}
}
