package sorting;
public class Insert extends Sorting{
	
	public Insert(int iRandom) {
		super.sSortingTitle = "Insert Sorting";
		super.iSortingNumber = iRandom;
	}
	@Override
	public void submit() {
		int[] randomList = super.randomList;		
		int iTotalSize = randomList.length;

		for (int i = 1; i < iTotalSize; i++) {	
			int iTemp = randomList[i];
			int iLeftNum = i-1;			
			while(iLeftNum >= 0 && iTemp < randomList[iLeftNum]) {
				randomList[iLeftNum+1] = randomList[iLeftNum];
				iLeftNum--;
			}
			randomList[iLeftNum+1] = iTemp;
		}		
	}
}
