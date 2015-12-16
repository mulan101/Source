package util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Util {
	public static int[] getRandomNumber(int iSize) {
		int[] returnList = new int[iSize];
		List<Integer> list = new ArrayList<Integer>(); 
		for(int i = 0 ; i < iSize ; i++) {
			list.add(i+1);
		}
		Collections.shuffle(list);
		
		for(int i = 0 ; i < list.size() ; i++) {
			returnList[i] = list.get(i);
		}
		return returnList;
	}
}
