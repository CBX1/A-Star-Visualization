public class DD extends Heur{
	//Diagonal Distance
	public DD(Node[][] highway, Node fin) {
		super(highway,fin);
	}
	public float heuristic(Node a) {
		return (float) 0.25 * Math.max(Math.abs(a.position.row()-fin.position.row()), Math.abs(a.position.col()-fin.position.col()));
	}

}
