package sorting;

public class Quick extends Sorting {

	public int[] returnList;

	public Quick(int iRandom) {
		super.sSortingTitle = "Merge Sorting";
		super.iSortingNumber = iRandom;
		returnList = new int[iRandom];
	}

	@Override
	public void submit() {
		int[] randomList = super.randomList;
		quick(randomList,0,randomList.length-1);		
	}
	
	public void quick(int[] returnList, int iLeft, int iRight) {
		int iPivot = iLeft;
		int iPivotValue = returnList[iPivot];
		
		while(iLeft < iRight) {			
			if(iPivotValue > returnList[iRight]) {
				returnList[iPivot] = returnList[iRight];
				iLeft++;
			} 
		}

	}

}
