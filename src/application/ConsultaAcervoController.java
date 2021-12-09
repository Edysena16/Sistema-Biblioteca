package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Livros;
import util.Conexao;

public class ConsultaAcervoController {
	@FXML TextField txtNome;
	@FXML TextField txtGenero;
	@FXML TextField txtEditora;
	@FXML TextField txtAutor;
	@FXML TableView<Livros> tbl;
	@FXML TableColumn<Livros, Number> colId;
	@FXML TableColumn<Livros, Number> colNome;
	@FXML TableColumn<Livros, Number> colGenero;
	@FXML TableColumn<Livros, Number> colEditora;
	@FXML TableColumn<Livros, Number> colAutor;
	

	
	private ArrayList<Livros> livros = new ArrayList<Livros>();
	
	
	@FXML
	private void BucasLivros() {
		
		livros = new ArrayList<Livros>();
		String sql = "Selec * from livro order by nome";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	}
