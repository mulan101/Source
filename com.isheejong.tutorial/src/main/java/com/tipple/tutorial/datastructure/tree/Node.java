package com.tipple.tutorial.datastructure.tree;
import java.util.ArrayList;
import java.util.List;


public class Node<T> {
	
	private List<T> values = new ArrayList<T>();
	private List<Node<T>> nodes = new ArrayList<Node<T>>();  
	
	public int add(T value){
		values.add(value);
		return values.size();
	}
	
	public void remove(int index){
		nodes.remove(index);
	}
}
