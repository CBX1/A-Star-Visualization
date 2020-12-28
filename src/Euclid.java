public class Euclid extends Heur {
	public Euclid(Node[][] highway, Node fin) {
		super(highway,fin);
	}
	
	public float heuristic(Node a) {
		return (float) Math.sqrt( (a.position.row() - fin.position.row())^2 +(a.position.col() - fin.position.col())^2  );
	}

}
