import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static String starterVertex;
	static String targetVertex;
	static int starterIndex = -1;
	static int targetIndex = -1;
	static int mustPassCount = 0;
	static ArrayList<Integer> mustPassIds = new ArrayList<Integer>();
	static ArrayList<Vertex> vertex = new ArrayList<Vertex>();
	static DijkstraForShortestPath dijkstraForShortestPath = new DijkstraForShortestPath();
	static Read read = new Read();
	static ArrayList<String> pathPermutation = new ArrayList<String>();
	static ArrayList<Integer> mustPassIdsFormOfInteger = new ArrayList<Integer>();

	public static void main(String[] args) {

		try {
			File file = new File(args[1]);
			FileWriter writer = new FileWriter(file);
			BufferedWriter write = new BufferedWriter(writer);

			read.createVertexList(args[0], vertex);
			read.createAdjVertex(args[0], vertex);
			write.write("21328595");
			write.newLine();
			write.newLine();
			write.write("Shortest Path:	");
			dijkstraForShortestPath.createPaths(vertex.get(starterIndex));
			write.write("Distance=" + String.format("%.6f", vertex.get(targetIndex).getDistance()));
			ArrayList<Vertex> shortestPath = dijkstraForShortestPath.shortestPath(vertex.get(targetIndex));
			write.write("	Path=(");
			for (int i = 0; i < shortestPath.size(); i++)
				write.write(shortestPath.get(i) + " ");
			write.write(")");
			write.newLine();
			write.newLine();

			vertex.clear();
			mustPassCount = 0;
			mustPassIds.clear();
			read.createVertexList(args[0], vertex);
			read.createAdjVertex(args[0], vertex);

			String allPermutations = "";
			for (int i = 0; i < mustPassCount; i++) {
				allPermutations = allPermutations + String.valueOf(i);
			}
			String paths = allPermutations.substring(0, mustPassCount);
			Permutation.permutation(paths);

			Double[] pathDistance = new Double[pathPermutation.size()];
			String[] mustPassPath = new String[pathPermutation.size()];
			for (int i = 0; i < pathPermutation.size(); i++) {
				pathDistance[i] = 0.0;
			}
			for (int i = 0; i < pathPermutation.size(); i++) {
				String[] word = pathPermutation.get(i).split("");
				for (int j = 0; j < word.length; j++) {
					mustPassIdsFormOfInteger.add(Integer.parseInt(word[j]));
				}

				dijkstraForShortestPath.createPaths(vertex.get(starterIndex));
				ArrayList<Vertex> shortestPath2 = dijkstraForShortestPath
						.shortestPath(vertex.get(mustPassIds.get(mustPassIdsFormOfInteger.get(0))));

				for (int k = 0; k < shortestPath2.size(); k++) {
					if (mustPassPath[i] != null) {
						if (k == shortestPath2.size() - 1) {
							;
						} else {
							mustPassPath[i] = mustPassPath[i] + " " + shortestPath2.get(k) + " ";
						}
					} else {
						mustPassPath[i] = shortestPath2.get(k) + " ";
					}
				}
				pathDistance[i] = pathDistance[i]
						+ vertex.get(mustPassIds.get(mustPassIdsFormOfInteger.get(0))).getDistance();

				vertex.clear();
				mustPassCount = 0;
				mustPassIds.clear();
				read.createVertexList(args[0], vertex);
				read.createAdjVertex(args[0], vertex);

				for (int j = 0; j < word.length - 1; j++) {
					dijkstraForShortestPath.createPaths(vertex.get(mustPassIds.get(mustPassIdsFormOfInteger.get(j))));

					ArrayList<Vertex> shortestPath3 = dijkstraForShortestPath
							.shortestPath(vertex.get(mustPassIds.get(mustPassIdsFormOfInteger.get(j + 1))));

					for (int k = 0; k < shortestPath3.size(); k++) {
						if (k == shortestPath3.size() - 1) {
							;
						} else if (k == 0) {
							mustPassPath[i] = mustPassPath[i] + shortestPath3.get(k) + "(mustpass)" + " ";
						} else {
							mustPassPath[i] = mustPassPath[i] + " " + shortestPath3.get(k) + " ";
						}

					}

					pathDistance[i] = pathDistance[i]
							+ vertex.get(mustPassIds.get(mustPassIdsFormOfInteger.get(j + 1))).getDistance();

					vertex.clear();
					mustPassCount = 0;
					mustPassIds.clear();
					read.createVertexList(args[0], vertex);
					read.createAdjVertex(args[0], vertex);
				}

				dijkstraForShortestPath.createPaths(
						vertex.get(mustPassIds.get(mustPassIdsFormOfInteger.get(mustPassIdsFormOfInteger.size() - 1))));

				ArrayList<Vertex> shortestPath4 = dijkstraForShortestPath.shortestPath(vertex.get(targetIndex));

				for (int k = 0; k < shortestPath4.size(); k++) {
					if (k == 0) {
						mustPassPath[i] = mustPassPath[i] + shortestPath4.get(k) + "(mustpass)" + " ";
					} else {
						mustPassPath[i] = mustPassPath[i] + " " + shortestPath4.get(k) + " ";
					}
				}

				pathDistance[i] = pathDistance[i] + vertex.get(targetIndex).getDistance();

				vertex.clear();
				mustPassCount = 0;
				mustPassIds.clear();
				read.createVertexList(args[0], vertex);
				read.createAdjVertex(args[0], vertex);

				mustPassIdsFormOfInteger.clear();
			}

			for (int i = 0; i < pathPermutation.size(); i++) {
				for (int j = i + 1; j < pathPermutation.size(); j++) {
					if (pathDistance[j] < pathDistance[i]) {
						pathDistance[i] = pathDistance[j];
						mustPassPath[i] = mustPassPath[j];
					}
				}
			}
			write.write("Constrained Shortest Path:	");
			write.write("Distance=" + String.format("%.6f", pathDistance[0]));
			write.write("	Path=(" + mustPassPath[0] + " )");
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
