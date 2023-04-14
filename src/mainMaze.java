import java.io.IOException;

public class mainMaze {

	public static void main(String[] args) throws IOException {
		//Instancia de Maze
		int numGoal = 1;
		int numStart = 1;
		int rows = 60;
		int columns = 80 ;
		int totalGrids = rows * columns;
		int numobstacles = (int) ((totalGrids * 30) / 100);
	
		
		/*
			MazeA mazea = new MazeA(rows,columns);
			mazea.rellenar(numobstacles, numGoal, numStart);
			mazea.changeMaze(); 
			System.out.println(mazea.toString());
		*/
		//Estadísticas sobre los resultados
		
		int fallos = 0;
		int sumPathLength = 0;
		int nTests = 100;
		for(int i = 0; i < nTests; i++) {
			try{
				MazeA mazeb = new MazeA(rows,columns);
				mazeb.rellenar(numobstacles, numGoal, numStart);
				mazeb.changeMaze(); 
				sumPathLength += mazeb.getPathLength();
			}catch(Exception e){
				fallos++;
			}
		}
		int successful = nTests - fallos;
		if(successful != 0) {
			int averagePathLength = sumPathLength / (nTests - fallos);
			System.out.println("There were " + fallos + " fails. The average path length of the algorithm was " + averagePathLength + ".");
		}else {
			System.out.println("All the tests failed");
		}
		
	}

}
