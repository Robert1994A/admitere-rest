package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.entity.State;
import ro.inf.ucv.admitere.exceptions.NotFoundException;
import ro.inf.ucv.admitere.repository.CityRepository;

@Service
@Transactional
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateService stateService;

	public List<City> findAll() throws NotFoundException {
		return cityRepository.findAll();

	}

	public Page<City> findAll(PageRequest pageRequest) throws NotFoundException {
		return cityRepository.findAll(pageRequest);

	}

	public City save(City city) {
		return cityRepository.saveAndFlush(city);
	}

	public List<City> findByState(Long stateId) {
		List<City> cities = null;
		if (stateId != null && stateId > 0) {
			State state = stateService.findOne(stateId);
			if (state != null) {
				cities = cityRepository.findByState(state);
			}
		}

		return cities;
	}

	public City findOne(Long id) {
		City city = null;
		if (id != null && id >= 0) {
			city = cityRepository.findById(id).get();
		}
		
		return city;
	}
}
