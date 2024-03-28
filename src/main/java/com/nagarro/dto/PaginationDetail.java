//Pagination DTO to handle the pageInfo response in get mapping
package com.nagarro.dto;

public class PaginationDetail {
	private boolean hasNextPage;
    private boolean hasPreviousPage;
    private long total;

    public PaginationDetail(long total, int limit, int offset) {
        this.hasNextPage = total > (limit + offset);
        this.hasPreviousPage = offset > 0;
        this.total = total;
    }

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
    

}
