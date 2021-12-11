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
import model.Livro;
import util.Conexao;

public class ConsultaAcervoController {
	@FXML TextField txtBusca;
	@FXML TextField txtNome;//mapear area nome livro em sceneBuild
	@FXML TextField txtGenero;//mapear area nome livro em sceneBuild
	@FXML TextField txtEditora;//mapear area nome livro em sceneBuild
	@FXML TextField txtAutor;//mapear area nome livro em sceneBuild
	
	@FXML TextField txtFiltro;
	@FXML TableView<Livro> tbl;
	@FXML TableColumn<Livro, Number> colId;
	@FXML TableColumn<Livro, String> colNome;
	@FXML TableColumn<Livro, String> colGenero;
	@FXML TableColumn<Livro, String> colEditora;
	@FXML TableColumn<Livro, String> colAutor;



	private ArrayList<Livro> livros = new ArrayList<Livro>();
	private Livro livroSelecionado = null;

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

		livros = new ArrayList<Livro>();
	
		String sql = "Select * from livro order by nome";
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();//necessita desse comando para Select// como se fosse uma matriz, traz conjunto de dados
			//para cada linha, um next>>
			while(rs.next()) {
				Livro l = new Livro();
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
	public void add() {
		if (livroSelecionado == null) {
			addLivro();
		}else {
			alteraLivro();
			livroSelecionado = null;
			txtNome.setText("");
			txtGenero.setText("");
			txtEditora.setText("");
			txtAutor.setText("");
			
		}
	}
	

	@FXML
	public void filtraRodovias() {
		livros = new ArrayList<Livro>();
		String sql = "select * from livro where nome like ? order by id";
		try {
			Connection con = Conexao.conectaSqlite();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, txtFiltro.getText()+"%");//pegar o que for digitado pelo usuario
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Livro l = new Livro();
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
	
	private void addLivro() {
		//livros = new ArrayList<Livros>();
		Livro l = lerTela();//Qual livro que ele vai add>> Livros l = lerTela();
		String sql = "insert into livro(nome, genero, editora, autor) values (?, ?, ?, ?)";//inserir da tabela sqlite
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);//Preparar a conexão com o banco sql, vai mandar esse comando sql ao banco
			ps.setString(1, l.getNome());
			ps.setString(2, l.getGenero());
			ps.setString(3, l.getEditora());
			ps.setString(4, l.getAutor());
			ps.executeUpdate();//executar
			
			
			con.close();//Fechar conexão
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void alteraLivro() {
		//usuarios = new ArrayList<Usuario>();
		Livro l = lerTela();//Qual livro que ele vai add>> Livros l = lerTela();
		String sql = "update livro set nome=?, genero=?, editora=?, autor=? where id=?";//Altera da tabela sqlite
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);//Preparar a conexão com o banco sql, vai mandar esse comando sql ao banco
			
			ps.setString(1, l.getNome());
			ps.setString(2, l.getGenero());
			ps.setString(3, l.getEditora());
			ps.setString(4, l.getAutor());
			ps.setInt(5, l.getId());
			ps.executeUpdate();//executar
			
			
			con.close();//Fechar conexão
			buscarLivros();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void excluirLivro() {
		String sql = "delete from livro where id=?";//Altera da tabela sqlite
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);//Preparar a conexão com o banco sql, vai mandar esse comando sql ao banco
			
			ps.setInt(1, livroSelecionado.getId());
			ps.executeUpdate();//executar
			
			
			con.close();//Fechar conexão
			buscarLivros();
			txtNome.setText("");
			txtGenero.setText("");
			txtEditora.setText("");
			txtAutor.setText("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Livro lerTela() {
		//increment para selecionar tela, verificação 		
		Livro l = new Livro();//.b
		if(livroSelecionado != null) {
			l.setId(livroSelecionado.getId());// usuario select != de null, foi clicado, processo de clicar user
			
		}

		
		l.setNome(txtNome.getText());//Pega o text desse componente e jogar aqui dentro
		l.setGenero(txtGenero.getText());
		l.setEditora(txtEditora.getText());
		l.setAutor(txtAutor.getText());
		return l;
	}



}
