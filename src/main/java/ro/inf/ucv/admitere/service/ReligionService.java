package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Religion;
import ro.inf.ucv.admitere.repository.ReligionRepository;

@Service
@Transactional
public class ReligionService {

	@Autowired
	private ReligionRepository religionRepository;

	public List<Religion> findAll() {
		return religionRepository.findAll();
	}

	public Page<Religion> findAll(PageRequest pageRequest) {
		return religionRepository.findAll(pageRequest);
	}

	public Religion saveAndFlush(Religion religion) {
		return religionRepository.saveAndFlush(religion);
	}

	public Religion findOne(Integer id) {
		Religion religion = null;
		if (id != null && id >= 0) {
			religion = religionRepository.findById(id).get();
		}
		return religion;
	}
}
