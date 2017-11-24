package com.bucuoa.father;

public class Son extends Father{
	
	public static void main(String[] args)
	{
		new Son().print();
	}
	
	protected void print()
	{	
		super.name= "sun";
		super.print();
//		System.out.println();
	}

}
