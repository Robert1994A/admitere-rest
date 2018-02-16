package ro.inf.ucv.admitere.wrapper;

import javax.validation.constraints.Min;

public class SearchModel {

	private String search = "";

	@Min(1)
	private Integer pageSize = 10;

	@Min(0)
	private Integer pageNumber = 0;

	private String direction;

	private String sortBy;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public String toString() {
		return "SearchModel [search=" + search + ", pageSize=" + pageSize + ", pageNumber=" + pageNumber
				+ ", direction=" + direction + ", sortBy=" + sortBy + "]";
	}

}
