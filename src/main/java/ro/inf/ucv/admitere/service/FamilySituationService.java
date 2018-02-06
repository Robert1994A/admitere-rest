package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.FamilySituation;
import ro.inf.ucv.admitere.repository.FamilySituationRepository;

@Service
@Transactional
public class FamilySituationService {

	@Autowired
	private FamilySituationRepository familySituationRepository;

	public List<FamilySituation> findAll() {
		return familySituationRepository.findAll();
	}

	public Page<FamilySituation> findAll(PageRequest pageRequest) {
		return familySituationRepository.findAll(pageRequest);
	}

	public FamilySituation saveAndFlush(FamilySituation familySituation) {
		return familySituationRepository.saveAndFlush(familySituation);
	}

	public FamilySituation findOne(Integer id) {
		FamilySituation familySituation = null;
		if (id != null && id >= 0) {
			familySituation = familySituationRepository.findById(id).get();
		}
		return familySituation;
	}
}
