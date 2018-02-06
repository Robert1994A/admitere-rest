package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.MaritalStatus;
import ro.inf.ucv.admitere.repository.MaritalStatusRepository;

@Service
@Transactional
public class MaritalStatusService {

	@Autowired
	private MaritalStatusRepository maritalStatusRepository;

	public List<MaritalStatus> findAll() {
		return maritalStatusRepository.findAll();
	}

	public Page<MaritalStatus> findAll(PageRequest pageRequest) {
		return maritalStatusRepository.findAll(pageRequest);
	}

	public MaritalStatus saveAndFlush(MaritalStatus maritalStatus) {
		return maritalStatusRepository.saveAndFlush(maritalStatus);
	}

	public MaritalStatus findOne(Integer id) {
		MaritalStatus maritalStatus = null;
		if (id != null && id >= 0) {
			maritalStatus = maritalStatusRepository.findById(id).get();
		}
		return maritalStatus;
	}
}
