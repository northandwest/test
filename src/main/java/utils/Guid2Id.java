package utils;

import java.util.UUID;

public class Guid2Id {

	public static void main(String[] args) {
		String guid = UUID.randomUUID().toString();
		String guid2 = UUID.fromString(guid).toString();
		
		int hashCode = UUID.fromString(guid2).hashCode();
		System.out.println(guid);
		System.out.println(guid2);
		System.out.println(hashCode);

	}

}
