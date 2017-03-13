package ua.cn.al.teach.figures;

import java.io.Serializable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application implements Serializable{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/mainFxml.fxml"));
        Parent root = loader.load();  
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("JavaFX Figures");
        stage.setScene(scene);
        stage.show();
        ((FXMLController)loader.getController()).setListener((BorderPane)root);
    }
}
