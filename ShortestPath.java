
package org.javaservlets;
import java.io.IOException;
import java.util.*; 


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/knightservlet")
public class ShortestPath extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	
	static final int board_size = 8; 
	// Class for storing a cell's data  
	static class Cell  
	{  
		int x, y;  
		List<Cell> path; 

		public Cell(int x, int y, List<Cell> path) 
		{ 
			this.x = x; 
			this.y = y; 
			this.path = path;
		} 


		// Prints out the coordinate represented by a cell object 
		static void printCell(Cell target) {
			System.out.println(target.x + ", " + target.y);
		}

		// Prints out the list of coordinates represented by a list of cell objects 
		static void printListofCells(List<Cell> path) {
			for(int i = 0; i < path.size(); i++) {
				printCell(path.get(i));
			}
		}

	} 

	//method that checks if the coordinate is inside the board  
	static boolean inBounds(int x, int y)  
	{  
		if (x >= 1 && x <= board_size && y >= 1 && y <= board_size) { 
			return true;  
		}
		return false;  
	}  

	// Method returns minimum step  
	// to reach target position  
	static List<Cell> knightShortestPath(int start[], int target[],  
			int N)  
	{  
		// queue for storing states of knight in board  
				List<Cell> states = new ArrayList<>();  
		// x and y direction, where a knight can move  
		int xmoves[] = {-2, -1, 1, -2,  2, -1, 1, 2};  
		int ymoves[] = {-1, -2, -2, 1, -1, 2, 2, 1};  

		

		// add initial position to states   
		Cell starting_cell = new Cell(start[0], start[1], new ArrayList<Cell>()); 
		starting_cell.path.add(starting_cell); 
		states.add(starting_cell); 

		 
		// boolean visited[][] = new boolean[N + 1][N + 1];  
		boolean visited[][] = new boolean[board_size + 1][board_size + 1];
		
		// all cells should be set to unvisited   
		for (int i = 1; i <= board_size; i++)  
			for (int j = 1; j <= board_size; j++)  
				visited[i][j] = false;  

		// make sure the starting knightPos is visited   
		visited[start[0]][start[1]] = true;  

		Cell coordinate;  
		int x, y; 
		
		while (states.size() != 0)  
		{  
			coordinate = states.get(0);  
			states.remove(0);  

			// if current cell is equal to target cell,  
			// return its distance  
			if (coordinate.x == target[0] && coordinate.y == target[1])  {
				System.out.println("Size of path " + coordinate.path.size()); 
				Cell.printListofCells(coordinate.path) ; 
				return coordinate.path;  
			}

			// loop for all reachable states  
			for (int i = 0; i < xmoves.length; i++)  
			{  
				x = coordinate.x + xmoves[i];  
				y = coordinate.y + ymoves[i];  

				// If reachable state is not yet visited and  
				// inside board, push that state into queue  
				if (inBounds(x, y) && !visited[x][y]) 
				{  
					visited[x][y] = true; 
					List<Cell> temp_list = new ArrayList<Cell>(); 
					Cell temp_cell = new Cell(x, y, temp_list); 

					for(int a = 0; a < coordinate.path.size(); a++) {
						temp_list.add(coordinate.path.get(a)); 
					}

					temp_cell.path.add(temp_cell);
					states.add(temp_cell);  
				}  
			}  
		}  
		return null; 
	}  
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String param1 = req.getParameter("x1"); 
		   String param2 = req.getParameter("y1"); 
		   String param3 = req.getParameter("x2"); 
		   String param4 = req.getParameter("y2"); 
		
		int start[] = {Integer.parseInt(param1), Integer.parseInt(param2)};  
		int target[] = {Integer.parseInt(param3), Integer.parseInt(param4)}; 
		System.out.println(knightShortestPath(start, target, board_size)); 
	} 
} 