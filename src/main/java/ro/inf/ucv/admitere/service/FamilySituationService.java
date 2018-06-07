package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.FamilySituation;
import ro.inf.ucv.admitere.repository.FamilySituationRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class FamilySituationService {

	private static final Logger logger = Logger.getLogger(FamilySituationService.class);

	@Autowired
	private FamilySituationRepository familySituationRepository;

	public List<FamilySituation> findAll() {
		List<FamilySituation> familySituations = null;
		try {
			familySituations = familySituationRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all family situations: ", e);
		}
		return familySituations;
	}

	public FamilySituation save(FamilySituation familySituation, boolean flush) {
		FamilySituation savedFamilySituation = null;
		try {
			if (familySituation != null) {
				if (flush) {
					savedFamilySituation = familySituationRepository.saveAndFlush(familySituation);
				} else {
					savedFamilySituation = familySituationRepository.save(familySituation);
				}
			}
		} catch (Exception e) {
			logger.error("Save family situation: " + familySituation, e);
			throw e;
		}

		return savedFamilySituation;
	}

	public FamilySituation findOne(Integer id) {
		FamilySituation familySituation = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				familySituation = familySituationRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find family situation by id: " + id, e);
		}

		return familySituation;
	}
}
