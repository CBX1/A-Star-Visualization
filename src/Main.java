import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main{
	static JFrame frame;
	 static int[][] squareArray;
	static JPanel uc;
	static JPanel aEu;
	
	 
public static void main(String[] args) {

	
	Node startt = null;
	Node goal = null;
	// System.out.println("start");
//
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    squareArray = new int[120][160];
	Node[][] highway = new Node[120][160];
	Node[][] euclid = new Node[120][160];
	Node[][] mdhH = new Node[120][160];
	Node[][] wStar1 = new Node[120][160];
	Node[][] wStar2 = new Node[120][160];
	Node[][] seq1 =new Node[120][160];
	Node[][] seq2 = new Node[120][160];
	Node[][] rhH = new Node[120][160];
	Node[][] chH = new Node[120][160];
	Node[][] ddH = new Node[120][160];
	Node[][] copy = new Node[120][160];
	Random rand = new Random();
		for(int x = 0; x < highway.length; x++ ) {
			for(int y = 0; y < highway[0].length; y++) {
				Node n = new Node(new Position(x,y));
				Node n2 = new Node(new Position(x,y));
			
				n.value = rand.nextInt(2);
				n2.value = n.value;
				highway[x][y]= n;
				copy[x][y] = n2;
				
			}
		}
		int z = 0;
		int zz = 0;
		int x =  rand.nextInt(4);
		for(int y = 0; y < 4; y++) {
			if(x==0) {
				highway=right(highway,'r');
			}
			else if(x==1) {
				highway = right(highway,'u');
			}
			else if(x==2) {
				highway = right(highway,'l');
			}
			else if(x==3) {
				highway = right(highway,'d');
			}
			if(highway == null){
				highway = new Node[120][160];
				for(int q = 0; q < copy.length; q++) {
					for(int p = 0; p < copy[0].length; p++) {
						Node ne = new Node();
						ne.position = new Position(q,p);
						ne.value = copy[q][p].value;
						highway[q][p] = ne;
					}
				}
				y--;
			}
			else {
				for(int q = 0; q < copy.length; q++) {
					for(int p = 0; p < copy[0].length; p++) {
						Node ne = new Node();
						ne.position = new Position(q,p);
						ne.value = highway[q][p].value;
						copy[q][p] = ne;
					}
				}
				
			}
			
		
		}
		int block = 0;
		Random oo = new Random();
		while(block <  3839) {
			int row = oo.nextInt(120);
			int col = oo.nextInt(160);
			if(highway[row][col].value < 2) {
				highway[row][col].value = 4;
				block++;
			}
		}
		boolean start = false;
		int row = 0;
		int col = 0;
		//// System.out.println("ddd");
		while(!start) {
			int rowprob = oo.nextInt(2);
			row = 0;
			if(rowprob == 1) {
				 row = oo.nextInt(119-99 + 1) + 99;
			}
			else {
				row = oo.nextInt(20);
			}
			int colprob = oo.nextInt(2);
			col = 0;
			if(colprob == 1) {
				 col = oo.nextInt(159-139 + 1) + 139;
			}
			else {
				col = oo.nextInt(20);
			}
			
			if(highway[row][col].value != 4){
				startt = highway[row][col];
				startt.position = new Position(row,col);
				start = true;
			}
		}
		
		boolean finish =false;
		
		while(!finish) {
			int rowprob = oo.nextInt(2);
			row = 0;
			if(rowprob == 1) {
				 row = oo.nextInt(119-99 + 1) + 99;
			}
			else {
				row = oo.nextInt(20);
			}
			int colprob = oo.nextInt(2);
			col = 0;
			if(colprob == 1) {
				 col = oo.nextInt(159-139 + 1) + 139;
			}
			else {
				col = oo.nextInt(20);
			}
			int f = (startt.position.row() - row) * (startt.position.row() - row);
			int y = (startt.position.col()-col) * (startt.position.col()-col);

			if(highway[row][col].value != 4){
				if( f + y >= 10000) {
				goal = highway[row][col];
				goal.position = new Position(row,col);
				finish = true;
				}
			}
		}
		for(int xx = 0; xx < euclid.length; xx++) {
			for(int yy = 0; yy < euclid[0].length; yy++) {
				Node n = new Node(highway[xx][yy]);
				Node n1 = new Node(highway[xx][yy]);
				
				
				Node star1 = new Node(highway[xx][yy]);
				star1.position = new Position(xx,yy);
				star1.value = n.value;
				wStar1[xx][yy] = new Node(star1);
				
				Node star2 = new Node(highway[xx][yy]);
				star2.position = new Position(xx,yy);
				star2.value = n.value;
				wStar2[xx][yy] = new Node(star2);
				
				Node s1 = new Node(highway[xx][yy]);
				s1.position = new Position(xx,yy);
				s1.value = n.value;
				seq1[xx][yy] = new Node(s1);
				
				
				Node s2 = new Node(highway[xx][yy]);
				s2.position = new Position(xx,yy);
				s2.value = n.value;
				seq2[xx][yy] = new Node(s2);
				
				Node rowheur = new Node(highway[xx][yy]);
				rowheur.position = new Position(xx,yy);
				rowheur.value = n.value;
				rhH[xx][yy] = new Node(rowheur);
				
				
				Node colheur = new Node(highway[xx][yy]);
				colheur.position = new Position(xx,yy);
				colheur.value = n.value;
				chH[xx][yy] = new Node(colheur);
				
				
				Node dH = new Node(highway[xx][yy]);
				dH.position = new Position(xx,yy);
				dH.value = n.value;
				ddH[xx][yy] = new Node(dH);
				
				
				n1.position = new Position(xx,yy);
				n1.value = n.value;
				n.position = new Position(xx,yy);
				n.value = highway[xx][yy].value;
				euclid[xx][yy] = new Node(n);
				mdhH[xx][yy] = new Node(n1);
			}
		}
		
		Node wstar1s = new Node(startt);
		Node wstar1g = new Node(goal);
		
		Node wstar2s = new Node(startt);
		Node wstar2g = new Node(goal);
		
		Node seq1s = new Node(startt);
		Node seq1g = new Node(goal);
		
		Node seq2s = new Node(startt);
		Node seq2g = new Node(goal);
		
		Node rhHs = new Node(startt);
		Node rhHg = new Node(goal);
		
		Node chHs = new Node(startt);
		Node chHg = new Node(goal);
		
		Node ddHs = new Node(startt);
		Node ddHg = new Node(goal);
		
		Node ns = new Node(startt);
		Node gs = new Node(goal);
		UCS as = new UCS(startt, goal, highway);
		Node gg = new Node(startt);
		Node gp = new Node(goal);
		// System.out.println("Uniform Cost Search");
		Node[] run1 = as.exec();
		for(int n = run1.length-1; n > 0; n--) {
			highway[run1[n].position.row()][run1[n].position.col()].value = 6;
		}	
// System.out.println("Euclidean Heuristic");
	    Euclid ee = new Euclid(euclid,euclid[gs.position.row()][gs.position.col()]);
	    AStar ac = new AStar(euclid[ns.position.row()][ns.position.col()], euclid[gs.position.row()][gs.position.col()],euclid,ee);
		Node[] eee = ac.exec();
		for(int p = eee.length-1; p > 0; p--) {
			euclid[eee[p].position.row()][eee[p].position.col()].value = 6;
		}
		
		// System.out.println("Manhattan Distance Heuristic");
		MDH mm = new MDH(mdhH, mdhH[gg.position.row()][gg.position.col()]);
		 AStar ad = new AStar(mdhH[gg.position.row()][gg.position.col()], euclid[gp.position.row()][gp.position.col()],mdhH,mm);		
		Node[] ef =  ad.exec();
		for(int p = ef.length-1; p > 0; p--) {
			mdhH[ef[p].position.row()][ef[p].position.col()].value = 6;
		}
		
		
		// System.out.println("Row Heuristic");
		RH r = new RH(rhH, rhHg);
		AStar ro = new AStar(rhH[rhHs.position.row()][rhHs.position.col()], rhH[rhHg.position.row()][rhHg.position.col()], rhH,r);
		Node[] rhHa = ro.exec();
		for(int p = rhHa.length-1; p > 0; p--) {
			rhH[rhHa[p].position.row()][rhHa[p].position.col()].value = 6;
		}

		// System.out.println("Column Heuristic");
		CL colu = new CL(chH, chHg);
		AStar ca = new AStar(chH[chHs.position.row()][chHs.position.col()], chH[chHg.position.row()][chHg.position.col()], chH,colu);
		Node[] chHa = ca.exec();
		for(int p = chHa.length-1; p > 0; p--) {
			chH[chHa[p].position.row()][chHa[p].position.col()].value = 6;
		}
		
		
		// System.out.println("Diagonal Heuristic");
		DD dia = new DD(ddH, ddHg);
		AStar rod = new AStar(ddH[ddHs.position.row()][ddHs.position.col()], ddH[ddHg.position.row()][ddHg.position.col()], ddH,dia);
		Node[] ddHa = rod.exec();
		for(int p = ddHa.length-1; p > 0; p--) {
			ddH[ddHa[p].position.row()][ddHa[p].position.col()].value = 6;
		}

		
		// System.out.println("Weighted Heuristic Weight 1 ");
		RH wa1 = new RH(wStar1,  wstar1g);
		WAStar ww = new WAStar(wStar1[wstar1s.position.row()][wstar1s.position.col()], wStar1[wstar1g.position.row()][wstar1g.position.col()], wStar1, dia,1);
		Node[] weee = ww.exec();
		for(int p = weee.length-1; p > 0; p--) {
			wStar1[weee[p].position.row()][weee[p].position.col()].value = 6;
		}
		
		// System.out.println("Weighted Heuristic Weight 2 ");
		WAStar ww2 = new WAStar(wStar2[wstar2s.position.row()][wstar2s.position.col()], wStar2[wstar2g.position.row()][wstar2g.position.col()], wStar2, dia,2);
		Node[] weh = ww2.exec();
		for(int p = weh.length-1; p > 0; p--) {
			rhH[weh[p].position.row()][weh[p].position.col()].value = 6;
		}

		
		// System.out.println("Sequential Search (1,2)");
		Heur[] arr = new Heur[5];
		arr[0] = new DD(seq1,seq1g);
		arr[1] = new CL(seq1,seq1g);
		arr[2] = new RH(seq1,seq1g);
		arr[3] = new Euclid(seq1,seq1g);
		arr[4] = new MDH(seq1,seq1g);
		
		Seq seqsf1 = new Seq(seq1[seq1s.position.row()][seq1s.position.col()], seq1[seq1g.position.row()][seq1g.position.col()], seq1, arr, 1, 2);
		Node[] qw = seqsf1.exec();
		// System.out.println("hh");
		for(int p = qw.length-1; p > 0; p--) {
			seq1[qw[p].position.row()][qw[p].position.col()].value = 6;
		
		}
		
		// System.out.println("Sequential Search (2,3)");
		
		Seq seqsf2 = new Seq(seq2[seq2s.position.row()][seq2s.position.col()], seq2[seq2g.position.row()][seq2g.position.col()], seq2, arr, 2, 3);
		Node[] qw2 = seqsf2.exec();
		// System.out.println("hh");
		for(int p = qw2.length-1; p > 0; p--) {
			seq2[qw2[p].position.row()][qw2[p].position.col()].value = 6;
		}
//
//		
//		
	 JFrame frame = new JFrame("Uniform Cost Search");
	   
  frame.setSize(screenSize.width, screenSize.height);
//  
//  
 		uc = new JPanel(new GridLayout(120, 160, 2, 2));
    aEu = new JPanel(new GridLayout(120, 160, 2, 2));
    JPanel mdh = new JPanel(new GridLayout(120, 160, 2, 2));
    JPanel jRow = new JPanel(new GridLayout(120, 160, 2, 2));	
    JPanel jCol = new JPanel(new GridLayout(120, 160, 2, 2));
    JPanel jDiag = new JPanel(new GridLayout(120, 160, 2, 2));
    JPanel weigstar1 = new JPanel(new GridLayout(120, 160, 2, 2));
    JPanel weigstar2 = new JPanel(new GridLayout(120, 160, 2, 2));
    JPanel seqse1 = new JPanel(new GridLayout(120, 160, 2, 2));
    JPanel seqse2 = new JPanel(new GridLayout(120, 160, 2, 2));
    
    
    set(squareArray,mdhH,mdh, Color.ORANGE,startt,goal);
	set(squareArray, highway,uc, Color.green, startt, goal);
  	set(squareArray, euclid,aEu, Color.pink,startt,goal);
  	set(squareArray, ddH,jDiag, Color.MAGENTA,startt,goal);
  	set(squareArray, chH,jCol, Color.red,startt,goal);
  	set(squareArray, rhH,jRow, Color.red,startt,goal);
  	set(squareArray, wStar1,weigstar1, Color.green,startt,goal);
  	set(squareArray, wStar2,weigstar2, Color.orange,startt,goal);
  	set(squareArray, seq1,seqse1, Color.MAGENTA,startt,goal);
  	set(squareArray, seq2,seqse2, Color.pink,startt,goal);
  	     
//     
   
    JMenuBar menubar = new JMenuBar();
    frame.setJMenuBar(menubar);
    JMenu file = new JMenu("Search");
    JMenuItem exit = new JMenuItem("Euclidean");
    JMenuItem ucs = new JMenuItem("Uniform Cost Search");
    JMenuItem mdhh = new JMenuItem("Manhattan Distance");
    JMenuItem dd = new JMenuItem("Diagonal Distance");
    JMenuItem rh = new JMenuItem("Row Distance");
    JMenuItem ch = new JMenuItem("Column Distance");
    JMenuItem sq = new JMenuItem("Sequential (1,2) ");
    JMenuItem sq1 = new JMenuItem("Sequential (2,3) ");
    JMenuItem wa11 = new JMenuItem("Weighted AStar (1,1)");
    JMenuItem wa2 = new JMenuItem("Weighted AStar (2,2)");
    
	
	class eeee implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Euclidean");
			   uc.setVisible(false);
			   mdh.setVisible(false);
			   jCol.setVisible(false);
			   jDiag.setVisible(false);
			   weigstar1.setVisible(false);
			   weigstar2.setVisible(false);
			   seqse1.setVisible(false);
			   seqse2.setVisible(false);
			   jRow.setVisible(false);
			   seqse2.setVisible(true);
		   aEu.setVisible(true);
		   frame.getContentPane().add(aEu);
		}
	}
	

	
	class ucs implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Uniform Cost Search");
			   mdh.setVisible(false);
			   jCol.setVisible(false);
			   jDiag.setVisible(false);
			   weigstar1.setVisible(false);
			   weigstar2.setVisible(false);
			   seqse1.setVisible(false);
			   seqse2.setVisible(false);
			   aEu.setVisible(false);
			   jRow.setVisible(false);
			   seqse2.setVisible(true);
		    uc.setVisible(true);
		    frame.getContentPane().add(uc);
		    
		}
	}
	
	class mandh implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Manhattan Distance");
			   uc.setVisible(false);
			   jCol.setVisible(false);
			   jDiag.setVisible(false);
			   weigstar1.setVisible(false);
			   weigstar2.setVisible(false);
			   seqse1.setVisible(false);
			   seqse2.setVisible(false);
			   aEu.setVisible(false);
			   jRow.setVisible(false);
			   seqse2.setVisible(true);
		    mdh.setVisible(true);
		    frame.getContentPane().add(mdh);
		    
		}
	}
	
	
	
	class jR implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Row");
		   uc.setVisible(false);
		   mdh.setVisible(false);
		   jCol.setVisible(false);
		   jDiag.setVisible(false);
		   weigstar1.setVisible(false);
		   weigstar2.setVisible(false);
		   seqse1.setVisible(false);
		   seqse2.setVisible(false);
		   aEu.setVisible(false);
		   jRow.setVisible(true);
		   frame.getContentPane().add(jRow);
		}
	}

	rh.addActionListener(new jR());

	class jC implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Column");
		   uc.setVisible(false);
		   mdh.setVisible(false);
		   jDiag.setVisible(false);
		   weigstar1.setVisible(false);
		   weigstar2.setVisible(false);
		   seqse1.setVisible(false);
		   seqse2.setVisible(false);
		   aEu.setVisible(false);
		   jRow.setVisible(false);
		   jCol.setVisible(true);
		   frame.getContentPane().add(jCol);
		}
	}

	ch.addActionListener(new jC());
	
	
	class jD implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Diagonal");
		   uc.setVisible(false);
		   mdh.setVisible(false);
		   jCol.setVisible(false);
		   weigstar1.setVisible(false);
		   weigstar2.setVisible(false);
		   seqse1.setVisible(false);
		   seqse2.setVisible(false);
		   aEu.setVisible(false);
		   jRow.setVisible(false);
		   jDiag.setVisible(true);
		   frame.getContentPane().add(jDiag);
		}
	}
	
	dd.addActionListener(new jD());
	
	class jSo implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Sequential Search 1 2");
		   uc.setVisible(false);
		   mdh.setVisible(false);
		   jCol.setVisible(false);
		   jDiag.setVisible(false);
		   weigstar1.setVisible(false);
		   weigstar2.setVisible(false);
		   seqse2.setVisible(false);
		   aEu.setVisible(false);
		   jRow.setVisible(false);
		   seqse1.setVisible(true);
		   frame.getContentPane().add(seqse1);
		}
	}

	sq.addActionListener(new jSo());

	
	class jSt implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Sequential Search 2 3");
		   uc.setVisible(false);
		   mdh.setVisible(false);
		   jCol.setVisible(false);
		   jDiag.setVisible(false);
		   weigstar1.setVisible(false);
		   weigstar2.setVisible(false);
		   seqse1.setVisible(false);
		   seqse2.setVisible(false);
		   aEu.setVisible(false);
		   jRow.setVisible(false);
		   seqse2.setVisible(true);
		   frame.getContentPane().add(seqse2);
		}
	}

	sq1.addActionListener(new jSt());
	
	
	class wAo implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Weighted A* 1");
		   uc.setVisible(false);
		   mdh.setVisible(false);
		   jCol.setVisible(false);
		   jDiag.setVisible(false);
		   weigstar2.setVisible(false);
		   seqse1.setVisible(false);
		   seqse2.setVisible(false);
		   aEu.setVisible(false);
		   jRow.setVisible(true);
		   weigstar1.setVisible(true);
		   frame.getContentPane().add(weigstar1);
		}
	}

	
	wa11.addActionListener(new wAo());
	
	class wa2 implements ActionListener{
		public void actionPerformed (ActionEvent e) {
		    frame.setTitle("Weighted A* 2");
		   uc.setVisible(false);
		   mdh.setVisible(false);
		   jCol.setVisible(false);
		   jDiag.setVisible(false);
		   weigstar1.setVisible(false);
		   seqse1.setVisible(false);
		   seqse2.setVisible(false);
		   aEu.setVisible(true);
		   jRow.setVisible(true);
		   weigstar2.setVisible(true);
		   frame.getContentPane().add(weigstar2);
		}
	}

	
	
    exit.addActionListener(new eeee());
    ucs.addActionListener(new ucs());
    mdhh.addActionListener(new mandh());
    
    
    file.add(exit);
    file.add(ucs);
    file.add(mdhh);
    file.add(dd);
    file.add(rh);
    file.add(ch);
    file.add(sq);
    file.add(sq1);
    file.add(wa11);
    file.add(wa2);
    
    menubar.add(file);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      frame.getContentPane().add(aEu);
      frame.getContentPane().add(mdh);
     frame.getContentPane().add(uc);
      frame.setVisible(true);


    }


