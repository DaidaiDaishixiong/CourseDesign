package MazeGenerator;

import MazeGenerator.Maze.Cell;

public class TestMaze {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*手动生成迷宫*/
		/*
		Maze maze = Maze.manualGenerate();
		Cell start = maze.cells[1 + maze.width];
		Cell end = maze.cells[maze.width-2 + maze.width * (maze.height - 2)];
		while((start = maze.mazeRouting(start)) != end);
		Maze.showMaze(maze);
		*/
		/*自动生成迷宫*/
		
		Maze maze = new Maze(17, 17);
		Cell start = maze.cells[1 + maze.width];
		start.parent = start;
		Cell end = start;
		while((end = maze.digPath(end)) != start);
		Maze.showMaze(maze);
		
		/*单路径寻路*/
		
		//maze.iniDirs();
		//end = maze.cells[maze.width-2 + maze.width * (maze.height - 2)];
		/*清空寻路时要用的栈*/
		//if(!maze.cellStack.isEmpty())
			//Maze.emptyStack(maze.cellStack);
		//while((start = maze.mazeRouting(start)) != end);
			//Maze.showMaze(maze);
		
		/*遍历迷宫*/
		
		//Cell des = start;
		//maze.iniDirs();//初始化方向
		/*清空寻路时要用的栈*/
		//if(!maze.cellStack.isEmpty())
			//Maze.emptyStack(maze.cellStack);
		//maze.cellStack.push(start);//将起点压入栈中
		//while((start = maze.traverseMaze(start)) != des);//回到起点时停止
			//Maze.showMaze(maze);
			
		/*最短路径*/
		Cell temp;//接收返回的终点
		end = maze.cells[maze.width - 2 + (maze.height - 2) * maze.width];
		temp = maze.shortestPath(start, end);
		/*输出路径*/
		while(temp != start) {
			temp.show = '·';
			temp = temp.parent;
		}
		Maze.showMaze(maze);//打印迷宫
	}
}
