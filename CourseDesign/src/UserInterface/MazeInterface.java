package UserInterface;

import MazeGenerator.Maze;
import javafx.application.Application;
import javafx.scene.paint.Color;//�з����·��
import javafx.scene.layout.Pane;//�Թ����
import javafx.scene.layout.HBox;//��������
import javafx.scene.layout.VBox;//��������
import javafx.scene.layout.BorderPane;//�Թ������
import javafx.scene.shape.Line;//���Թ�
import javafx.scene.control.Button;//��ť(ȷ��)
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;//ѡ��Ѱ·�ķ�ʽ
/*����*/
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MazeInterface extends Application {
	
	public static Pane setMaze(Maze maze) {
		int x = 3, y =3;//����ԭ��λ��
		for(int i = 1; i < maze.width - 1; i++) {
			for(int j = 1; j < maze.height - 1; j++) {
				if(maze.cells[i + j * maze.width].show == '#') {
						
					}
				}
			}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
