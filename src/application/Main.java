package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application{
    
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/telas/tabuleiroRobo.fxml"));
        stage.setTitle("Jogo de Robô");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
