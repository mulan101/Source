package sorting;

public class Quick extends Sorting {

	public int[] returnList;

	public Quick(int iRandom) {
		super.sSortingTitle = "Quick Sorting";
		super.iSortingNumber = iRandom;
		returnList = new int[iRandom];
	}

	@Override
	public void submit() {
		int[] randomList = super.randomList;
		quick(randomList, 0, randomList.length - 1);
	}

	public void quick(int[] randomList, int left, int right) {
		int iPivot = randomList[left];
		int iLeft = left;
		int iRight = right;
		while (left < right) {
			// 값이 선택한 피봇과 같거나 크다면, 이동할 필요가 없다
			while ((randomList[right] >= iPivot) && (left < right)) {
				right--;
			}

			// 그렇지 않고 값이 피봇보다 작다면,
			// 피봇의 위치에 현재 값을 넣는다.
			if (left != right) {
				randomList[left] = randomList[right];
			}
			// 왼쪽부터 현재 위치까지 값을 읽어들이면서
			// 피봇보다 큰 값이 있다면, 값을 이동한다.
			while ((randomList[left] <= iPivot) && (left < right)) {
				left++;
			}
			if (left != right) {
				randomList[right] = randomList[left];
				right--;
			}
		}
		// 모든 스캔이 끝났다면, 피봇값을 현재 위치에 입력한다.
		// 이제 피봇을 기준으로 왼쪽에는 피봇보다 작거나 같은 값만 남았다.
		randomList[left] = iPivot;
		iPivot = left;
		left = iLeft;
		right = iRight;

		if (left < iPivot) {
			quick(randomList, left, iPivot - 1);
		}
		if (right > iPivot) {
			quick(randomList, iPivot + 1, right);
		}
	}

}
