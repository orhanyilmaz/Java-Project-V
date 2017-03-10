public class Permutation {

	public static void permutation(String s) {
		permutation("", s);
	}

	private static void permutation(String prefix, String s) {
		int N = s.length();
		if (N == 0)
			Main.pathPermutation.add(prefix);
		else {
			for (int i = 0; i < N; i++)
				permutation(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, N));
		}

	}

}
