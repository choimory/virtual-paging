package component;

import java.util.ArrayList;
import java.util.List;

public class PageMaker {

	// member
	public int display;
	public int page;
	public int totalRow;
	public int lastPage;
	public List<Integer> pageGroup;

	// toString
	@Override
	public String toString() {
		String result = "";

		return result;
	}

	// constructor
	public PageMaker() {
		this.display = 20;
		this.page = 1;
		this.totalRow = 0;
		this.lastPage = 1;
	}

	public PageMaker(int display, int page, int totalRow, int lastPage) {
		this.display = display;
		this.page = page;
		this.totalRow = totalRow;
		this.lastPage = lastPage;
	}

	// setter, getter
	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		int minimum=20;
		int maximum=100;
		
		// - if display under minimum (display < minimum), display=minimum
		// - if display over maximum (maximum < display), display=maximum
		if (display < minimum) {
			this.display = minimum;
		} else if (maximum < display) {
			this.display = maximum;
		} else {
			this.display = display;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		int minimum=1;
		
		// - if page under minimum (page < minimum), page=minimum
		if (page < minimum) {
			this.page = minimum;
		} else {
			this.page = page;
		}
	}

	public void setPage(int page, int lastPage) {
		int minimum=1;
		
		// - if page under minimum (page < minimum), page=minimum
		// - if page over lastPage (lastPage < page), page=lastPage
		if (page < minimum) {
			this.page = minimum;
		} else if (lastPage < page) {
			this.page = lastPage;
		} else {
			this.page = page;
		}
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		int minimum=1;
		
		// - if totalRow under minimum, totalRow=minimum
		 if (totalRow < minimum) {
			this.totalRow = 0;
		} else  {
			this.totalRow = totalRow;
		} 
	}

	public int getlastPage() {
		return lastPage;
	}

	public void setlastPage(int totalRow) {
		setTotalRow(totalRow);
		double lastPage = 0.0;

		// # calculate lastPage
		// - totalRow divine display 
		// - up decimal (ceil)
		
		// - if totalRow under display, lastPage=1
		// - else, calculate lastPage
		if (totalRow < display) {
			this.lastPage = 1;
		} else{
			lastPage = (double) totalRow / display;
			lastPage = Math.ceil(lastPage);
			this.lastPage = (int) lastPage;
		}  
	}

	public void setlastPage(int display, int totalRow) {
		setTotalRow(totalRow);
		double lastPage = 0.0;

		// # calculate lastPage
		// - totalRow divine display 
		// - up decimal (ceil)
		
		// - if totalRow under display, lastPage=1
		// - else, calculate lastPage
		if (totalRow < display) {
			this.lastPage = 1;
		} else{
			lastPage = (double) totalRow / display;
			lastPage = Math.ceil(lastPage);
			this.lastPage = (int) lastPage;
		}  
	}

	public void pageGroupingBy10() {
		pageGroup = new ArrayList<Integer>();
		double pageRangeDouble = 0.0;
		double lastPageRangeDouble = 0.0;
		int pageRange = 0;
		int lastPageRange = 0;

		// # calculate pageRange for basic paging
		// - page divine 10
		// - drop decimal (floor)
		// - multiple 10
		pageRangeDouble = (double) page / 10;
		pageRangeDouble = Math.floor(pageRangeDouble);
		pageRange = (int) pageRangeDouble * 10;

		// # calculate lastPageRange for lastPageRange paging
		// - page divine 10
		// - drop decimal (floor)
		// - multiple 10
		lastPageRangeDouble = (double) lastPage / 10;
		lastPageRangeDouble = Math.floor(lastPageRangeDouble);
		lastPageRange = (int) lastPageRangeDouble * 10;

		// # make pageGroup
		// - if lastPage under 10, 1~lastPage
		// - if page equal pageRange, pageRange-9 ~ pageRange
		// - if pageRange equal lastPageRange(page reached lastPageGroup), lastPageRange+1~lastPage
		// - else, pageRange+1 ~ pageRange+10
		if (lastPage < 10) {
			for (int i = 1; i <= lastPage; i++) {
				pageGroup.add(i);
			}
		} else if (page == pageRange) {
			for (int i = pageRange - 9; i <= pageRange; i++) {
				pageGroup.add(i);
			}
		} else if (pageRange == lastPageRange) {
			for (int i = pageRange + 1; i <= lastPage; i++) {
				pageGroup.add(i);
			}
		} else {
			for (int i = pageRange + 1; i <= pageRange + 10; i++) {
				pageGroup.add(i);
			}
		}
	}

	public void pageGroupingBy6() {
		pageGroup = new ArrayList<Integer>();
		int pageRange = 3;

		// # make pageGroup
		// - page-pageRange ~ page+pageRange
		// - if page - pageRange under 1, 1~pageRange
		// - if lastPage under page+pageRange, page-pageRange~lastPage
		// - else, page-pageRange ~ page+pageRange
		if (page - pageRange < 1) {
			for (int i = 1; i <= page + pageRange; i++) {
				pageGroup.add(i);
			}
		} else if (lastPage < page + pageRange) {
			for (int i = page - pageRange; i <= lastPage; i++) {
				pageGroup.add(i);
			}
		} else {
			for (int i = page - pageRange; i <= page + pageRange; i++) {
				pageGroup.add(i);
			}
		}
	}

	// total paging
	public void totalPaging(int page, int totalRow) {
		setlastPage(totalRow);
		setPage(page, lastPage);
		pageGroupingBy10();
	}

	public void totalPaging(int display, int page, int totalRow) {
		setDisplay(display);
		setlastPage(totalRow);
		setPage(page, lastPage);
		pageGroupingBy10();
	}

	// Virtualize Board
	public void printVirtualBoard() {
		// # print header
		System.out.println("INDEX | display value | page value | totalRow value | lastPage value");

		// # print content
		// - if page ne lastPage, repeat display to 1, desc
		// - if page eq lastPage, repeat totalRow to ((page-1)*display), desc
		if (page != lastPage) {
			for (int i = display; 1 <= i; i--) {
				System.out.println("" + ((page - 1) * display + i) + " | display:" + display + " | page:" + page + " | totalRow:" + totalRow
						+ " | lastPage:" + lastPage);
			}
		} else if (page == lastPage) {
			for (int i = totalRow; i > ((page - 1) * display); i--) {
				System.out.println("" + i + " | display:" + display + " | page:" + page + " | totalRow:" + totalRow + " | lastPage:" + lastPage);
			}
		}

		// # hr
		System.out.println("-------------------------------------------------------------");

		// # print pagination
		for (int i : pageGroup) {
			if (page == i) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
	}

	public void printVirtualBoard(int display, int page, int totalRow) {
		totalPaging(display, page, totalRow);
		printVirtualBoard();
	}

}
