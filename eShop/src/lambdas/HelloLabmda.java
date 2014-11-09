package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eshop.entity.Item;

public class HelloLabmda {
	public static final Item[] items = {
			new Item(42L, "Computer Mouse", "Logitech", "Accessoaries",
					"High quality optical mouse", 12.5, 20),

			new Item(15L, "Motherboard", "ASUS", "Motherboards",
					"AMD Athlon II x4 A03 motherboard", 125.7, 15),

			new Item(3L, "Windows 8", "Microsoft", "Software",
					"Popular desktop OS", 125.7, 15),

			new Item(105L, "External Hard Disk", "Seagate", "Hard Disks",
					"Good archiving solution for small office", 125.7, 15),

			new Item(9L, "Motherboard", "ASUS", "Motherboards",
					"X99-SOC Force", 125.7, 15),

	};

	public static void main(String[] args) {
		List<Item> il = Arrays.asList(items);
		il.stream().forEachOrdered(s -> System.out.println(s));

		// Old way:
		System.out.println("\nOld way:");
		List<Integer> list = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		for (Integer n : list) {
			int x = n * n;
			System.out.println(x);
		}

		// New way:
		System.out.println("\nJava 8 way:");
		List<Integer> list1 = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		list.stream().sorted((i, j) -> j - i).map((x) -> x * x)
				.forEach(System.out::println);

		// Old way:
		System.out.println("\nOld way:");
		List<Integer> list2 = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		int sum2 = 0;
		for (Integer n : list2) {
			int x = n * n;
			sum2 = sum2 + x;
		}
		System.out.println(sum2);

		// New way:
		System.out.println("\nJava 8 way:");
		List<Integer> list3 = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		int sum3 = list3.stream().map(x -> x * x).reduce((x, y) -> x + y).get();
		System.out.println(sum3);

	}

}