private static Node[][] right(Node[][] highway, char dd) {
	int count = 0;
	ArrayList<Position> p = new ArrayList<Position>();
	Random r = new Random();
	int row,col;
	if(dd == 'r') {
	 row = r.nextInt(120);
	col = 159;
	}
	else if(dd == 'l') {
		 row = r.nextInt(120);
			col = 0;
	}
	else if(dd == 'u') {
		 col = r.nextInt(160);
			row = 0;
			}
	else {
		//down
		row = 119;
		col = r.nextInt(160);
	}
	boolean tran = true;
	char d = dd;
	Random ner = new Random();
	highway = sethighway(row, col, highway);
	p.add(new Position(row,col));
	for(int x = 0; x < 19 ; x++){
		int ez = ner.nextInt(4);
//		// System.out.println("Iteration: " + x + " ez: " + ez);
	
				if(ez ==0) {
					if(dd == 'l') {
						x--;
					}
					else {
						col--;
						if(isValid(row,col,highway)) {
							highway = sethighway(row,col,highway);
							p.add(new Position(row,col));
							d = 'r';
						
						}
						else if(has(p,(new Position(row,col)))) {

							x--;
							col++;
						}
						else {
							return null;
						}
					}
				}
				else if(ez == 1) {
					
					if(dd == 'r') {
						x--;
					}
					
					else {
						col++;
						d = 'l';
						if(isValid(row,col,highway)) {
							highway = sethighway(row,col,highway);
						}
						else if(has(p,(new Position(row,col)))) {
							x--;
							col--;
						}
						else {
							return null;
						}
					}
				}
				else if(ez == 2) {
					
					if(dd == 'd') {
						x--;
					}
					
					else {
						row++;
						d = 'u';
						if(isValid(row,col,highway)) {
							highway = sethighway(row,col,highway);
						}
						else if(has(p,(new Position(row,col)))) {

							x--;
							row--;
						}
						else {
							return null;
						}
					}
				}
				else if(ez ==3) {
					
					if(dd == 'u') {
						x--;
					}
					
					else {
						row--;
						d = 'd';
						if(isValid(row,col,highway)) {
							highway = sethighway(row,col,highway);
						}
						else if(has(p,(new Position(row,col)))) {
							x--;
							row++;
						}
						else {
							return null;
						}
					}
				}
				
					
			
		
	}
	//// System.out.println("ddd");
	count = 20;
	Random rand  = new Random();
	int ren = 4;
	boolean valid = true;
	while(valid) {
		ren = rand.nextInt(9);
	//	// System.out.println(ren);
		if(ren > 3) {
			for(int l = 0; l < 20; l++) {
				if(d == 'u') {
					row++;
				}
				else if (d=='d') {
					row--;
					
				}
				else if(d == 'r') {
					col++;
				}
				else if (d == 'l') {
					col--;
				}
				//// System.out.println("Iteration" + count);
			//	// System.out.println("row: " + row );
				//// System.out.println("col: " + col);
			//	// System.out.println(isValid(row,col,highway));
				if(isValid(row,col,highway)) {
					highway = sethighway(row,col,highway);
					count++;
				}
				else {
					if(count > 99 && (col > 159 || col < 0 || row > 119 || row < 0)) {
						//// System.out.println("hi");
						valid = false;
						return highway;
					}
					else {
						return null;
					
					}
				}
				
			}
			
		}
		else {
			Random e = new Random();
			if(d == 'r' || d == 'l') {
				int x = e.nextInt(2);
				if(x==0) {
					d = 'u';
				}
				else {
					d = 'd';
				}
			}
			else {
				int x = e.nextInt(2);
				if(x==0) {
					d = 'l';
				}
				else {
					d = 'r';
				}
			}
			for(int l = 0; l < 20; l++) {
				if(d == 'u') {
					row++;
				}
				else if (d=='d') {
					row--;
					
				}
				else if(d == 'r') {
					col++;
				}
				else if (d == 'l') {
					col--;
				}
//				// System.out.println("Iteration" + count);
//				// System.out.println("row: " + row );
//				// System.out.println("col: " + col);
//				// System.out.println(isValid(row,col,highway));
				if(isValid(row,col,highway)) {
					highway = sethighway(row,col,highway);
					count++;
				}
				else {
					if(count > 99 && (col > 159 || col < 0 || row > 119 || row < 0)) {
						// System.out.println("hi");
						valid = false;
						return highway;
					}
					else {
						return null;
					
					}
				}
				
			}
		}

//			
//				if(highway == null) {
//					return null;
//				}
//				else {
//					switch(d) {
//					case 'l':
//						col +=20;
//					case 'r':
//						col -= 20;
//					case 'd':
//						row -=20;
//					case 'u':
//						row +=20;
//					}
//					count += 20;
//				}
//		}
//		else {
//			int random = rand.nextInt(2);
//			boolean derp = (d == 'r' || d == 'l') ? true : false;
//			if(derp) {
//				if(random == 0) {
//					d = 'u';
//					highway = twentyF(highway,d,row,col,count);
//					if(highway == null) {
//						return null;
//					}
//					else {
//					row+=20;	
//					}
//				}
//				else {
//					d = 'd';
//					highway = twentyF(highway,d,row,col,count);
//					if(highway == null) {
//						return null;
//					}
//					else {
//					row-=20;	
//					}
//				}
//					
//			}
//			else {
//				if(random == 0) {
//					d = 'r';
//					highway = twentyF(highway,d,row,col,count);
//					if(highway == null) {
//						return null;
//					}
//					else {
//					col-=20;	
//					count += 20;
//					}
//				}
//				else {
//					d = 'l';
//					highway = twentyF(highway,d,row,col,count);
//					if(highway == null) {
//						return null;
//					}
//					else {
//					col+=20;
//					count+=20;
//					}
//				}
//			}
	
		
	}
	
	return highway;
	
}

