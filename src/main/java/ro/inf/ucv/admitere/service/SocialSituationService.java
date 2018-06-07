package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.SocialSituation;
import ro.inf.ucv.admitere.repository.SocialSituationRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class SocialSituationService {

	private static final Logger logger = Logger.getLogger(SocialSituationService.class);

	@Autowired
	private SocialSituationRepository socialSituationRepository;

	public List<SocialSituation> findAll() {
		List<SocialSituation> socialSituations = null;
		try {
			socialSituations = socialSituationRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all social situations: ", e);
		}
		return socialSituations;
	}

	public SocialSituation save(SocialSituation socialSituation, boolean flush) {
		SocialSituation savedSocialSituation = null;
		try {
			if (socialSituation != null) {
				if (flush) {
					savedSocialSituation = socialSituationRepository.saveAndFlush(socialSituation);
				} else {
					savedSocialSituation = socialSituationRepository.save(socialSituation);
				}
			}
		} catch (Exception e) {
			logger.error("Save social situation: " + socialSituation, e);
			throw e;
		}
		return savedSocialSituation;
	}

	public SocialSituation findOne(Integer id) {
		SocialSituation socialSituation = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				socialSituation = socialSituationRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find social situation by id: " + id, e);
		}

		return socialSituation;
	}
}
