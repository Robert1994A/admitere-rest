package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.SocialSituation;
import ro.inf.ucv.admitere.repository.SocialSituationRepository;

@Service
@Transactional
public class SocialSituationService {

	@Autowired
	private SocialSituationRepository socialSituationRepository;

	public List<SocialSituation> findAll() {
		return socialSituationRepository.findAll();
	}

	public Page<SocialSituation> findAll(PageRequest pageRequest) {
		return socialSituationRepository.findAll(pageRequest);
	}

	public SocialSituation saveAndFlush(SocialSituation socialSituation) {
		return socialSituationRepository.saveAndFlush(socialSituation);
	}

	public SocialSituation findOne(Integer id) {
		SocialSituation socialSituation = null;
		if (id != null && id >= 0) {
			socialSituation = socialSituationRepository.findById(id).get();
		}

		return socialSituation;
	}
}
