
public class CL extends Heur {

	public CL(Node[][] highway, Node fin) {
		super(highway, fin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float heuristic(Node x) {
		// TODO Auto-generated method stub
		return (float) .5 * Math.abs(x.position.col() - fin.position.col());
	}

}
