package com.kemalakcicek.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorException<E> {

	private Integer status;

	private Exception<E> exception;

	@Override
	public String toString() {
		return "{" + "\n  \"status\": " + this.status + "," + "\n  \"exception\": {" + "\n    \"hostname\": \""
				+ exception.getHostName() + "\"," + "\n    \"path\": \"" + exception.getPath() + "\","
				+ "\n    \"createTime\": \"" + exception.getCreateTime() + "\"," + "\n    \"message\": \""
				+ exception.getMessage() + "\"" + "\n  }" + "\n}";
	}

}
