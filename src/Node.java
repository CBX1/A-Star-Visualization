public class Node implements Comparable<Node>{
	Position position;
	float gcost;
	float hcost;
	boolean isFringe;
	int value;
	Node[] succ = new Node[8];
	public Node parent;
	boolean explore;
	public Node(Position position) {
		super();
		this.position = position;
	}

	public Node() {
		this.position = null;
		this.gcost = 0;
		this.hcost = 0;
		this.isFringe = false;

		this.explore = false;
	}


	public Node(Node n) {
		this.position = n.position;
		this.gcost = n.gcost;
		this.hcost = n.hcost;
		this.isFringe = n.isFringe;
		this.explore = n.explore;
		this.parent = n.parent;
		this.value = n.value;
	}

	public Position getPosition() {
		return position;
	}




	@Override
	public String toString() {
		return "Node [" + position + ", gcost=" + gcost + ", hcost=" + hcost + ", value=" + value + "]";
	}

	public void setPosition(Position position) {
		this.position = position;
	}


	public float getGcost() {
		return gcost;
	}


	public void setGcost(float gcost) {
		this.gcost = gcost;
	}


	public float getHcost() {
		return hcost;
	}


	public void setHcost(float hcost) {
		this.hcost = hcost;
	}


	public boolean isFringe() {
		return isFringe;
	}


	public void setFringe(boolean isFringe) {
		this.isFringe = isFringe;
	}


	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}



	
	@Override
	public int compareTo(Node o) {
		if (gcost+hcost < o.gcost + o.hcost) {
			return -1;
		}
		else if (gcost + hcost > o.gcost + hcost) {
			return 1;
		}
		return 0;
	}

	
	public boolean equals(Node o) {
		if(o.position.row() == position.row() && o.position.col() == position.col()) {
			return true;
		}
		return false;
	
	}

	
}
