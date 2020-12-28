
public class RH extends Heur {
	public RH(Node[][] highway, Node fin) {
		super(highway, fin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float heuristic(Node x) {
		// TODO Auto-generated method stub
		return .25f * Math.abs(x.position.row() - fin.position.row());
	}

}
