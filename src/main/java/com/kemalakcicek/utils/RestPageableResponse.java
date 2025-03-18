package com.kemalakcicek.utils;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestPageableResponse<T> {

	private List<T> content;

	private int pageNumber;

	private int pageSize;

	private long totalElements;

}
