/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Telas;

import Loja.BancoDados.ItemRegistroDAO;
import Loja.Registro.ItemRegistro;
import com.mysql.cj.util.StringUtils;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dell i5
 */
public class TelaRelatorioController implements Initializable {

    @FXML
    private TextField tfPesquisar;
    @FXML
    private TableView<ItemRegistro> tabela;
    @FXML
    private TableColumn<ItemRegistro, String> colunaCliente;
    @FXML
    private TableColumn<ItemRegistro, String> colunaProduto;
    @FXML
    private TableColumn<ItemRegistro, String> colunaQuantidade;
    @FXML
    private TableColumn<ItemRegistro, Float> colunaValor;
    @FXML
    private TextField periodo;
    @FXML
    private TextField total;
    @FXML
    private DatePicker dataInicial;
    @FXML
    private DatePicker dataFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        colunaProduto.setCellValueFactory(new PropertyValueFactory("produtos"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        colunaValor.setCellValueFactory(new PropertyValueFactory("valor"));

    }

    @FXML
    private void pesquisar(ActionEvent event) {

        if (StringUtils.isNullOrEmpty(tfPesquisar.getText()) || Objects.isNull(dataInicial.getValue()) || Objects.isNull(dataFinal.getValue())) {
            alert("Erro", "Campos obrigatorios vazios", Alert.AlertType.ERROR);
        } else {

            try {
                List<ItemRegistro> resultados = ItemRegistroDAO.pesquisar(tfPesquisar.getText(), Date.valueOf(dataInicial.getValue()), Date.valueOf(dataFinal.getValue()));

                if (resultados.isEmpty()) {
                    alert("Erro", "Não foi localizado dados", Alert.AlertType.ERROR);
                } else {

                    periodo.setText("Periodo "
                            + dataInicial.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - "
                            + dataFinal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                    Double valorTotal = resultados.stream().mapToDouble(ItemRegistro::getValor).sum();
                    total.setText(valorTotal.toString());

                    tabela.setItems(FXCollections.observableArrayList(resultados));
                    tabela.refresh();
                }

            } catch (Exception e) {
                e.printStackTrace();

                alert("Erro", "Falha ao pesquisar", Alert.AlertType.ERROR);

            }
        }

    }

    void alert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
