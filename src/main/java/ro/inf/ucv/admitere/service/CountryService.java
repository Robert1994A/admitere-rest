package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.repository.CountryRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class CountryService {

	private static final Logger logger = Logger.getLogger(CountryService.class);

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findAll() {
		List<Country> countries = null;
		try {
			countries = countryRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all countries: ", e);
		}
		return countries;
	}

	public Country findById(Integer countryId) {
		Country country = null;
		try {
			if (PrimitiveUtils.isValid(countryId)) {
				country = countryRepository.findById(countryId).get();
			}
		} catch (Exception e) {
			logger.error("Find country by id: " + countryId, e);
		}

		return country;
	}
}
