package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.entity.State;
import ro.inf.ucv.admitere.repository.StateRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class StateService {

	private static final Logger logger = Logger.getLogger(StateService.class);

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CountryService countryService;

	public List<State> findAll() {
		List<State> states = null;
		try {
			states = stateRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all states: ", e);
		}
		return states;
	}

	public State save(State state, boolean flush) {
		State savedState = null;
		try {
			if (state != null) {
				if (flush) {
					savedState = stateRepository.saveAndFlush(state);
				} else {
					savedState = stateRepository.save(state);
				}
			}
		} catch (Exception e) {
			logger.error("Save state: " + state, e);
			throw e;
		}
		return savedState;
	}

	public List<State> findByCountry(Integer countryId) {
		List<State> states = null;
		try {
			if (PrimitiveUtils.isValid(countryId)) {
				Country country = countryService.findById(countryId);
				if (country != null) {
					states = stateRepository.findByCountry(country);
				}
			}
		} catch (Exception e) {
			logger.error("Find states by country id: " + countryId, e);
		}

		return states;
	}

	public State findOne(Integer stateId) {
		State state = null;
		try {
			if (PrimitiveUtils.isValid(stateId)) {
				state = stateRepository.findById(stateId).get();
			}
		} catch (Exception e) {
			logger.error("Find state by id: " + stateId, e);
		}

		return state;
	}
}
