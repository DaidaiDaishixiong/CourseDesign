package MazeGenerator;

import java.io.*;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Maze {
	
	/*定义迷宫的单元格*/
	public class Cell{
		int x, y, dirs;//坐标及方向
		public char show = ' ';
		Cell parent;//父节点
		int distance = 0;//该单元格距离起点的距离
		boolean right = false, down = false, left = false, up = false;//记录每个方向是否被走过；
		
		public Cell() {
		}
		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public int width, height;//迷宫的长度与宽度
	public Cell[] cells;//迷宫的单元格
	Stack<Cell> cellStack = new Stack<>();//寻路用的栈
	Queue<Cell> cellQueue = new LinkedList<>();
	
	/**清空栈*/
	public static void emptyStack(Stack<Cell> stack) {
		while(!stack.empty()) {
			stack.pop();
		}
	}
	
	/**判断能否向右*/
	public int right(Cell cell){
		if(cell.x + 2 < this.width && this.cells[(cell.x + 1) + cell.y * this.width].show != '#' && cell.right == false)//不能出界，中间不能有墙且没有走过
			return 1;
		else {
			cell.right = true;//将路径设置为不可走
			return 0;
		}
	}
	
	/**判断能否向下*/
	public int down(Cell cell) {
		if(cell.y + 2 < this.height && this.cells[cell.x + (cell.y + 1) * this.width].show != '#' && cell.down == false)//不能出界，中间不能有墙且没有走过
			return 1;
		else {
			cell.down = true;//将路径设置为不可走
			return 0;
		}
	}
	
	/**判断能否向左*/
	public int left(Cell cell) {
		if(cell.x - 2 >= 0 && this.cells[(cell.x - 1) + cell.y * this.width].show != '#' && cell.left == false)//不能出界，中间不能有墙且没有走过
			return 1;
		else {
			cell.left = true;//将路径设置为不可走
			return 0;
		}
	}
	
	/**判断能否向上*/
	public int up(Cell cell) {
		if(cell.y - 2 >= 0 && this.cells[cell.x + (cell.y - 1) * this.width].show != '#' && cell.up == false)//不能出界，中间不能有墙且没有走过
			return 1;
		else {
			cell.up = true;//将路径设置为不可走
			return 0;
		}
	}
	
	/**最短路径,使用之前初始化方向*/
	public Cell shortestPath(Cell start, Cell end) {
		
		Cell currentCell;//当前位置
		/*将起点入队*/
		cellQueue.add(start);
		/*把可以到达的地方放入队列*/
		while(!cellQueue.isEmpty()) {
			currentCell = cellQueue.poll();
			/*找到终点的时候返回*/
			if(currentCell == end) {
				return currentCell;
			}
			
			if(this.right(currentCell) == 1) {
				if(cells[(currentCell.x + 2) + currentCell.y * width].distance == 0) {//该位置未被访问过
					cellQueue.offer(cells[(currentCell.x + 2) + currentCell.y * width]);//入队
					cells[(currentCell.x + 2) + currentCell.y * width].distance = currentCell.distance++;//移动一次距离增加1
					/*记录方向，不能重复*/
					currentCell.right = true;
					cells[(currentCell.x + 2) + currentCell.y * width].left = true;
					
				cells[(currentCell.x + 2) + currentCell.y * width].parent = currentCell;//将路径连接起来
				//currentCell.show = '·';//显示路径
				//showMaze(this);//打印迷宫
				}
			}
			if(this.down(currentCell) == 1) {
				if(cells[currentCell.x + (currentCell.y + 2) * width].distance == 0) {//该位置未被访问过
					cellQueue.offer(cells[currentCell.x + (currentCell.y + 2) * width]);//入队
					cells[currentCell.x + (currentCell.y + 2) * width].distance = currentCell.distance++;//移动一次距离增加1
					/*记录方向，不能重复*/
					currentCell.down = true;
					cells[currentCell.x + (currentCell.y + 2) * width].up = true;
					
				cells[currentCell.x + (currentCell.y + 2) * width].parent = currentCell;//将路径连接起来
				//currentCell.show = '·';//显示路径
				//showMaze(this);//打印迷宫
				}
			}
			if(this.left(currentCell) == 1) {
				if(cells[(currentCell.x - 2) + currentCell.y * width].distance == 0) {//该位置未被访问过
					cellQueue.offer(cells[(currentCell.x - 2) + currentCell.y * width]);//入队
					cells[(currentCell.x - 2) + currentCell.y * width].distance = currentCell.distance++;//移动一次距离增加1
					/*记录方向，不能重复*/
					currentCell.left = true;
					cells[(currentCell.x - 2) + currentCell.y * width].right = true;
					
				cells[(currentCell.x - 2) + currentCell.y * width].parent = currentCell;//将路径连接起来
				//currentCell.show = '·';//显示路径
				//showMaze(this);//打印迷宫
				}
			}
			if(this.up(currentCell) == 1) {
				if(cells[currentCell.x + (currentCell.y - 2) * width].distance == 0) {//该位置未被访问过
					cellQueue.offer(cells[currentCell.x + (currentCell.y - 2) * width]);//入队
					cells[currentCell.x + (currentCell.y - 2) * width].distance = currentCell.distance++;//移动一次距离增加1
					/*记录方向，不能重复*/
					currentCell.up = true;
					cells[currentCell.x + (currentCell.y - 2) * width].down =true;
					
				cells[currentCell.x + (currentCell.y - 2) * width].parent = currentCell;//将路径连接起来
				//currentCell.show = '·';//显示路径
				//showMaze(this);//打印迷宫
				}
			}	
		}
		/*没找到终点时*/
		return null;
	}
	
	/**遍历迷宫,使用之前初始化方向*/
	public Cell traverseMaze(Cell start){
		int x = 0, y = 0, dir;//x,y:重点的坐标，dir:指定的任意方向
		int n = 0;//记录走了几个方向
		Cell temp;//终点
		
		/*当除来的方向以外有两个以上的方向可以走时*/
		if(this.right(start) + this.down(start) + this.left(start) + this.up(start) >= 3 && !cellStack.contains(start))
			cellStack.push(start);//将符合条件的方格入栈
		/*栈内元素无路可走时出栈*/
		if(start.right == true && start.down == true && start.left == true && start.up ==true && start == cellStack.peek()) {
			cellStack.pop();
			return cellStack.peek();
		}
		
 			while(n < 4) {
				dir = 1 << n;//指定一个方向
				n++;
				switch(dir) {
				
				case 1://向右走
					if(start.right == false) {
						/*不能往回走*/
						if(start.parent.x == start.x + 2) {
							start.right = true;
							continue;
						}
						x = start.x + 2;
						y = start.y;
						start.right = true;
					}
					else continue;
					break;
				case 2://向下走
					if(start.down == false) {
						/*不能往回走*/
						if(start.parent.y == start.y + 2) {
							start.down = true;
							continue;
						}
						x = start.x;
						y = start.y + 2;
						start.down = true;
					}
					else continue;
					break;
				case 4://向左走
					if(start.left == false) {
						/*不能往回走*/
						if(start.parent.x == start.x - 2) {
							start.left = true;
							continue;
						}
						x = start.x - 2;
						y = start.y;
						start.left = true;
					}
					else continue;
					break;
				case 8://向上走
					if(start.up == false) {
						/*不能往回走*/
						if(start.parent.y == start.y - 2) {
							start.up = true;
							continue;
						}
						x = start.x;
						y = start.y - 2;
						start.up = true;
					}
					else continue;
					break;
				}
				
				temp = this.cells[x + y * this.width];
				temp.parent = start;
				//if((start.right && start.down && start.up && start.left) != true && temp == start.parent)//当前三个方向没走完之前不能往回走
						//continue;
				if((start.right && start.down && start.up && start.left) == true && temp == start.parent)//当走到死路时返回栈顶元素
					return cellStack.peek();
				/*当终点不是墙时*/
				if(temp.show != '#') {
					temp.parent = start;
					temp.show = '·';//显示路径
					return temp;//返回终点对象
				}
				else
					continue;//终点是墙就换一个方向
			}
			/*若该结点没有有效的路径*/
			return cellStack.peek();//返回当前栈顶对象
	}
	
	
	/**单路径寻路,使用之前初始化方向*/
	public Cell mazeRouting(Cell start) {
		int x = 0, y = 0, dir;//x,y:重点的坐标，dir:指定的任意方向
		int n = 0;//记录走了几个方向
		Cell temp;//终点
		
		cellStack.push(start);//将起点入栈
		while(n < 4) {
			dir = 1 << n;//指定一个方向
			n++;
			switch(dir) {
			
			case 1://向右走
				start.right = true;
				if(start.x + 2 < this.width) {
					x = start.x + 2;
					y = start.y;
				}
				else continue;
				break;
			case 2://向下走
				start.down = true;
				if(start.y + 2 < this.height) {
					x = start.x;
					y = start.y + 2;
				}
				else continue;
				break;
			case 4://向左走
				start.left = true;
				if(start.x - 2 >= 0) {
					x = start.x - 2;
					y = start.y;
				}
				else continue;
				break;
			case 8://向上走
				start.up = true;
				if(start.y - 2 >= 0) {
					x = start.x;
					y = start.y - 2;
				}
				else continue;
				break;
			}
			
			/*当起点和终点之间是通路*/
			if(this.cells[start.x + (x - start.x) / 2 + (start.y + (y - start.y) / 2) * width].show != '#') {
				temp = this.cells[x + y * this.width];
				if((start.right && start.down && start.up && start.left) != true && temp == start.parent)//当前三个方向没走完之前不能往回走
						continue;
				/*当终点不是墙时*/
				if(temp.show != '#') {
					temp.parent = start;
					temp.show = '·';//显示路径
					cellStack.push(temp);//把终点入栈
					return cellStack.peek();//返回栈顶的对象
				}
				else
					continue;//终点是墙就换一个方向
			}
			else 
				continue;//如果起点和终点隔了一堵墙就换一个方向
		}
		/*若该结点没有有效的路径*/
		cellStack.pop();//弹出栈顶对象
		return cellStack.peek();//返回当前栈顶对象
	}
	
	/**手动生成迷宫*/
	public static Maze manualGenerate() throws Exception{
		String fileName = "maze.txt";
		String receiveString = new String();//接收文件中的一行字符串
		Scanner fileInput = new Scanner(new File(fileName));
		int row = 0, column = 0;//文本中迷宫的规格
		ArrayList<Character> charArr = new ArrayList<>();
		
		/*读取文件并初始化迷宫*/
		while(fileInput.hasNext()) {
			receiveString = fileInput.nextLine();
			for(int i = 0; i < receiveString.length(); i++) {
				if(receiveString.charAt(i) == '1')
					charArr.add('#');
				else
					charArr.add(' ');
			}
			row++;
		}
			column = receiveString.toCharArray().length;
		
		Maze manualMaze = new Maze(column, row);
		for(int i = 0; i < charArr.size(); i++) {
			manualMaze.cells[i].show = charArr.get(i);
		}
		
		fileInput.close();
		return manualMaze;
	}
	
	/**随机生成迷宫*/
	public Cell digPath(Cell start) {
		int x = 0, y = 0, dir = 0;
		Cell tCell;
		
		if(start == null)
			return null;
		
		while(start.dirs != 0) {
			dir = 1 << (int)((Math.random() * 10000) % 4);//随机指定一个方向
			
			if((int)(~start.dirs & dir) != 0)//如果遇到死路，判断下一次方向是否与上一次重复	
				continue;
			start.dirs &= ~dir;//将当前方向的反码形式保存下来
			
			/*迷宫的下一步*/
			switch(dir) {
			
			case 1://向右走
				if(start.x + 2 < this.width) {
					x = start.x + 2;
					y = start.y;
				}
				else continue;
				break;
			case 2://向下走
				if(start.y + 2 < this.height) {
					x = start.x;
					y = start.y + 2;
				}
				else continue;
				break;
			case 4://向左走
				if(start.x - 2 >= 0) {
					x = start.x - 2;
					y = start.y;
				}
				else continue;
				break;
			case 8://向上走
				if(start.y - 2 >= 0) {
					x = start.x;
					y = start.y - 2;
				}
				else continue;
				break;
			}
			
			tCell = this.cells[x + y * this.width];
			
			/*终点不能为墙*/
			if(tCell.show == ' ') {
				/*终点的单元格如果已经连接就舍掉*/
				if(tCell.parent != null) {
					continue;
				}
				tCell.parent = start;//连接终点与起点
				this.cells[start.x + (x - start.x) / 2 + (start.y + (y - start.y) / 2) * width].show = ' ';//去掉起点与终点之间的墙
				return tCell;
			}
		}
		return start.parent;
	}
	
	/**自动生成迷宫后，重新初始化每个单元格的方向用于寻路*/
	public void iniDirs() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(i * j % 2 != 0) {
					cells[i + j * width].parent = null;
					cells[i + j * width].dirs = 15;
					cells[i + j * width].right = false;
					cells[i + j * width].down = false;
					cells[i + j * width].left = false;
					cells[i + j * width].up = false;
				}
			}
		}
	}
	
	/**输出迷宫的形状*/
	public static void showMaze(Maze maze) {
		for (int i = 0; i < maze.height; i++ )
		{
			for (int j = 0; j < maze.width; j++ )
			{
				System.out.print(maze.cells[j + i * maze.width].show);
			}
			System.out.println();
		}
	}
	
	/**初始化迷宫*/
	public Maze(int width, int height){	
		/*设置并初始化迷宫的长度和宽度*/
		this.width = width;
		this.height = height;
		cells = new Cell[this.width * this.height];
		
		/*为每个单元格分配空间*/
		//for(int i = 0; i < width * height; i++) {
			//cells[i] = new Cell();
		//}
		
		/*为每个单元格分配空间并初始化每个单元格*/
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				cells[i + j * width] = new Cell();
				if(i * j % 2 != 0) {
					cells[i + j * width].x = i;
					cells[i + j * width].y = j;
					cells[i + j * width].dirs = 15;
				}
				else {
					cells[i + j * width].show = '#';
				}
			}
		}
	}
	
	public Maze() {
		this(7, 7);
	}
}
