
class Test1 extends Object{
	public static void main(String[] args) {
		int ia1[] = { 1, 2 };
		int ia2[] = ia1.clone();
		System.out.println((ia1 == ia2) + " ");
		ia1[1]++;
		System.out.println(ia1[1]);
		System.out.println(ia2[1]);
	}
}
