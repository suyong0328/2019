package util;

public class PageBean {
	String pageNum;
	int currentPage;
	int PAGESIZE;
	int BLOCKSIZE;
	int startRow;
	int endRow;
	int pageCount;
	int startPage;
	int endPage;
	int rowNum;
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPAGESIZE() {
		return PAGESIZE;
	}
	public void setPAGESIZE(int pAGESIZE) {
		PAGESIZE = pAGESIZE;
	}
	public int getBLOCKSIZE() {
		return BLOCKSIZE;
	}
	public void setBLOCKSIZE(int bLOCKSIZE) {
		BLOCKSIZE = bLOCKSIZE;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

}
