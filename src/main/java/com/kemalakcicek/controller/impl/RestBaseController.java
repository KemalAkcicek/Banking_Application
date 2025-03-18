package com.kemalakcicek.controller.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.model.RootEntity;
import com.kemalakcicek.utils.PagerUtils;
import com.kemalakcicek.utils.RestPageableRequest;
import com.kemalakcicek.utils.RestPageableResponse;

public class RestBaseController {

	public <T> RootEntity<T> ok(T data) {
		return RootEntity.ok(data);
	}

	public <T> RootEntity<T> error(String message) {
		return RootEntity.error(message);
	}

	public Pageable toPageable(RestPageableRequest request) {
		return PagerUtils.toPageable(request);
	}

	public <T> RestPageableResponse<T> toPageableResponse(Page<?> page, List<T> content) {

		return PagerUtils.toPageableResponse(page, content);
	}
}
