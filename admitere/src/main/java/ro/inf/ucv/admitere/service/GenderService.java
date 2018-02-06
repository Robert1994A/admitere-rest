package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.repository.GenderRepository;

@Service
@Transactional
public class GenderService {

	@Autowired
	private GenderRepository genderRepository;

	public List<Gender> findAll() {
		return genderRepository.findAll();
	}

	public Page<Gender> findAll(PageRequest pageRequest) {
		return genderRepository.findAll(pageRequest);
	}

	public Gender saveAndFlush(Gender gender) {
		return genderRepository.saveAndFlush(gender);
	}

	public Gender findOne(Integer id) {
		Gender gender = null;
		if (id != null && id >= 0) {
			gender = genderRepository.findById(id).get();
		}

		return gender;
	}
}
