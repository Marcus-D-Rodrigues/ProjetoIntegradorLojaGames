/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Telas;

import Loja.BancoDados.ItemClienteDAO;
import Loja.Registro.ItemCliente;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class TelaClienteController implements Initializable {

    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfSobrenome;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private TextField tfRg;
    @FXML
    private TextField tfCpf;
    @FXML
    private ComboBox<String> comboGenero;
    @FXML
    private ComboBox<String> comboEstadoCivil;
    @FXML
    private TextField tfCidade;
    @FXML
    private TextField tfBairro;
    @FXML
    private TextField tfCep;
    @FXML
    private TextField tfEndereco;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfComplemento;
    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfCelular;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField tfPesquisar;
    @FXML
    private TableView<ItemCliente> tabela;
    @FXML
    private TableColumn<ItemCliente, String> colunaNome;
    @FXML
    private TableColumn<ItemCliente, String> colunaCpf;
    
    boolean editMode = false;
    List<ItemCliente> listaCliente = new ArrayList<>();
    ItemCliente itemClienteEdicao = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome" + "sobrenome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        
        comboGenero.getItems().add("Masculino");
        comboGenero.getItems().add("Feminino");
        comboGenero.getItems().add("outro");
        
        comboEstadoCivil.getItems().add("Casado");
        comboEstadoCivil.getItems().add("Solteiro");
        comboEstadoCivil.getItems().add("outro");
    }    

    @FXML
    private void salvar(ActionEvent event) {
        if (!editMode) {
            ItemCliente item = new ItemCliente();

            item.nome = tfNome.getText();
            item.sobrenome= tfSobrenome.getText();
            item.rg = tfRg.getText();
            item.cpf = tfCpf.getText();
            item.cep = tfCep.getText();
            item.cidade = tfCidade.getText();
            item.bairro = tfBairro.getText();
            item.complemento = tfComplemento.getText();
            item.endereco = tfEndereco.getText();
            item.telefone= tfTelefone.getText();
            item.celular = tfCelular.getText();
            item.email = tfEmail.getText();

            try {
                ItemClienteDAO.inserir(item);
                alert("Inserido", "Cliente inserido", Alert.AlertType.INFORMATION);

            } catch (Exception e) {
                e.printStackTrace();
                alert("Erro", "Falha ao salvar", Alert.AlertType.ERROR);
            }

        } else {
            itemClienteEdicao.nome = tfNome.getText();
            itemClienteEdicao.sobrenome = tfSobrenome.getText();
            itemClienteEdicao.rg = tfRg.getText();
            itemClienteEdicao.cpf = tfCpf.getText();
            itemClienteEdicao.cep = tfCep.getText();
            itemClienteEdicao.bairro = tfBairro.getText();
            itemClienteEdicao.complemento = tfComplemento.getText();
            itemClienteEdicao.endereco = tfEndereco.getText();
            itemClienteEdicao.numero = tfNumero.getText();
            itemClienteEdicao.telefone = tfTelefone.getText();
            itemClienteEdicao.celular = tfCelular.getText();
            itemClienteEdicao.email = tfEmail.getText();

            for (int i = 0; i < listaCliente.size(); i++) {
                ItemCliente itemLista = listaCliente.get(i);
                if (itemLista.id == itemClienteEdicao.id) {
                    listaCliente.set(i, itemClienteEdicao);
                    break;
                }
            }

            alert("Editado", "Cliente editado", Alert.AlertType.INFORMATION);

        }

        limpar(event);
        pesquisar(event);
    }

    @FXML
    private void limpar(ActionEvent event) {
        tfNome.clear();
        tfSobrenome.clear();
        tfRg.clear();
        tfCpf.clear();
        tfCep.clear();
        tfCidade.clear();
        tfBairro.clear();
        tfComplemento.clear();
        tfEndereco.clear();
        tfNumero.clear();
        tfTelefone.clear();
        tfCelular.clear();
        tfEmail.clear();
        comboGenero.setValue("");
        comboEstadoCivil.setValue("");
        dataNascimento.setValue(null);
        btnSalvar.setText("Inserir");
    }

    @FXML
    private void pesquisar(ActionEvent event) {
        if (tfPesquisar.getText().equals("")) {
            try {
                List<ItemCliente> resultados = ItemClienteDAO.listar();

                tabela.setItems(FXCollections.observableArrayList(resultados));
                tabela.refresh();

            } catch (Exception e) {
                e.printStackTrace();

                alert("Erro", "Falha ao salvar", Alert.AlertType.INFORMATION);
    }
        }else {
            List<ItemCliente> listaResultado = new ArrayList<>();

            for (int i = 0; i < listaCliente.size(); i++) {
                ItemCliente itemLista = listaCliente.get(i);
                if (itemLista.nome.contains(tfPesquisar.getText())) {
                    listaResultado.add(itemLista);
                }
            }
            tabela.setItems(FXCollections.observableArrayList(listaCliente));
            tabela.refresh();
        }
    }

    @FXML
    private void excluir(ActionEvent event) {
         ItemCliente itensSelecionados = tabela.getSelectionModel().getSelectedItem();

        if (itensSelecionados != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Remoção");
            alert.setContentText("Remover o item " + itensSelecionados.nome);

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == ButtonType.OK) {
                for (int i = 0; i < listaCliente.size(); i++) {
                    ItemCliente itemLista = listaCliente.get(i);
                    if (itemLista.id == itensSelecionados.id) {
                        listaCliente.remove(i);
                        break;
                    }
                }
            }

            pesquisar(event);
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        ItemCliente itensSelecionados = tabela.getSelectionModel().getSelectedItem();

        if (itensSelecionados != null) {

            editMode = true;

            itemClienteEdicao = itensSelecionados;

            tfNome.setText(itemClienteEdicao.nome);
            tfSobrenome.setText(itemClienteEdicao.sobrenome);
            tfRg.setText(itemClienteEdicao.rg);
            tfCpf.setText(itemClienteEdicao.cpf);
            tfCep.setText(itemClienteEdicao.cep);
            tfCidade.setText(itemClienteEdicao.cidade);
            tfBairro.setText(itemClienteEdicao.bairro);
            tfComplemento.setText(itemClienteEdicao.complemento);
            tfEndereco.setText(itemClienteEdicao.endereco);
            tfNumero.setText(itemClienteEdicao.numero);           
            tfTelefone.setText(itemClienteEdicao.telefone);
            tfCelular.setText(itemClienteEdicao.celular);
            tfEmail.setText(itemClienteEdicao.email);

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
