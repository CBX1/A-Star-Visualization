import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
public class Alg {
	Node[] path;
	private PriorityQueue<Node> fringe;
	private Node[][] aN;
	Node g;
	Node start;
	Node[][] highway;
	long startTime = System.nanoTime();
	long endtime = 0;
	public Alg(Node start, Node g, Node[][] highway) {
		this.fringe = new PriorityQueue<Node>(new Comparator<Node>() {
				public int compare(Node o1, Node o2) {
					return Float.compare(o1.gcost + o1.hcost, o2.gcost + o2.hcost);
				}
			});
		
		this.aN = new Node[120][160];
		this.start = start;
		this.g = g;
		this.path = null;
		this.highway = highway;
	}
	
	public Node[] exec() {
		Runtime runtime = Runtime.getRuntime();
		long before = runtime.totalMemory() - runtime.freeMemory();
		Node n = start;
		int count = 1;
		FringeAdd(n,null,gcost(start,start), hcost(start));
		while(!fringe.isEmpty()) {
			n = fringe.poll();
			succ(n); 
			count++;
			if(n.position.row() == g.position.row() && n.position.col() == g.position.col()) {
				long after = runtime.totalMemory() - runtime.freeMemory();
				// System.out.println("Memory: " + Math.abs(after-before));
				// System.out.println("Nodes Expanded:" + count);
				return revealPath();
			}
			for(int x =0; x < n.succ.length; x++) {
				Node nc = n.succ[x];
				if(nc != null) {
				float cC = n.gcost + gcost(n, nc);
				if(!nc.explore) {
					if(!nc.isFringe) {
						FringeAdd(nc, null, Integer.MAX_VALUE, 0);
					}
					if(cC < nc.gcost) {
						FringeAdd(nc, n, cC, hcost(nc));
					}
				}
				}
			}
		}
		return null;
	}

	private void succ(Node n) {
		
		int count = 0;
		int[][] directions = new int[][] {{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};
		for (int[] direction : directions) {
			 int col = n.position.col() + direction[0];
		     int row = n.position.row() + direction[1];
		     if((row >=0 && row < 120)) {
		    	 
		    	 if(col >= 0 && col < 160) {
		    		 
		 			if(highway[row][col].value != 4) {
		 				Node succ = highway[row][col];
		 				succ.gcost = (float) (n.gcost + gcost(n,highway[row][col]));	
		 				n.succ[count] = highway[row][col];
		 				count++;
		 				if(succ.position.row() ==119 && succ.position.col() == 159) {
		 				
		 				}
		 			}
		    	 }
		     }
		} 
		n.explore = true;
		
	}

	public float hcost(Node start2) {
		return 0;
	}

	public void FringeAdd(Node n, Node p, float gcost, float hcost) {
		n.gcost = gcost;
		n.hcost = hcost;
		n.parent = p;
		if(n.isFringe) {
			fringe.remove(n);
		}
		fringe.add(n);
		n.isFringe = true;
		this.aN[n.position.row()][n.position.col()]=n;
	}
	 public float gcost(Node parent, Node child) {
		 int current = parent.value;
		 int next = child.value;
		    boolean both_highway = false;
			  if( (child.value == parent.value && (child.value == 2 || child.value == 3)) || ( (child.value == 2 && parent.value ==3) || (child.value==3 && parent.value ==2) )) {
					both_highway= true;
				}
		 if(current == 2) {
			 current = 0;
		 }
		 if(next == 2) {
			 next = 0;
		 }
		 if(current == 3) {
			 current = 1;
		 }
		 if(next == 3) {
			 next = 1;
		 }
		 boolean diagonal = false;
		
 
		 if(current == 0 && next == 0 && diagonal == false) {
				if(both_highway) {
					return (float) .25;
				}
				return 1;
			}
			else if(current == 0 && next == 0 && diagonal == true) {
				return (float) Math.sqrt(2);
			}
			
			else if(current == 1 && next == 1 && diagonal == false) {
				if(both_highway) {
					return (float) .5;
				}
				return 2;
			}
			else if(current == 1 && next == 1 && diagonal == true) {
				return (float) Math.sqrt(8);
			}
			else if(( (current == 0 && next == 1) || (next == 0 && current == 1) ) && diagonal == false) {
				if(both_highway) {
					return (float) .375;
				}
				return (float) 1.5;
			}
			else if( ( (current == 0 && next == 1) || (next == 0 && current == 1) ) && diagonal == true) {
				return (float) ((Math.sqrt(2) + Math.sqrt(8))/2);
			}
	return 0;
	 }
	 
	 public Node[] revealPath() {
		 ArrayList<Node> pathlist = new ArrayList<Node>();
		 Node n = aN[g.position.row()][g.position.col()];
		 Node p = n;
		 while(n != null) {
			 p = n.parent;
			 pathlist.add(n);
			 n = p;
		 }
		pathlist.add(0, g);
		path = new Node[pathlist.size()];
		 path = pathlist.toArray(path);
		 float gcost = 0;
		 float hcost = 0;
			Node ae = path[0];
				for(int i = 0; i < path.length; i++) {
					Node ne = path[i];
					gcost += gcost(ne, ae);
					int o = -i + path.length -1;
					path[o].gcost = gcost;
					hcost += hcost(ne);
					path[o].hcost = hcost(path[o]);
					ae = ne;
					if(i == 0) {
						gcost = 0;
						path[o].gcost = 0;
					}
				
					
				}
						
		int count = 0;		
//		// System.out.println("gcost: " + gcost + "\n hcost: "+ hcost(g) );
		for(int x = path.length - 1; x > 0; x--) {
		//	// System.out.println("hi" + path[x]);
			count++;
		}
		endtime = System.nanoTime();
		// System.out.print("Time: " );
		// System.out.println(  .000000001* (endtime-startTime));
		// System.out.println("length: " + count);
		 return path;
	 }
}
