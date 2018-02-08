package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.SampleNomenclature;
import ro.inf.ucv.admitere.repository.SampleNomenclatureRepository;

@Service
@Transactional
public class SampleNomenclatureService {

	private static final Logger logger = Logger.getLogger(SampleNomenclatureService.class);

	@Autowired
	private SampleNomenclatureRepository sampleNomenclatureRepository;

	public List<SampleNomenclature> findAll() {
		List<SampleNomenclature> sampleNomenclatures = null;
		try {
			sampleNomenclatures = sampleNomenclatureRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all sample nomenclatures: ", e);
		}
		return sampleNomenclatures;
	}

	public SampleNomenclature save(SampleNomenclature sampleNomenclature, boolean flush) {
		SampleNomenclature savedSampleNomenclature = null;
		try {
			if (sampleNomenclature != null) {
				if (flush) {
					savedSampleNomenclature = sampleNomenclatureRepository.saveAndFlush(sampleNomenclature);
				} else {
					savedSampleNomenclature = sampleNomenclatureRepository.save(sampleNomenclature);
				}
			}
		} catch (Exception e) {
			logger.error("Save sample nomenclature: " + sampleNomenclature, e);
		}
		return savedSampleNomenclature;
	}
}
