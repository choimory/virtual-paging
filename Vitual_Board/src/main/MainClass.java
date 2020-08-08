package main;

import component.PageMaker;

public class MainClass {

	public static void main(String[] args) {
		PageMaker board = new PageMaker();
		int display=40;
		int page=11;
		int totalRow=5001;
		
		board.totalPaging(display, page, totalRow);
		board.printVirtualBoard();
		
	}

}
