/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Telas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell i5
 */
public class TelaMenuController implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cliente(ActionEvent event) throws Exception{
        URL url = getClass().getResource("/Loja/Telas/TelaCliente.fxml");
        
        Parent tela = FXMLLoader.load(url);
        
        Scene cena = new Scene(tela);
        
        Stage stage = new Stage();
        
        stage.setScene(cena);
        
        stage.show();
        
    
}
    @FXML
    private void produto(ActionEvent event) {
    }

    @FXML
    private void venda(ActionEvent event) {
    }

    @FXML
    private void relatorio(ActionEvent event) {
    }
    
}
