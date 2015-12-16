package sorting;
public class Select extends Sorting{
	
	public Select(int iRandom) {
		super.sSortingTitle = "Select Sorting";
		super.iSortingNumber = iRandom;
	}
	
	@Override
	public void submit() {
		int[] randomList = super.randomList;		
		int iCursor = 0;
		int iTemp = 0;
		for(int i = 0 ; i < randomList.length ; i++) {
			iCursor = i;
			for(int j = i+1 ; j < randomList.length ; j++) {
				if(randomList[iCursor] > randomList[j]) {
					iCursor = j;
				}
				if(j == randomList.length-1 && i != iCursor) {
					iTemp = randomList[iCursor];
					randomList[iCursor] = randomList[i];
					randomList[i] = iTemp;
				}
			}
		}
	}
}
