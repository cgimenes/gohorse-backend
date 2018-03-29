package com.xgh.infra.repository;

import java.util.List;

public class PagedResult<T> {
	public static int PAGE_SIZE = 10;
	
	private List<T> items;
	
	public PagedResult(List<T> items) {
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}
}
