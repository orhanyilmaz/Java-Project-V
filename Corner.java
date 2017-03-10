class Corner {
	private final Vertex targetVertex;
	private final double distance;

	public Corner(Vertex targetVertex, double distance) {
		this.targetVertex = targetVertex;
		this.distance = distance;
	}

	public Vertex getTargetVertex() {
		return targetVertex;
	}

	public double getDistance() {
		return distance;
	}

}
