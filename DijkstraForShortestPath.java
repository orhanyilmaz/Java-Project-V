import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

public class DijkstraForShortestPath {

	public void createPaths(Vertex starterVertex) {
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();

		double distanceOfVertex = 0; // initialize distance of a vertex variable
		double distanceToVertex = 0; // initialize distance of path variable
		Vertex vertexV; // create vertex for traversal
		Vertex vertexW; // create vertex for traversal
		starterVertex.setDistance(0); // set distance 0 for starterVertex
		vertexQueue.add(starterVertex); // add starterVertex in vertexQueue
		
		
		while (!vertexQueue.isEmpty()) {
			vertexW = vertexQueue.poll(); // Retrieves and removes the head of VertexQueue 
			
			for (int i = 0; i < vertexW.adj.size(); i++) { // traversal for all
															// corners
				vertexV = vertexW.adj.get(i).getTargetVertex();
				distanceOfVertex = vertexW.adj.get(i).getDistance();
				distanceToVertex = vertexW.getDistance() + distanceOfVertex;
				if (distanceToVertex < vertexV.getDistance()) {					
					vertexV.setDistance(distanceToVertex);
					vertexV.setPre(vertexW);
					vertexQueue.add(vertexV);
				}				
			}
		}
	}
	

	public ArrayList<Vertex> shortestPath(Vertex targetVertex) {

		ArrayList<Vertex> shortestPath = new ArrayList<Vertex>();
		Vertex vertexForTraversal;

		for (vertexForTraversal = targetVertex; vertexForTraversal != null; vertexForTraversal = vertexForTraversal.getPre()) {
			shortestPath.add(vertexForTraversal);
		}

		Collections.reverse(shortestPath); // this methods listing the
											// shortestPath reversely
		return shortestPath;
	}
}
