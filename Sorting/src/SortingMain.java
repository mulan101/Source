
import org.junit.Test;

import sorting.Bubble;
import sorting.Insert;
import sorting.Merge;
import sorting.Quick;
import sorting.Select;
import sorting.Sorting;


public class SortingMain {
	
	@Test
	public void bubble() {
		Sorting sorting = new Bubble(100000);
		sorting.process();
	}
	
	@Test
	public void select() {
		Sorting sorting = new Select(100000);
		sorting.process();
	}
	
	@Test
	public void insert() {
		Sorting sorting = new Insert(100000);
		sorting.process();
	}
	
	@Test
	public void merge() {
		//Sorting sorting = new Merge(100000);
		//sorting.process();
	}
	
	@Test
	public void quick() {
		Sorting sorting = new Quick(100000);
		sorting.process();
	}
}
