package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Livros;
import util.Conexao;

public class ConsultaAcervoController {
	
	@FXML TableView<Livros> tbl;
	@FXML TableColumn<Livros, Number> colId;
	@FXML TableColumn<Livros, String> colNome;
	@FXML TableColumn<Livros, String> colGenero;
	@FXML TableColumn<Livros, String> colEditora;
	@FXML TableColumn<Livros, String> colAutor;



	private ArrayList<Livros> livros = new ArrayList<Livros>();

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

}
