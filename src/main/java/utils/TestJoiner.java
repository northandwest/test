package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJoiner {

	public static void main(String[] args) {
		List<String> t2 = new ArrayList<String>();
		t2.add("1");
		t2.add("2");
		t2.add("3");
		
		String[] t =  new String[t2.size()];
		t2.toArray(t);
		
		Joiner joiner = Joiner.on(",");
		String join = joiner.join(t);
		System.out.println(join);
		
		String join2 = Joiner.on(",").join(t2);
		System.out.println(join2);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "c");
		
		String join3 = Joiner.on(",").join(map);
		System.out.println(join3);
		
		Joiner joinerk = Joiner.on(",");
		joinerk.withKeyValueSeparator("->");
		String join4 =	joinerk.join(map);
		System.out.println(join4);
	}

}
