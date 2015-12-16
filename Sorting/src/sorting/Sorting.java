package sorting;

import util.Util;

public abstract class Sorting {
	
	public int[] randomList = null;
	public String sSortingTitle = null;
	public int iSortingNumber = 0;
	private long lStartTime = 0;
	private long lEndTime = 0;
	
	public void newRandomNumberList() {
		randomList = Util.getRandomNumber(iSortingNumber);
	}
	
	public void before() {
		System.out.println("===================================================");
		System.out.println("Sorting Title : " + sSortingTitle);
		System.out.println("Sorting Number : " + iSortingNumber);
		/*System.out.print("before Sorting : ");
		for (int iNum : randomList) {
			System.out.print(iNum + ",");
		}
		System.out.println("");*/
	}
	
	public void after() {		
		/*System.out.print("after Sorting : ");
		for (int iNum : randomList) {
			System.out.print(iNum + ",");
		}
		System.out.println("");*/
		System.out.println("time : " + ((lEndTime-lStartTime)*0.000001) + "ms");
		System.out.println("===================================================");
	}
	
	public abstract void submit();
	
	public void process() {
		newRandomNumberList();
		before();
		lStartTime = System.nanoTime();
		submit();
		lEndTime = System.nanoTime();
		after();
	}
}
