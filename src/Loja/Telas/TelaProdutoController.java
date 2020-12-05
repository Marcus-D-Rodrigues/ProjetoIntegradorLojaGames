/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Telas;

import Loja.BancoDados.ItemProdutoDAO;
import Loja.Registro.ItemProduto;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TextField tfCriador;
    @FXML
    private TextField tfPlataforma;
    @FXML
    private TextField tfGeneros;
    @FXML
    private TextField tfPesquisa;
    @FXML
    private TableView<ItemProduto> tabela;
    @FXML
    private TableColumn<ItemProduto, String> colunaNome;
    @FXML
    private TableColumn<ItemProduto, Float> colunaQuantidade;
    @FXML
    private TableColumn<ItemProduto, Float> colunaPreco;
    @FXML
    private Button btnSalvar;
    
    boolean editMode = false;
    ItemProduto itemProdutoEdita = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome_produto"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade_produto"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory("preco"));
    }

    @FXML
    private void limpar(ActionEvent event) {
        tfNomeProduto.clear();
        tfQuantidade.clear();
        taDescricao.clear();
        tfPreco.clear();
        tfCriador.clear();
        tfPlataforma.clear();
        tfGeneros.clear();
        btnSalvar.setText("Inserir");
    }

    @FXML
    private void salvar(ActionEvent event) {
        if ("".equals(tfNomeProduto.getText()) || tfCriador.getText() == "" || tfPlataforma.getText() == "" || taDescricao.getText() == "" || tfGeneros.getText() == "" || tfQuantidade.getText() == "" ||tfPreco.getText() == ""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Infomações");
            alert.setHeaderText("Preencha todos os campos");
        }
      
       else if (!editMode) {
            
            ItemProduto item = new ItemProduto();

            item.nome_produto = tfNomeProduto.getText();
            item.nome_criador = tfCriador.getText();
            item.plataforma = tfPlataforma.getText();
            item.generos = tfGeneros.getText();
            item.descricao = taDescricao.getText();
            item.quantidade_produto = Integer.parseInt(tfQuantidade.getText());
            item.preco = Float.parseFloat(tfPreco.getText());
            

            try {
                ItemProdutoDAO.inserir(item);
                alert("Inserido", "Produto inserido", AlertType.INFORMATION);

            } catch (Exception e) {
                e.printStackTrace();
                alert("Erro", "Falha ao salvar", AlertType.ERROR);
            }

        } else {
            
            if ("".equals(tfNomeProduto.getText()) || tfCriador.getText() == "" || tfPlataforma.getText() == "" || taDescricao.getText() == "" || tfGeneros.getText() == "" || tfQuantidade.getText() == "" ||tfPreco.getText() == ""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Infomações");
            alert.setHeaderText("Preencha todos os campos");
        }
            itemProdutoEdita.nome_produto = tfNomeProduto.getText();
            itemProdutoEdita.nome_criador = tfCriador.getText();
            itemProdutoEdita.plataforma = tfPlataforma.getText();
            itemProdutoEdita.generos = tfGeneros.getText();
            itemProdutoEdita.descricao = taDescricao.getText();
            itemProdutoEdita.quantidade_produto = Integer.parseInt(tfQuantidade.getText());
            itemProdutoEdita.preco = Float.parseFloat(tfPreco.getText());

            try {
                ItemProdutoDAO.editar(itemProdutoEdita);
                alert("Editado", "produto editado", AlertType.INFORMATION);

            } catch (Exception e) {
                e.printStackTrace();
                alert("Erro", "Falha ao salvar", AlertType.ERROR);
            }
        }
        
        limpar(event);
        pesquisa(event);
    }

    @FXML
    private void pesquisa(ActionEvent event) {
        if (tfPesquisa.getText().equals("")) {
            try {
                List<ItemProduto> resultados = ItemProdutoDAO.listar();

                tabela.setItems(FXCollections.observableArrayList(resultados));
                tabela.refresh();

            } catch (Exception e) {
                e.printStackTrace();

                alert("Erro", "Falha ao pesquisar", Alert.AlertType.INFORMATION);
            }
        } else {
            try {
                List<ItemProduto> resultados = ItemProdutoDAO.pesquisa(tfPesquisa.getText());

                tabela.setItems(FXCollections.observableArrayList(resultados));
                tabela.refresh();

            } catch (Exception e) {
                e.printStackTrace();

                alert("Erro", "Falha ao pesquisar", Alert.AlertType.ERROR);

            }

        }
    }

    @FXML
    private void excluir(ActionEvent event) {
        ItemProduto itensSelecionados = tabela.getSelectionModel().getSelectedItem();

        if (itensSelecionados != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirma remoção");
            alert.setContentText("Remover o item " + itensSelecionados.nome_produto);

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == ButtonType.OK) {
                try {
                ItemProdutoDAO.excluir(itensSelecionados.id);
                alert("Excluído", "Produto excluído", AlertType.INFORMATION);

            } catch (Exception e) {
                e.printStackTrace();
                alert("Erro", "Falha ao excluir", AlertType.ERROR);
            }
                
            }

            pesquisa(event);
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        ItemProduto itensSelecionados = tabela.getSelectionModel().getSelectedItem();

        if (itensSelecionados != null) {

            editMode = true;

            itemProdutoEdita = itensSelecionados;
            tfNomeProduto.setText(itemProdutoEdita.nome_produto);
            tfCriador.setText(itemProdutoEdita.nome_criador);
            tfPlataforma.setText(itemProdutoEdita.plataforma);
            tfGeneros.setText(itemProdutoEdita.generos);
            taDescricao.setText(itemProdutoEdita.descricao);
            tfQuantidade.setText(itemProdutoEdita.quantidade_produto + "");
            tfPreco.setText(itemProdutoEdita.preco + "");

            btnSalvar.setText("Salvar");

        }
    }
    
    void alert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
