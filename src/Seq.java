	
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Seq {
	int rowval;
	int colval;
	Node start;
	Node fin;
	Node[] path;
	Heur[] harr;
	int hlen;
	float w1;
	float w2;
	Node[][] highway;
	HashMap<Node, Node[]> bp;
	ArrayList<PriorityQueue<Node>> open;
	long startTime = System.nanoTime();
	long endTime = 0;
	int count = 0;
	Runtime runtime = Runtime.getRuntime();
    long before = runtime.totalMemory() - runtime.freeMemory();
	public Seq (Node start, Node fin,
			Node[][] highway, Heur[] harr, float w1, float w2) {
		this.rowval =120;
		this.colval =160;
		this.start = start; this.fin = fin;
		this.highway = highway;
		this.harr = harr;
		this.hlen = harr.length;
		this.bp = new HashMap<Node, Node[]>();
		this.open = new ArrayList<PriorityQueue<Node>>(hlen);
		this.w1 = w1;
		this.w2 = w2;
	}
	public Node[] exec() {
		
	    int o = 0;
	    while(o < hlen) {
	    	open.add(new PriorityQueue<Node>( new Comparator<Node>() {
				public int compare(Node o1, Node o2) {
					return Float.compare(o1.gcost + o1.hcost, o2.gcost + o2.hcost);
				}
			}));
			insert(start, null, gcost(start, start), hcost(start, o), o);
			insert(fin, null, Integer.MAX_VALUE, 0, o);
	    	o++;
	    }
		PriorityQueue<Node> heur = open.get(0);
		while(minKey(heur) < Integer.MAX_VALUE) { 			
			for(int i = 1; i < hlen; i++) {
				PriorityQueue<Node> unadHeur = open.get(i);
				if(unadHeur.peek() != null && (minKey(unadHeur) <= w2*minKey(heur))) {
					if(bp.get(fin)[i].gcost <= minKey(unadHeur)) {
						if(bp.get(fin)[i].gcost < Integer.MAX_VALUE) {
							long After = runtime.totalMemory() - runtime.freeMemory();
						    // System.out.println("Memory: " + (After-before));
							return path(i);
						}
					} 
					else {
						count++;
						expandState(unadHeur.poll(), i);
					}

				} else {
					if(bp.get(fin)[0].gcost <= minKey(heur)) {
						if(bp.get(fin)[0].gcost < Integer.MAX_VALUE) {
							long After = runtime.totalMemory() - runtime.freeMemory();
						    // System.out.println("Memory: " + (After-before));
							return path(0);
						}
					} else {
						count++;
						expandState(heur.poll(), 0);
					}

				}
			}

		}

		return null;
	}
	
	
	public float minKey(PriorityQueue<Node> fringe) {
		return fringe.peek().hcost + fringe.peek().gcost;
	}

	public void expandState(Node n, int hInd) {
		int count = 0;
		int[][] directions = new int[][] {{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};

		for (int[] direction : directions) {
			 int col = n.position.col() + direction[0];
		     int row = n.position.row() + direction[1];
		     if((row >=0 && row < rowval)) {
		    	 if(col >= 0 && col < colval) {
		 			if(highway[row][col].value != 4) {
		 				Node succ = highway[row][col];
		 				succ.gcost = (float) (n.gcost + gcost(n,highway[row][col]));	
		 				n.succ[count] = highway[row][col];
		 				highway[row][col].parent = n;
		 				count++;
		 			}
		    	 }
		     }
		}
		Node[] sub = new Node[count];
		for(int i = 0; i < sub.length; i ++) {
			sub[i] = n.succ[i];
		}
		n.succ = sub;
		highway[n.position.row()][n.position.col()].succ = sub;
		
		for(int i = 0; i < n.succ.length; i++) {
			Node child = n.succ[i];
			if(bp.get(child) == null) {
				int c = 0;
				Node[] arr = new Node[hlen];
				while(c < hlen) {
					Node no = new Node(child);
					no.gcost = Integer.MAX_VALUE;
					no.hcost = 0;
					arr[c] = no;
					c++;
				}
				bp.put(child, arr);
			}
			child = bp.get(child)[hInd];
			float cC = n.gcost + gcost(n,child);
			if(child.gcost > cC) {
				child.gcost = cC;
				if(!child.explore) {
					insert(child, n, gcost(n, child), hcost(child, hInd), hInd);
					
				}
			}
			
		}
		n.explore = true;
	}
	
	
	public void insert(Node n, Node p, float gCost, float hCost, int Num) {
		PriorityQueue<Node> fringe = open.get(Num);
		if(n != null && bp.get(n) != null) {
			if(bp.get(n)[Num].isFringe) {
				open.get(Num).remove(n);
			}
		}
		n.gcost = gCost;
		n.hcost = hCost;
		n.parent = p;
		n.isFringe = true; 
		fringe.add(n);
		Node[] bparr  = bp.get(n);
		if(bparr == null) {
			int c = 0;
			Node[] arr = new Node[hlen];
			while(c < hlen) {
				Node pop = new Node(n);
				pop.gcost = Integer.MAX_VALUE;
				pop.hcost = 0;
				arr[c] = pop;
				c++;
			}
			bp.put(n, arr);
			bparr = bp.get(n);
		}
		bparr[Num] = n;
		
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
	
	 public float hcost(Node n, int heuristicNum) {
			return w1*harr[heuristicNum].heuristic(n);
	}
	 
	 
	 
	 public Node[] path(int ind) {
			ArrayList<Node> l = new ArrayList<Node>();		
			Node n = bp.get(fin)[ind];
			Node p = bp.get(fin)[ind];
			while(n != start) {
					p = n.parent;
					l.add(n);
					n = p;
			}
			
			path = new Node[l.size()];
			l.add(0,start);
			float gcost = 0;
			path = l.toArray(path);
			Node ae = path[0];
				for(int i = 0; i < path.length; i ++) {
					Node ne = path[i];
					gcost += gcost(ne, ae);
					int o = -i + path.length -1;
					path[o].gcost = gcost;
					path[o].hcost = hcost(path[o],ind);
					ae = ne;
					if(i == 0) {
						gcost = 0;
						path[o].gcost = 0;
					}
			}
			endTime = System.nanoTime();
			// System.out.print("Time: ");
			// System.out.println(  .000000001* (endTime-startTime));
			// System.out.println("Nodes expanded: "+ count);
			// System.out.println("length: " + path.length);
			return path;
		}
	 
	 
}