private static boolean has(ArrayList<Position> p, Position position) {
	for(Position e : p) {
		if(e.row() == position.row() && e.col() == position.col()) {
			return true;
		}
	}
	
	return false;
}

private static Node[][] sethighway(int row, int col, Node[][] highway) {
	if(highway[row][col].value == 0) {
		highway[row][col].value=2;
	}
	else if(highway[row][col].value == 1) {
		highway[row][col].value=3;
	}
	return highway;
	
}
static Node[][] reset(Node[][] h, ArrayList<Position> p){
	for(Position pos : p) {
		if(h[pos.row()][pos.col()].value == 2) {
			h[pos.row()][pos.col()].value= 0;
		}
		
		else if(h[pos.row()][pos.col()].value == 3) {
			h[pos.row()][pos.col()].value= 1;
		}

	}
	h[0][0].value = 9;
	return h;
}

static boolean isValid(int row, int col, Node[][] h)  {
	return (row >= 0 && row < 120 && col >=0 && col < 160 ) && h[row][col].value != 2  && h[row][col].value != 3;
}


static Node[][] twentyF(Node[][] highway, char c, int row, int col, int count){
	for(int x = 0; x < 20; x ++) {
		switch(c) {
		case 'l':
			col++;
			if(isValid(row,col,highway)) {
				highway = sethighway(row,col,highway);
			}
			else {
				if(count > 100) {
					return highway;
				}
				return null;
			}
		case 'u':
			row++;
			if(isValid(row,col,highway)) {
				highway = sethighway(row,col,highway);
			}
			else {
				if(count > 100) {
					return highway;
				}
				return null;
			}
		case 'r':
			col--;
			if(isValid(row,col,highway)) {
				highway = sethighway(row,col,highway);
			}
			else {
				if(count > 100) {
					return highway;
				}
				return null;
			}
		case 'd':
			row--;
			if(isValid(row,col,highway)) {
				highway = sethighway(row,col,highway);
			}
			else {
				if(count > 100) {
					return highway;
				}
				return null;
			}
		}
	}
	return null;
}


public static void set(int[][] squareArray, Node[][] highway, JPanel frame, Color c, Node start, Node goal) {
	highway[start.position.row()][start.position.col()].value =7;
	highway[goal.position.row()][goal.position.col()].value=7;
	 for (int i = 0; i < squareArray.length; i++) {
	    	for(int j = 0; j < squareArray[0].length; j++) {
	        squareArray[i][j] = highway[i][j].value;
	        JButton b=new JButton("" + squareArray[i][j]);
	        Color x;
	        switch(squareArray[i][j]) {
	        	case 0:
	        		x = Color.white;
	        		break;
	        	case 1:
	        		x = Color.LIGHT_GRAY;
	        		break;
	        	case 2:
	        		x = Color.DARK_GRAY;
	        		break;
	        	case 3:
	        		x = Color.BLUE;
	        		break;
	        	case 4:
	        		x = Color.black;
	        		break;
	        	case 6:
	        		x = c;
	        		break;
	        	case 7:
	        		x = Color.YELLOW;
	        		break;
	        	default:
	        		x = Color.RED;
	        		break;
	        }
	        b.setBackground(x);
	        frame.add(b);
	        }
	    }
	
	
}



}



			
			
			
			
	
			
			
		
			
			
		
			
			
			

		
	
