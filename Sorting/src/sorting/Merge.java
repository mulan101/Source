package sorting;

public class Merge extends Sorting {

	public int[] returnList;

	public Merge(int iRandom) {
		super.sSortingTitle = "Merge Sorting";
		super.iSortingNumber = iRandom;
		returnList = new int[iRandom];
	}

	@Override
	public void submit() {
		int[] randomList = super.randomList;
		join(randomList, 0, randomList.length-1);
		super.randomList = this.returnList;
	}

	private void join(int[] paramList, int iLeft, int iRight) {
		if (iLeft < iRight) {
			int iMid = (iLeft + iRight) / 2;
			join(paramList, iLeft, iMid);
			join(paramList, iMid+1, iRight);
			merge(paramList, iLeft, iMid, iRight);
		}
	}

	private void merge(int[] paramList, int iLeft, int iMid, int iRight) {

		int i = iLeft;
		int j = iMid + 1;
		int k = iLeft;
		while (i <= iMid && j <= iRight) {
			if (paramList[i] < paramList[j]) {
				returnList[k] = paramList[i];
				i++;				
			} else {
				returnList[k] = paramList[j];
				j++;
			}
			k++;
		}

		if (i > iMid) {
			for (int m = j; m <= iRight; m++) {
				returnList[k] = paramList[m];
				k++;
			}
		} else {
			for (int m = i; m <= iMid; m++) {
				returnList[k] = paramList[m];
				k++;
			}
		}

	}
}
