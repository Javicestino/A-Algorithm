import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MazeA extends Maze{
	private static class Node{
		private int row;
		private int column;

		public Node() {
			column = 0;
			row = 0;
		}
		public Node(int row, int column) {
			this.row = row;
			this.column = column;
		}
		public int getRow() {
			return row;
		}
		public int getCol() {
			return column;
		}
		public boolean equals(Object n) {
			if(row == ((Node) n).getRow() && column == ((Node) n).getCol()) {
				return true;
			}else {
				return false;
			}
		}
		public int hashCode() {
			return row*10 + column*30;
		}
	}
	
	private Set<Node> closedSet;
	private Set<Node> openSet;
	private Map<Node,Node> parent;
	private Map<Node,Integer> f;
	private Map<Node,Integer> g;
	private int pathLength;
	public MazeA(int ROW_SIZE, int COLUMN_SIZE) {
		super(ROW_SIZE, COLUMN_SIZE);
		closedSet = new HashSet<Node>();
		openSet = new HashSet<Node>();
		parent = new HashMap<Node,Node>();
		g = new HashMap<Node,Integer>();
		f = new HashMap<Node,Integer>();
		pathLength = 0;
	}
	public Node minF(Set<Node> openSet) {
		int minG = 38000;
		Node returnNode = new Node();
		for(Node node :  openSet) {
			if(f.get(node) < minG) {
				minG = f.get(node);
				returnNode = node;
			}
		}
		return returnNode;
		
	}
	public List<Node> neightbours(Node current){
		int currentI = current.getRow();
		int currentJ = current.getCol();
		List<Node> neightbourList = new ArrayList<Node>();
		for(int i = -1 ; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if((currentI + i) > 0 && (currentI + i) < ROW_SIZE && (currentJ + j) > 0 && (currentJ + j) < COLUMN_SIZE && getState(currentI + i,currentJ + j) != '#') {
					neightbourList.add(new Node(currentI + i, currentJ + j));
				}
			}
		}
		return neightbourList;
	}
	
	
	public void reconstructPath(Map<Node,Node> parent, Node current){
		Node p = parent.get(current);
		while(p != null) {
			pathLength++;
			change(p.getRow(),p.getCol());
			p = parent.get(current);
			current = p;
		}
		pathLength--; 
	}
	
	public void changeMaze() {
		//Inicialización de las diferentes estructuras
		int tentativeG;
		Node init = new Node(initRow,initCol);
		g.put(init, 0);
		f.put(init,heuristic(init));
		parent.put(init, null);
		openSet.add(init);
		Node current = new Node();
		Boolean fin = false;
		//inicio algorithm
	
		while(!openSet.isEmpty() && !fin ) {
			current = minF(openSet);//minG comprobada
			if(current.getCol() == goalCol && current.getRow() == goalRow) {
				fin = true;
				this.reconstructPath(parent, current);
				break;
			}
			
			openSet.remove(current);
			closedSet.add(current);
			//hasta aquí
			if(neightbours(current) == null) {
				throw new RuntimeException("No hay camino");
			}else {
				for(Node neightbour: neightbours(current)) {
					if(!closedSet.contains(neightbour)){
						tentativeG = g.get(current) + 1;
						if(!openSet.contains(neightbour) || tentativeG < g.get(neightbour) ) {
							parent.put(neightbour, current);
							g.put(neightbour, tentativeG);
							f.put(neightbour,tentativeG + heuristic(neightbour));
							openSet.add(neightbour);
						}
					}
				}
			}
		}
		if(fin == false) {
			throw new RuntimeException("No hay camino");
		}
	}
	
	public int heuristic(Node n) {
		return Math.abs(n.getCol() - goalCol) + Math.abs(n.getRow() - goalRow);
	}
	public int getPathLength() {
		return pathLength;
	}
}
