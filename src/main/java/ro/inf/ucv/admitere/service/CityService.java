package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.entity.State;
import ro.inf.ucv.admitere.repository.CityRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class CityService {

	private static final Logger logger = Logger.getLogger(CityService.class);

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateService stateService;

	public List<City> findAll() {
		List<City> cities = null;
		try {
			cities = cityRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all cities: ", e);
		}
		return cities;
	}

	public City save(City city, boolean flush) {
		City savedCity = null;
		try {
			if (city != null) {
				if (flush) {
					savedCity = cityRepository.saveAndFlush(city);
				} else {
					savedCity = cityRepository.save(city);
				}
			}
		} catch (Exception e) {
			logger.error("Save city: " + city, e);
		}
		return savedCity;
	}

	public List<City> findByState(Integer stateId) {
		List<City> cities = null;
		try {
			if (PrimitiveUtils.isValid(stateId)) {
				State state = stateService.findOne(stateId);
				if (state != null) {
					cities = cityRepository.findByState(state);
				}
			}
		} catch (Exception e) {
			logger.error("Find cities by state id: " + stateId, e);
		}

		return cities;
	}

	public City findOne(Integer id) {
		City city = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				city = cityRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find city by id: " + id, e);
		}

		return city;
	}
}
