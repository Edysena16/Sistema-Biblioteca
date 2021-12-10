package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Livros;
import util.Conexao;

public class ConsultaAcervoController {
	@FXML TextField txtBusca;
	@FXML TextField txtNome;//mapear area nome livro em sceneBuild
	
	@FXML TextField txtFiltro;
	@FXML TableView<Livros> tbl;
	@FXML TableColumn<Livros, Number> colId;
	@FXML TableColumn<Livros, String> colNome;
	@FXML TableColumn<Livros, String> colGenero;
	@FXML TableColumn<Livros, String> colEditora;
	@FXML TableColumn<Livros, String> colAutor;



	private ArrayList<Livros> livros = new ArrayList<Livros>();
	private Livros livroSelecionado = null;

	@FXML
	public void initialize() {
		colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colGenero.setCellValueFactory(cellData -> cellData.getValue().generoProperty());
		colEditora.setCellValueFactory(cellData -> cellData.getValue().editoraProperty());
		colAutor.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
		buscarLivros();
	}
	
	@FXML
	public void selecionaRegistro() {
		if(tbl.getSelectionModel().getSelectedItem() != null);//Se o item select for dif de null, sig que clicou no reg
		livroSelecionado = tbl.getSelectionModel().getSelectedItem();
		txtBusca.setText(livroSelecionado.getNome());
		
	}
	@FXML
	private void buscarLivros() {

		livros = new ArrayList<Livros>();
	
		String sql = "Select * from livro order by nome";
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();//necessita desse comando para Select// como se fosse uma matriz, traz conjunto de dados
			//para cada linha, um next>>
			while(rs.next()) {
				Livros l = new Livros();
				l.setId(rs.getInt("id"));
				l.setNome(rs.getString("nome"));
				l.setGenero(rs.getString("genero"));
				l.setEditora(rs.getString("editora"));
				l.setAutor(rs.getString("autor"));
				livros.add(l);

			}
			con.close();
			tbl.setItems(FXCollections.observableArrayList(livros));//Converter ArrayListe em tabela 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void filtraRodovias() {
		livros = new ArrayList<Livros>();
		String sql = "select * from livro where nome like ? order by id";
		try {
			Connection con = Conexao.conectaSqlite();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, txtFiltro.getText()+"%");//pegar o que for digitado pelo usuario
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Livros l = new Livros();
				l.setId(rs.getInt("id"));
				l.setNome(rs.getString("nome"));
				l.setGenero(rs.getString("genero"));
				l.setEditora(rs.getString("editora"));
				l.setAutor(rs.getString("autor"));
				livros.add(l);
			}
			con.close();
			tbl.setItems(FXCollections.observableArrayList(livros));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
