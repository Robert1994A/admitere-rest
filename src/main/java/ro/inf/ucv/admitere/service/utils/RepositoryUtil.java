package ro.inf.ucv.admitere.service.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.service.FacultyService;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
public class RepositoryUtil {

	private static final Logger logger = Logger.getLogger(FacultyService.class);

	public List<String> deleteByIds(List<Integer> ids, JpaRepository<? extends Object, Integer> repository) {
		List<String> warningMessages = new ArrayList<String>();
		if (ids != null && !ids.isEmpty()) {
			for (Integer id : ids) {
				if (PrimitiveUtils.isValid(id)) {
					try {
						repository.deleteById(id);
					} catch (Exception e) {
						logger.error("Cannot delete entry with id: " + id);
						warningMessages.add("Cannot delete entry with id: " + id);
					}
				} else {
					logger.error("Cannot delete entry with id: " + id);
					warningMessages.add("Cannot delete entry with id: " + id);
				}
			}
		}

		return warningMessages;
	}
}
