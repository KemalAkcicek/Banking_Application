package com.kemalakcicek.utils;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PagerUtils {

	public boolean isNullOrEmpty(String value) {

		return value == null || value.trim().length() == 0;
	}

	public Pageable toPageable(RestPageableRequest request) {

		if (!isNullOrEmpty(request.getColumnName())) {

			Sort sort = request.isAsc() ? Sort.by(Direction.ASC, request.getColumnName())
					: Sort.by(Direction.DESC, request.getColumnName());

			return PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);

		}

		return PageRequest.of(request.getPageNumber(), request.getPageSize());

	}

	public <T> RestPageableResponse<T> toPageableResponse(Page<?> page, List<T> content) {

		RestPageableResponse<T> restPageableResponse = new RestPageableResponse<>();

		restPageableResponse.setContent(content);
		restPageableResponse.setPageNumber(page.getPageable().getPageNumber());
		restPageableResponse.setPageSize(page.getPageable().getPageSize());
		restPageableResponse.setTotalElements(page.getTotalElements());

		return restPageableResponse;
	}

}
