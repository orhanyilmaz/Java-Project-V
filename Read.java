import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Read {

	public void createVertexList(String path, ArrayList<Vertex> vertex) {
		try {
			int count = 0;
			int length = Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[length];
			for (String line : Files.readAllLines(Paths.get(path))) {
				results[count++] = line;
			}
			String[] word = results[0].split(",");
			String[] word3 = word[0].split("\\:");
			String[] word4 = word[1].split("\\:");
			Main.starterVertex = word3[1];
			Main.targetVertex = word4[1];
			for (int i = 1; i < length; i++) {
				word = results[i].split(" ");
				String[] word2 = word[0].split("\\.");
				vertex.add(new Vertex(word2[0])); // add all vertices in
													// ArrayList variable
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < vertex.size(); i++) {
			if (vertex.get(i).getId().equals(Main.starterVertex)) {
				Main.starterIndex = i; // get starterVertex index information
			}
			if (vertex.get(i).getId().equals(Main.targetVertex)) {
				Main.targetIndex = i; // get targetVertex index information
			}
		}
	}

	public void createAdjVertex(String path, ArrayList<Vertex> vertex) {
		try {
			int count = 0;
			int length = Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[length];
			for (String line : Files.readAllLines(Paths.get(path))) {
				results[count++] = line;
			}
			for (int i = 1; i < length; i++) {
				String[] word = results[i].split(" ");
				String[] word3 = word[1].split(",");
				if (word.length == 3) { // i understand that this vertex.get(i)
										// is mustpass vertex
					Main.mustPassIds.add(i - 1);
					Main.mustPassCount++;
				}
				for (int j = 0; j < word3.length; j++) {
					String[] word4 = word3[j].split("\\(");
					String[] word5 = word4[1].split("\\)");
					for (int k = 0; k < vertex.size(); k++) {
						if (vertex.get(k).getId().equals(word4[0])) {
							vertex.get(i - 1).adj.add(new Corner(vertex.get(k), Double.parseDouble(word5[0])));

						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
