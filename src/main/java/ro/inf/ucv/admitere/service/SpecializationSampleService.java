package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.SpecializationSample;
import ro.inf.ucv.admitere.repository.SpecializationSampleRepository;

@Service
@Transactional
public class SpecializationSampleService {

	private static final Logger logger = Logger.getLogger(SpecializationSampleService.class);

	@Autowired
	private SpecializationSampleRepository specializationSampleRepository;

	public List<SpecializationSample> findAll() {
		List<SpecializationSample> specializationSamples = null;
		try {
			specializationSamples = specializationSampleRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all specialization samples: ", e);
		}
		return specializationSamples;
	}

	public SpecializationSample save(SpecializationSample specializationSample, boolean flush) {
		SpecializationSample savedSpecializationSample = null;
		try {
			if (specializationSample != null) {
				if (flush) {
					savedSpecializationSample = specializationSampleRepository.saveAndFlush(specializationSample);
				} else {
					savedSpecializationSample = specializationSampleRepository.save(specializationSample);
				}
			}
		} catch (Exception e) {
			logger.error("Save specialization sample: " + specializationSample, e);
			throw e;
		}
		return savedSpecializationSample;
	}
}
