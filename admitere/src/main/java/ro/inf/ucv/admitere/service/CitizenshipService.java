package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.Citizenship;
import ro.inf.ucv.admitere.repository.CitizenshipRepository;

@Service
@Transactional
public class CitizenshipService {

	@Autowired
	private CitizenshipRepository citizenshipRepository;

	public List<Citizenship> findAll() {
		return citizenshipRepository.findAll();
	}

	public Page<Citizenship> findAll(PageRequest pageRequest) {
		return citizenshipRepository.findAll(pageRequest);
	}

	public Citizenship saveAndFlush(Citizenship citizenship) {
		return citizenshipRepository.saveAndFlush(citizenship);
	}

	public Citizenship findOne(Integer id) {
		Citizenship citizenship = null;
		if (id != null && id >= 0) {
			citizenship = citizenshipRepository.findById(id).get();
		}

		return citizenship;
	}
}
