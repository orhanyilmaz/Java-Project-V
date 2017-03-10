import java.util.ArrayList;

class Vertex implements Comparable<Vertex> {
	private final String id;
	private Vertex pre;	
	private double distance = Double.POSITIVE_INFINITY;
	ArrayList<Corner> adj = new ArrayList<Corner>();
	
	public Vertex(String id) {
		this.id = id;		
	}

	public String toString() {
		return id;
	}

	public ArrayList<Corner> getAdjacencies() {
		return adj;
	}

	public void setAdjacencies(ArrayList<Corner> adj) {
		this.adj = adj;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Vertex getPre() {
		return pre;
	}

	public void setPre(Vertex pre) {
		this.pre = pre;
	}

	public String getId() {
		return id;
	}

	public int compareTo(Vertex other) {
		return Double.compare(distance, other.distance);
	}

}
