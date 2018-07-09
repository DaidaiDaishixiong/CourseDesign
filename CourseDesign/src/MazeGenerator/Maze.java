package MazeGenerator;

import java.io.*;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Maze {
	
	/*�����Թ��ĵ�Ԫ��*/
	public class Cell{
		int x, y, dirs;//���꼰����
		public char show = ' ';
		Cell parent;//���ڵ�
		int distance = 0;//�õ�Ԫ��������ľ���
		boolean right = false, down = false, left = false, up = false;//��¼ÿ�������Ƿ��߹���
		
		public Cell() {
		}
		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	public int width, height;//�Թ��ĳ�������
	public Cell[] cells;//�Թ��ĵ�Ԫ��
	Stack<Cell> cellStack = new Stack<>();//Ѱ·�õ�ջ
	Queue<Cell> cellQueue = new LinkedList<>();
	
	/**���ջ*/
	public static void emptyStack(Stack<Cell> stack) {
		while(!stack.empty()) {
			stack.pop();
		}
	}
	
	/**�ж��ܷ�����*/
	public int right(Cell cell){
		if(cell.x + 2 < this.width && this.cells[(cell.x + 1) + cell.y * this.width].show != '#' && cell.right == false)//���ܳ��磬�м䲻����ǽ��û���߹�
			return 1;
		else {
			cell.right = true;//��·������Ϊ������
			return 0;
		}
	}
	
	/**�ж��ܷ�����*/
	public int down(Cell cell) {
		if(cell.y + 2 < this.height && this.cells[cell.x + (cell.y + 1) * this.width].show != '#' && cell.down == false)//���ܳ��磬�м䲻����ǽ��û���߹�
			return 1;
		else {
			cell.down = true;//��·������Ϊ������
			return 0;
		}
	}
	
	/**�ж��ܷ�����*/
	public int left(Cell cell) {
		if(cell.x - 2 >= 0 && this.cells[(cell.x - 1) + cell.y * this.width].show != '#' && cell.left == false)//���ܳ��磬�м䲻����ǽ��û���߹�
			return 1;
		else {
			cell.left = true;//��·������Ϊ������
			return 0;
		}
	}
	
	/**�ж��ܷ�����*/
	public int up(Cell cell) {
		if(cell.y - 2 >= 0 && this.cells[cell.x + (cell.y - 1) * this.width].show != '#' && cell.up == false)//���ܳ��磬�м䲻����ǽ��û���߹�
			return 1;
		else {
			cell.up = true;//��·������Ϊ������
			return 0;
		}
	}
	
	/**���·��,ʹ��֮ǰ��ʼ������*/
	public Cell shortestPath(Cell start, Cell end) {
		
		Cell currentCell;//��ǰλ��
		/*��������*/
		cellQueue.add(start);
		/*�ѿ��Ե���ĵط��������*/
		while(!cellQueue.isEmpty()) {
			currentCell = cellQueue.poll();
			/*�ҵ��յ��ʱ�򷵻�*/
			if(currentCell == end) {
				return currentCell;
			}
			
			if(this.right(currentCell) == 1) {
				if(cells[(currentCell.x + 2) + currentCell.y * width].distance == 0) {//��λ��δ�����ʹ�
					cellQueue.offer(cells[(currentCell.x + 2) + currentCell.y * width]);//���
					cells[(currentCell.x + 2) + currentCell.y * width].distance = currentCell.distance++;//�ƶ�һ�ξ�������1
					/*��¼���򣬲����ظ�*/
					currentCell.right = true;
					cells[(currentCell.x + 2) + currentCell.y * width].left = true;
					
				cells[(currentCell.x + 2) + currentCell.y * width].parent = currentCell;//��·����������
				//currentCell.show = '��';//��ʾ·��
				//showMaze(this);//��ӡ�Թ�
				}
			}
			if(this.down(currentCell) == 1) {
				if(cells[currentCell.x + (currentCell.y + 2) * width].distance == 0) {//��λ��δ�����ʹ�
					cellQueue.offer(cells[currentCell.x + (currentCell.y + 2) * width]);//���
					cells[currentCell.x + (currentCell.y + 2) * width].distance = currentCell.distance++;//�ƶ�һ�ξ�������1
					/*��¼���򣬲����ظ�*/
					currentCell.down = true;
					cells[currentCell.x + (currentCell.y + 2) * width].up = true;
					
				cells[currentCell.x + (currentCell.y + 2) * width].parent = currentCell;//��·����������
				//currentCell.show = '��';//��ʾ·��
				//showMaze(this);//��ӡ�Թ�
				}
			}
			if(this.left(currentCell) == 1) {
				if(cells[(currentCell.x - 2) + currentCell.y * width].distance == 0) {//��λ��δ�����ʹ�
					cellQueue.offer(cells[(currentCell.x - 2) + currentCell.y * width]);//���
					cells[(currentCell.x - 2) + currentCell.y * width].distance = currentCell.distance++;//�ƶ�һ�ξ�������1
					/*��¼���򣬲����ظ�*/
					currentCell.left = true;
					cells[(currentCell.x - 2) + currentCell.y * width].right = true;
					
				cells[(currentCell.x - 2) + currentCell.y * width].parent = currentCell;//��·����������
				//currentCell.show = '��';//��ʾ·��
				//showMaze(this);//��ӡ�Թ�
				}
			}
			if(this.up(currentCell) == 1) {
				if(cells[currentCell.x + (currentCell.y - 2) * width].distance == 0) {//��λ��δ�����ʹ�
					cellQueue.offer(cells[currentCell.x + (currentCell.y - 2) * width]);//���
					cells[currentCell.x + (currentCell.y - 2) * width].distance = currentCell.distance++;//�ƶ�һ�ξ�������1
					/*��¼���򣬲����ظ�*/
					currentCell.up = true;
					cells[currentCell.x + (currentCell.y - 2) * width].down =true;
					
				cells[currentCell.x + (currentCell.y - 2) * width].parent = currentCell;//��·����������
				//currentCell.show = '��';//��ʾ·��
				//showMaze(this);//��ӡ�Թ�
				}
			}	
		}
		/*û�ҵ��յ�ʱ*/
		return null;
	}
	
	/**�����Թ�,ʹ��֮ǰ��ʼ������*/
	public Cell traverseMaze(Cell start){
		int x = 0, y = 0, dir;//x,y:�ص�����꣬dir:ָ�������ⷽ��
		int n = 0;//��¼���˼�������
		Cell temp;//�յ�
		
		/*�������ķ����������������ϵķ��������ʱ*/
		if(this.right(start) + this.down(start) + this.left(start) + this.up(start) >= 3 && !cellStack.contains(start))
			cellStack.push(start);//�����������ķ�����ջ
		/*ջ��Ԫ����·����ʱ��ջ*/
		if(start.right == true && start.down == true && start.left == true && start.up ==true && start == cellStack.peek()) {
			cellStack.pop();
			return cellStack.peek();
		}
		
 			while(n < 4) {
				dir = 1 << n;//ָ��һ������
				n++;
				switch(dir) {
				
				case 1://������
					if(start.right == false) {
						/*����������*/
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
				case 2://������
					if(start.down == false) {
						/*����������*/
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
				case 4://������
					if(start.left == false) {
						/*����������*/
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
				case 8://������
					if(start.up == false) {
						/*����������*/
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
				//if((start.right && start.down && start.up && start.left) != true && temp == start.parent)//��ǰ��������û����֮ǰ����������
						//continue;
				if((start.right && start.down && start.up && start.left) == true && temp == start.parent)//���ߵ���·ʱ����ջ��Ԫ��
					return cellStack.peek();
				/*���յ㲻��ǽʱ*/
				if(temp.show != '#') {
					temp.parent = start;
					temp.show = '��';//��ʾ·��
					return temp;//�����յ����
				}
				else
					continue;//�յ���ǽ�ͻ�һ������
			}
			/*���ý��û����Ч��·��*/
			return cellStack.peek();//���ص�ǰջ������
	}
	
	
	/**��·��Ѱ·,ʹ��֮ǰ��ʼ������*/
	public Cell mazeRouting(Cell start) {
		int x = 0, y = 0, dir;//x,y:�ص�����꣬dir:ָ�������ⷽ��
		int n = 0;//��¼���˼�������
		Cell temp;//�յ�
		
		cellStack.push(start);//�������ջ
		while(n < 4) {
			dir = 1 << n;//ָ��һ������
			n++;
			switch(dir) {
			
			case 1://������
				start.right = true;
				if(start.x + 2 < this.width) {
					x = start.x + 2;
					y = start.y;
				}
				else continue;
				break;
			case 2://������
				start.down = true;
				if(start.y + 2 < this.height) {
					x = start.x;
					y = start.y + 2;
				}
				else continue;
				break;
			case 4://������
				start.left = true;
				if(start.x - 2 >= 0) {
					x = start.x - 2;
					y = start.y;
				}
				else continue;
				break;
			case 8://������
				start.up = true;
				if(start.y - 2 >= 0) {
					x = start.x;
					y = start.y - 2;
				}
				else continue;
				break;
			}
			
			/*�������յ�֮����ͨ·*/
			if(this.cells[start.x + (x - start.x) / 2 + (start.y + (y - start.y) / 2) * width].show != '#') {
				temp = this.cells[x + y * this.width];
				if((start.right && start.down && start.up && start.left) != true && temp == start.parent)//��ǰ��������û����֮ǰ����������
						continue;
				/*���յ㲻��ǽʱ*/
				if(temp.show != '#') {
					temp.parent = start;
					temp.show = '��';//��ʾ·��
					cellStack.push(temp);//���յ���ջ
					return cellStack.peek();//����ջ���Ķ���
				}
				else
					continue;//�յ���ǽ�ͻ�һ������
			}
			else 
				continue;//��������յ����һ��ǽ�ͻ�һ������
		}
		/*���ý��û����Ч��·��*/
		cellStack.pop();//����ջ������
		return cellStack.peek();//���ص�ǰջ������
	}
	
	/**�ֶ������Թ�*/
	public static Maze manualGenerate() throws Exception{
		String fileName = "maze.txt";
		String receiveString = new String();//�����ļ��е�һ���ַ���
		Scanner fileInput = new Scanner(new File(fileName));
		int row = 0, column = 0;//�ı����Թ��Ĺ��
		ArrayList<Character> charArr = new ArrayList<>();
		
		/*��ȡ�ļ�����ʼ���Թ�*/
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
	
	/**��������Թ�*/
	public Cell digPath(Cell start) {
		int x = 0, y = 0, dir = 0;
		Cell tCell;
		
		if(start == null)
			return null;
		
		while(start.dirs != 0) {
			dir = 1 << (int)((Math.random() * 10000) % 4);//���ָ��һ������
			
			if((int)(~start.dirs & dir) != 0)//���������·���ж���һ�η����Ƿ�����һ���ظ�	
				continue;
			start.dirs &= ~dir;//����ǰ����ķ�����ʽ��������
			
			/*�Թ�����һ��*/
			switch(dir) {
			
			case 1://������
				if(start.x + 2 < this.width) {
					x = start.x + 2;
					y = start.y;
				}
				else continue;
				break;
			case 2://������
				if(start.y + 2 < this.height) {
					x = start.x;
					y = start.y + 2;
				}
				else continue;
				break;
			case 4://������
				if(start.x - 2 >= 0) {
					x = start.x - 2;
					y = start.y;
				}
				else continue;
				break;
			case 8://������
				if(start.y - 2 >= 0) {
					x = start.x;
					y = start.y - 2;
				}
				else continue;
				break;
			}
			
			tCell = this.cells[x + y * this.width];
			
			/*�յ㲻��Ϊǽ*/
			if(tCell.show == ' ') {
				/*�յ�ĵ�Ԫ������Ѿ����Ӿ����*/
				if(tCell.parent != null) {
					continue;
				}
				tCell.parent = start;//�����յ������
				this.cells[start.x + (x - start.x) / 2 + (start.y + (y - start.y) / 2) * width].show = ' ';//ȥ��������յ�֮���ǽ
				return tCell;
			}
		}
		return start.parent;
	}
	
	/**�Զ������Թ������³�ʼ��ÿ����Ԫ��ķ�������Ѱ·*/
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
	
	/**����Թ�����״*/
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
	
	/**��ʼ���Թ�*/
	public Maze(int width, int height){	
		/*���ò���ʼ���Թ��ĳ��ȺͿ��*/
		this.width = width;
		this.height = height;
		cells = new Cell[this.width * this.height];
		
		/*Ϊÿ����Ԫ�����ռ�*/
		//for(int i = 0; i < width * height; i++) {
			//cells[i] = new Cell();
		//}
		
		/*Ϊÿ����Ԫ�����ռ䲢��ʼ��ÿ����Ԫ��*/
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
