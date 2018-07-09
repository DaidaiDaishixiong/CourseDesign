package UserInterface;

import MazeGenerator.Maze;
import javafx.application.Application;
import javafx.scene.paint.Color;//有分歧的路径
import javafx.scene.layout.Pane;//迷宫面板
import javafx.scene.layout.HBox;//横坐标轴
import javafx.scene.layout.VBox;//纵坐标轴
import javafx.scene.layout.BorderPane;//迷宫主面板
import javafx.scene.shape.Line;//画迷宫
import javafx.scene.control.Button;//按钮(确定)
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;//选择寻路的方式
/*动画*/
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MazeInterface extends Application {
	
	public static Pane setMaze(Maze maze) {
		int x = 3, y =3;//设置原点位置
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
