public class AStar extends Alg {
	private Heur func;
	
	public AStar(Node start, Node goal, Node[][] highway, Heur func) {
		super(start, goal, highway);	
		this.func = func;
	}
	
	@Override
	public float hcost(Node n) {
		return func.heuristic(n);
	}

}