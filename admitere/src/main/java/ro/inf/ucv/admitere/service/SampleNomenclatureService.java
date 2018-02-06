package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.SampleNomenclature;
import ro.inf.ucv.admitere.repository.SampleNomenclatureRepository;

@Service
@Transactional
public class SampleNomenclatureService {

	@Autowired
	private SampleNomenclatureRepository sampleNomenclatureRepository;

	public List<SampleNomenclature> findAll() {
		return sampleNomenclatureRepository.findAll();
	}

	public Page<SampleNomenclature> findAll(Pageable pageable) {
		return sampleNomenclatureRepository.findAll(pageable);
	}

	public SampleNomenclature saveAndFlush(SampleNomenclature sampleNomenclature) {
		return sampleNomenclatureRepository.saveAndFlush(sampleNomenclature);
	}
}
