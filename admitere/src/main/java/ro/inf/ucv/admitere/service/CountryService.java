package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.exceptions.NotFoundException;
import ro.inf.ucv.admitere.repository.CountryRepository;

@Service
@Transactional
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findAll() throws NotFoundException {
		return countryRepository.findAll();
	}

	public Country save(Country country) {
		return countryRepository.saveAndFlush(country);
	}

	public Country findById(Long countryId) {
		return countryRepository.findById(countryId).get();
	}
}
