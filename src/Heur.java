
public abstract class Heur {
	Node[][] highway;
	Node fin;
	
	public Heur(Node[][] highway, Node fin) {
		this.highway = highway;
		this.fin = fin;
	}
	
	public abstract float heuristic(Node x);
}
