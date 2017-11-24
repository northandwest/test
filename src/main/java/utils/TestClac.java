package utils;

public class TestClac {

	public static void main(String[] args) {
		
		for( int i = 0; i < 50; i ++)
		{
			Double nums = 70d+i;
			
			tt(nums);
		}
	}

	private static void tt(Double nums) {
		Double double1 = nums * 0.05;
		Double floor = Math.ceil(double1);
		System.out.println(nums+"*0.05="+double1+"=>"+floor.intValue());
	}

}
