/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Telas;

import Loja.BancoDados.ItemClienteDAO;
import Loja.BancoDados.ItemProdutoDAO;
import Loja.BancoDados.ItemVendaDAO;
import Loja.Registro.ItemCliente;
import Loja.Registro.ItemProduto;
import Loja.Registro.ItemVenda;
import Loja.Registro.Venda;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nilso
 */
public class TelaVendaController implements Initializable {

    @FXML
    private TextField tfPesquisa;
    @FXML
    private TableColumn<ItemProduto, String> colunaNome;
    @FXML
    private TableColumn<ItemProduto, String> colunaDescricao;
    @FXML
    private TableColumn<ItemProduto, Float> colunaPreco;
    @FXML
    private TableView<ItemProduto> tabelaProdutos;
    @FXML
    private TableView<ItemVenda> tabelaSacola;
    @FXML
    private TableColumn<ItemVenda, String> colunaProdutoVenda;
    @FXML
    private TableColumn<ItemVenda, Float> colunaPrecoVenda;
    @FXML
    private TableColumn<ItemVenda, Float> colunaQtdVenda;
    @FXML
    private TextField tfCliente;

    List<ItemVenda> listaDeVenda = new ArrayList<>();
    
//    float valorTotal = 0;
    int IdCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome_produto"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        
        colunaProdutoVenda.setCellValueFactory(new PropertyValueFactory("nome_produto"));
        colunaPrecoVenda.setCellValueFactory(new PropertyValueFactory("preco"));
        colunaQtdVenda.setCellValueFactory(new PropertyValueFactory("quantidade_produto"));
    }

    @FXML
    private void pesquisa(ActionEvent event) {
        if (tfPesquisa.getText().equals("")) {
            try {
                List<ItemProduto> resultados = ItemProdutoDAO.listar();

                tabelaProdutos.setItems(FXCollections.observableArrayList(resultados));
                tabelaProdutos.refresh();

            } catch (Exception e) {
                e.printStackTrace();

                alert("Erro", "Falha ao pesquisar", Alert.AlertType.INFORMATION);
            }
        } else {
            try {
                List<ItemProduto> resultados = ItemProdutoDAO.pesquisa(tfPesquisa.getText());

                tabelaProdutos.setItems(FXCollections.observableArrayList(resultados));
                tabelaProdutos.refresh();

            } catch (Exception e) {
                e.printStackTrace();

                alert("Erro", "Falha ao pesquisar", Alert.AlertType.ERROR);

            }

        }
    }

    @FXML
     private void acaoSacola(ActionEvent event){
        ItemVenda item = new ItemVenda();
        item.id = tabelaProdutos.getSelectionModel().getSelectedItem().getId();
        item.nome_produto = tabelaProdutos.getSelectionModel().getSelectedItem().getNome_produto();
        item.preco = tabelaProdutos.getSelectionModel().getSelectedItem().getPreco();
        item.quantidade_produto = 1;
        item.valor_total = tabelaProdutos.getSelectionModel().getSelectedItem().getPreco();
//        valorTotal += tabelaProdutos.getSelectionModel().getSelectedItem().getPreco();
        listaDeVenda.add(item);
        tabelaSacola.setItems(FXCollections.observableArrayList(listaDeVenda));
        
    }

    @FXML
    private void validar(ActionEvent event) throws Exception {
        
        if (tfCliente.getText().equals("")) {            
            alert("Erro", "Preenche o campo cliente", Alert.AlertType.INFORMATION);  
        } else {
            try {
                List<ItemCliente> resultados = ItemClienteDAO.Validar(tfCliente.getText());
                
                for(ItemCliente item : resultados){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Cliente Validor");
                    alert.setContentText("Nome Cliente: " + item.nome);
                    alert.showAndWait();
                    IdCliente = item.id;
                }
            } catch (Exception e) {
                e.printStackTrace();
                alert("Erro", "Falha ao pesquisar", Alert.AlertType.ERROR);
            }

        }

    }
    
    @FXML
    private void finalizarCompra(ActionEvent event) throws Exception {
        Venda venda = new Venda();
        LocalDate localDate = LocalDate.now();
        
        venda.id_cliente = IdCliente;
        venda.data_venda = Date.valueOf(localDate);
        
        ItemVendaDAO.finalizarCompra(venda, listaDeVenda);
        alert("Finalizando", "Compra efetuada", Alert.AlertType.CONFIRMATION);
        
        limpar(event);
    }
    
    @FXML
    private void limpar(ActionEvent event) {
        while (listaDeVenda.size() > 0) {
            listaDeVenda.remove(0);
        }
        tabelaSacola.setItems(FXCollections.observableArrayList(listaDeVenda));
    }

    @FXML
    private void excluir(ActionEvent event) {
        ItemVenda itemSelecionado = tabelaSacola.getSelectionModel().getSelectedItem();

        for ( int i = 0; i < listaDeVenda.size(); i++) {
            ItemVenda itemLista = listaDeVenda.get(i);
            if (itemLista.id == itemSelecionado.id) {
                listaDeVenda.remove(i);
                break;
            }
        }
        tabelaSacola.setItems(FXCollections.observableArrayList(listaDeVenda));
    }
    
    
    void alert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }




    
}
