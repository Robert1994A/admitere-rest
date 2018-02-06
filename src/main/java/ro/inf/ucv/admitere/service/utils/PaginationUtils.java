package ro.inf.ucv.admitere.service.utils;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.wrapper.SearchModel;

@Service
public class PaginationUtils {

	private static final Logger logger = Logger.getLogger(PaginationUtils.class);

	@Autowired
	private ConfigurationUtils configurationUtils;

	public Pageable getPageRequest(Object object, SearchModel searchModel) {
		int pageNumber = searchModel.getPageNumber();
		int pageSize = searchModel.getPageSize();
		String sortBy = searchModel.getSortBy();
		String sortDirection = null;
		Direction direction = searchModel.getDirection();
		if (direction != null) {
			sortDirection = direction.name();
		}
		return getPageRequest(object, pageNumber, pageSize, sortDirection, sortBy);
	}

	public Pageable getPageRequest(Object object, Integer pageNumber, Integer pageSize, String sortDirection,
			String sortBy) {
		Sort sort = null;
		if (pageNumber == null || pageNumber < 0) {
			pageNumber = 0;
		}

		if (pageSize == null || pageSize < 1) {
			pageSize = configurationUtils.getPaginationPerPage();
		}

		if (StringUtils.isBlank(sortBy) && StringUtils.isBlank(sortDirection)) {
			return PageRequest.of(pageNumber, pageSize);
		}

		sortBy = getSortBy(object, sortBy);

		if (StringUtils.isBlank(sortBy)) {
			return PageRequest.of(pageNumber, pageSize);
		}

		if ("ASC".equalsIgnoreCase(sortDirection)) {
			sort = new Sort(Direction.ASC, sortBy);
		} else if ("DESC".equalsIgnoreCase(sortDirection)) {
			sort = new Sort(Direction.DESC, sortBy);
		}

		return PageRequest.of(pageNumber, pageSize, sort);
	}

	private String getSortBy(Object object, String sortBy) {
		try {
			if (object != null && StringUtils.isNotBlank(sortBy)) {
				for (Field field : object.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					String fieldName = field.getName();
					if (fieldName != null && fieldName.equalsIgnoreCase(sortBy)) {
						return fieldName;
					}
				}
			}
		} catch (Exception e) {
			// Stay silent.
			logger.error("Try to extract fields from object: ", e);
		}

		return null;
	}
}