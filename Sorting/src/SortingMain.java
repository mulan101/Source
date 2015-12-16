
import sorting.*;

public class SortingMain {
	public static void main(String[] args) {
		Sorting sorting = new Bubble(10000);
		sorting.process();
		
		Sorting sorting1 = new Select(10000);
		sorting1.process();
		
		Sorting sorting2 = new Insert(10000);
		sorting2.process();
		
		//Sorting sorting3 = new Merge(6);
		//sorting3.process();
	}
}
