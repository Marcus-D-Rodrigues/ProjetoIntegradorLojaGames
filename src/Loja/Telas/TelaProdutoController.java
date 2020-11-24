/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Telas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nilso
 */
public class TelaProdutoController implements Initializable {

    @FXML
    private TextField tfNomeProduto;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private TextArea taDescricao;
    @FXML
    private TextField tfPreco;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaQuantidade;
    @FXML
    private TableColumn<?, ?> colunaPreco;
    @FXML
    private TextField tfPesquisa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void limpar(ActionEvent event) {
    }

    @FXML
    private void salvar(ActionEvent event) {
    }

    @FXML
    private void excluir(ActionEvent event) {
    }

    @FXML
    private void editar(ActionEvent event) {
    }

    @FXML
    private void pesquisa(ActionEvent event) {
    }

    
}
