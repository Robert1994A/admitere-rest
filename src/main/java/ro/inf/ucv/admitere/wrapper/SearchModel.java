package ro.inf.ucv.admitere.wrapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Sort.Direction;

public class SearchModel {

	@NotNull
	private String search = "";

	@Min(1)
	private Integer pageSize = 10;

	@Min(0)
	private Integer pageNumber = 0;

	private Direction direction;

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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
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
