public class WAStar extends Alg {
	private Heur func;
	private float weight;
	
	public WAStar(Node start, Node goal, Node[][] highway, Heur func, float weight) {
		super(start,goal,highway);
		this.func = func;
		this.weight = weight;
		
	}
	
	@Override
	public float hcost(Node n) {
		return weight * func.heuristic(n);
	}

}