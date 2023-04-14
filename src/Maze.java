//Lab 1 sistemas inteligentes

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Maze {
	protected int ROW_SIZE;
	protected int COLUMN_SIZE;
	protected char[][] maze; 
	int initRow;
	int initCol;
	int goalRow;
	int goalCol;
	
	//state maze[i][j]
	//node(struct) -> state, parent,children,depth, pathcost g(x)
	public Maze(int ROW_SIZE,int COLUMN_SIZE) {
		this.ROW_SIZE = ROW_SIZE;
		this.COLUMN_SIZE = COLUMN_SIZE;
		maze = new char[ROW_SIZE][COLUMN_SIZE];
		for(int i = 0; i < ROW_SIZE ; i++) {
			for(int j = 0; j < COLUMN_SIZE;j++) {
				maze[i][j] = ' ';
			}
		}
	}
	
	public static int random(int n) {
		Random num = new Random();
		int numRandom = num.nextInt(n);
		return numRandom;
	}
	
	public void rellenar(int numobstacles, int numGoal, int  numStart) throws IOException {
		
		while(numobstacles > 0) {
			int Rcol = random(COLUMN_SIZE);
			int Rrow = random(ROW_SIZE);
			if(maze[Rrow][Rcol] != '#') {
				maze[Rrow][Rcol] = '#';
				numobstacles--;
			}
			
		}
		Boolean avalaible = true;
		while(numGoal > 0 && avalaible) {
			goalCol= random(COLUMN_SIZE);
			goalRow = random(ROW_SIZE);
			if (maze[goalRow][goalCol] != '#') {
				maze[goalRow][goalCol] = 'G';
				numGoal--;
			}else {
				avalaible = false;
			}
		}
		while(numStart > 0 && avalaible) {
			initCol = random(COLUMN_SIZE);
			initRow = random(ROW_SIZE);
			if (maze[initRow][initCol] != '#' && maze[initRow][initCol] != 'G') {
				maze[initRow][initCol] = 'S';
				numStart--;
			}else {
				avalaible = false;
			}
		}
		
		if(avalaible == false) {
			String message = "The proccess of filling the maze could'nt be completed because \n"
					+ "	there was an aspirant to be goalState / initState that was occupied"
					+ "\n\n\n";
			try{
				FileWriter outputFile = new FileWriter("C:\\Users\\rafas\\OneDrive\\Escritorio\\ing_software\\2do año\\2ndCuatrimestre\\inteligentSystems\\Lab1\\Lab1\\src\\output.txt");
				outputFile.write(message);
				outputFile.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			throw new RuntimeException("Stopped: look at output.txt");
		}
	}
	
	public String toString() {
		StringBuilder st = new StringBuilder();
		for(int i = 0; i<ROW_SIZE; i++) {
			for(int j = 0; j< COLUMN_SIZE; j++) {
			st.append((maze[i][j]));
			}
			st.append('\n');
		}
		
		return st.toString();
	}
	public String toFile() {
		StringBuilder st = new StringBuilder();
		for(int i = 0; i<ROW_SIZE; i++) {
			for(int j = 0; j< COLUMN_SIZE; j++) {
			st.append((maze[i][j]));
			}
			st.append('\n');
		}
		FileWriter outputFile;
		try {
			outputFile = new FileWriter("C:\\Users\\rafas\\OneDrive\\Escritorio\\ing_software\\2do año\\2ndCuatrimestre\\inteligentSystems\\Lab1\\Lab1\\src\\output.txt");
			outputFile.write(st.toString());
			outputFile.close();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return st.toString();
	}
	public char getState(int row,int col) {
		return maze[row][col];
	}
	public void change(int row,int col) {
		if(maze[row][col] != 'S')
			maze[row][col] = '0';
	}
	public static int[] FindS(char[][]maze,int col, int rows) { // Find the pos of the Start position
		boolean encontrado = false;
		
		int[] pos = new int[2];
		while(!encontrado) {
			for(int i = 0; i<col; i++) {
				for(int j=0; j<rows; j++) {
					if (maze[i][j] == 'S') {
						pos[0] = i;
						pos[1] = j;
						encontrado = true;
						break;
					}
				}
			}
		}
		return pos;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
