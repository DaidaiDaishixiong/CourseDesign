package MazeGenerator;

import MazeGenerator.Maze.Cell;

public class TestMaze {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*�ֶ������Թ�*/
		/*
		Maze maze = Maze.manualGenerate();
		Cell start = maze.cells[1 + maze.width];
		Cell end = maze.cells[maze.width-2 + maze.width * (maze.height - 2)];
		while((start = maze.mazeRouting(start)) != end);
		Maze.showMaze(maze);
		*/
		/*�Զ������Թ�*/
		
		Maze maze = new Maze(17, 17);
		Cell start = maze.cells[1 + maze.width];
		start.parent = start;
		Cell end = start;
		while((end = maze.digPath(end)) != start);
		Maze.showMaze(maze);
		
		/*��·��Ѱ·*/
		
		//maze.iniDirs();
		//end = maze.cells[maze.width-2 + maze.width * (maze.height - 2)];
		/*���Ѱ·ʱҪ�õ�ջ*/
		//if(!maze.cellStack.isEmpty())
			//Maze.emptyStack(maze.cellStack);
		//while((start = maze.mazeRouting(start)) != end);
			//Maze.showMaze(maze);
		
		/*�����Թ�*/
		
		//Cell des = start;
		//maze.iniDirs();//��ʼ������
		/*���Ѱ·ʱҪ�õ�ջ*/
		//if(!maze.cellStack.isEmpty())
			//Maze.emptyStack(maze.cellStack);
		//maze.cellStack.push(start);//�����ѹ��ջ��
		//while((start = maze.traverseMaze(start)) != des);//�ص����ʱֹͣ
			//Maze.showMaze(maze);
			
		/*���·��*/
		Cell temp;//���շ��ص��յ�
		end = maze.cells[maze.width - 2 + (maze.height - 2) * maze.width];
		temp = maze.shortestPath(start, end);
		/*���·��*/
		while(temp != start) {
			temp.show = '��';
			temp = temp.parent;
		}
		Maze.showMaze(maze);//��ӡ�Թ�
	}
}
