public class MDH extends Heur {
//manhattan distance
public MDH(Node[][] highway, Node fin) {
	super(highway,fin);
}

	public float heuristic(Node a) {
		return (float) (Math.abs(a.position.row() - fin.position.row()) + Math.abs(a.position.col() - fin.position.col()));
	}
}