package sorting;
public class Bubble extends Sorting{
	
	public Bubble(int iRandom) {
		super.sSortingTitle = "Bubble Sorting";
		super.iSortingNumber = iRandom;
	}
	@Override
	public void submit() {
		int[] randomList = super.randomList;
		int iTotalSize = randomList.length;
		int iTemp = 0;
		for (int i = 0; i < iTotalSize; i++) {
			for (int j = 0; j < iTotalSize - i - 1; j++) {
				if (randomList[j] > randomList[j+1]) {
					iTemp = randomList[j+1];
					randomList[j+1] = randomList[j];
					randomList[j] = iTemp;
				}
			}
		}		
	}
}
