
package Loja.APP;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aplicacao extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) throws IOException{
        URL url = getClass().getResource("/Loja/Telas/TelaMenu.fxml");
        
        Parent tela = FXMLLoader.load(url);
        
        Scene cena = new Scene(tela);
        
        stage.setScene(cena);
        
        stage.show();
    }
    
}
