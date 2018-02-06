package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.entity.State;
import ro.inf.ucv.admitere.exceptions.NotFoundException;
import ro.inf.ucv.admitere.repository.StateRepository;

@Service
@Transactional
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CountryService countryService;

	public List<State> findAll() throws NotFoundException {
		return stateRepository.findAll();
	}

	public Page<State> findAll(PageRequest pageRequest) throws NotFoundException {
		return stateRepository.findAll(pageRequest);
	}

	public State save(State state) {
		return stateRepository.saveAndFlush(state);
	}

	public List<State> findByCountry(Long countryId) {
		List<State> states = null;
		if (countryId != null && countryId > 0) {
			Country country = countryService.findById(countryId);
			if (country != null) {
				states = stateRepository.findByCountry(country);
			}
		}

		return states;
	}

	public State findOne(Long stateId) {
		return stateRepository.findById(stateId).get();
	}
}
