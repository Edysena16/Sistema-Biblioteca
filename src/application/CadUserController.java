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
import model.Usuario;
import util.Conexao;

public class CadUserController {

	//Todos componentes a serem mapeados na tela
	@FXML TextField txtId;
	@FXML TextField txtNome;//mapear area nome livro em sceneBuild
	@FXML TextField txtEmail;//mapear area email livro em sceneBuild
	@FXML TextField txtTelefone;//mapear area telefone livro em sceneBuild
	@FXML TextField txtEndereco;//mapear area author endereco em sceneBuild
	@FXML TableView<Usuario> tbl;
	@FXML TableColumn<Usuario, Number> colId;
	@FXML TableColumn<Usuario, String> colNome;
	@FXML TableColumn<Usuario, String> colEmail;
	@FXML TableColumn<Usuario, String> colEndereco;
	@FXML TableColumn<Usuario, String> colTelefone;

	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private Usuario usuarioSelecionado = null;//variavel
	

	@FXML
	public void initialize() {
		colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		colTelefone.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
		colEndereco.setCellValueFactory(cellData -> cellData.getValue().enderecoProperty());
		buscarUsuarios();
	}
		
	@FXML
	public void buscarUsuarios() {
		usuarios = new ArrayList<Usuario>();

		String sql = "Select * from usuario order by id";
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();//necessita desse comando para Select// como se fosse uma matriz, traz conjunto de dados
			//para cada linha, um next>>
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setTelefone(rs.getString("telefone"));
				u.setEndereco(rs.getString("endereco"));
				usuarios.add(u);

			}
			con.close();
			tbl.setItems(FXCollections.observableArrayList(usuarios));//Converter ArrayListe em tabela 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	@FXML
	public void add() {
		if (usuarioSelecionado == null) {
			addUsuarios();
		}else {
			alteraUsuarios();
			usuarioSelecionado = null;
			txtNome.setText("");
			txtEmail.setText("");
			txtTelefone.setText("");
			txtEndereco.setText("");
			
		}
	}
	
	@FXML
	public void selecionaRegistro() {
		if(tbl.getSelectionModel().getSelectedItem() != null);//Se o item select for dif de null, sig que clicou no reg
		usuarioSelecionado = tbl.getSelectionModel().getSelectedItem();
		txtId.setText(usuarioSelecionado.getId()+"");//Gambiarra de conversao no java um inteiro somado com string
		txtNome.setText(usuarioSelecionado.getNome());
		txtEmail.setText(usuarioSelecionado.getEmail());
		txtTelefone.setText(usuarioSelecionado.getTelefone());
		txtEndereco.setText(usuarioSelecionado.getEndereco());
		
	}
	
	private void addUsuarios() {
		//usuarios = new ArrayList<Usuario>();
		Usuario u = lerTela();//Qual livro que ele vai add>> Livros l = lerTela();
		String sql = "insert into usuario(id, nome, email, telefone, endereco) values (?, ?, ?, ?, ?)";//inserir da tabela sqlite
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);//Preparar a conexão com o banco sql, vai mandar esse comando sql ao banco
			
			ps.setString(2, u.getNome());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getTelefone());
			ps.setString(5, u.getEndereco());
			ps.executeUpdate();//executar
			
			
			con.close();//Fechar conexão
			buscarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//metodo alterar usuario
	private void alteraUsuarios() {
		//usuarios = new ArrayList<Usuario>();
		Usuario u = lerTela();//Qual livro que ele vai add>> Livros l = lerTela();
		String sql = "update usuario set nome=?, email=?, telefone=?, endereco=? where id=?";//Altera da tabela sqlite
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);//Preparar a conexão com o banco sql, vai mandar esse comando sql ao banco
			
			ps.setString(1, u.getNome());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getTelefone());
			ps.setString(4, u.getEndereco());
			ps.setInt(5, u.getId());
			ps.executeUpdate();//executar
			
			
			con.close();//Fechar conexão
			buscarUsuarios();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void excluirUsuarios() {
		String sql = "delete from usuario where id=?";//Altera da tabela sqlite
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);//Preparar a conexão com o banco sql, vai mandar esse comando sql ao banco
			
			ps.setInt(1, usuarioSelecionado.getId());
			ps.executeUpdate();//executar
			
			
			con.close();//Fechar conexão
			buscarUsuarios();
			txtNome.setText("");
			txtEmail.setText("");
			txtTelefone.setText("");
			txtEndereco.setText("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Usuario lerTela() {
		//increment para selecionar tela, verificação 		
		Usuario u = new Usuario();//.b
		if(usuarioSelecionado != null) {
			u.setId(usuarioSelecionado.getId());// usuario select != de null, foi clicado, processo de clicar user
			
		}

		
		u.setNome(txtNome.getText());//Pega o text desse componente e jogar aqui dentro
		u.setEmail(txtEmail.getText());
		u.setTelefone(txtTelefone.getText());
		u.setEndereco(txtEndereco.getText());
		return u;
	}
}
