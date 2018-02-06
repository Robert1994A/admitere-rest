package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.MedicalCondition;
import ro.inf.ucv.admitere.repository.MedicalConditionRepository;

@Service
@Transactional
public class MedicalConditionService {

	@Autowired
	private MedicalConditionRepository medicalConditionRepository;

	public List<MedicalCondition> findAll() {
		return medicalConditionRepository.findAll();
	}

	public Page<MedicalCondition> findAll(PageRequest pageRequest) {
		return medicalConditionRepository.findAll(pageRequest);
	}

	public MedicalCondition saveAndFlush(MedicalCondition medicalCondition) {
		return medicalConditionRepository.saveAndFlush(medicalCondition);
	}

	public MedicalCondition findOne(Integer id) {
		MedicalCondition medicalCondition = null;
		if (id != null && id >= 0) {
			medicalCondition = medicalConditionRepository.findById(id).get();
		}

		return medicalCondition;
	}
}
