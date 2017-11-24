package com.bucuoa.father;

public class Son2 extends Father{
	
	public static void main(String[] args)
	{
		new Son2().print();
	}
	
	protected void print()
	{	
		super.name= "sun2";
		super.print();
//		System.out.println();
	}

}
