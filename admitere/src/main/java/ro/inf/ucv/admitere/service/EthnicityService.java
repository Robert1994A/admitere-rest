package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.Ethnicity;
import ro.inf.ucv.admitere.repository.EthnicityRepository;

@Service
@Transactional
public class EthnicityService {

	@Autowired
	private EthnicityRepository ethnicityRepository;

	public List<Ethnicity> findAll() {
		return ethnicityRepository.findAll();
	}

	public Page<Ethnicity> findAll(PageRequest pageRequest) {
		return ethnicityRepository.findAll(pageRequest);
	}

	public Ethnicity saveAndFlush(Ethnicity ethnicity) {
		return ethnicityRepository.saveAndFlush(ethnicity);
	}

	public Ethnicity findOne(Integer id) {
		Ethnicity ethnicity = null;
		if (id != null && id >= 0) {
			ethnicity = ethnicityRepository.findById(id).get();
		}

		return ethnicity;
	}
}
