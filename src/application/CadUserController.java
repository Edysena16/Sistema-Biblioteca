package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
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
		
	@FXML
	public void buscarUsuarios() {
		
	}
	
	@FXML
	public void add() {
		addUsuarios();
	}
	private void addUsuarios() {
		//usuarios = new ArrayList<Usuario>();
		Usuario u = lerTela();//Qual livro que ele vai add>> Livros l = lerTela();
		String sql = "insert into usuario(nome, email, telefone, endereco) values (?, ?, ?, ?)";//inserir da tabela sqlite
		try {
			Connection con = Conexao.conectaSqlite();//Conectar Conexão
			PreparedStatement ps = con.prepareStatement(sql);//Preparar a conexão com o banco sql, vai mandar esse comando sql ao banco
			ps.setString(1, u.getNome());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getTelefone());
			ps.setString(4, u.getEndereco());
			ps.executeUpdate();//executar
			
			
			con.close();//Fechar conexão
			buscarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Usuario lerTela() {
		Usuario u = new Usuario();
		
		u.setNome(txtNome.getText());//Pega o text desse componente e jogar aqui dentro
		u.setEmail(txtEmail.getText());
		u.setTelefone(txtTelefone.getText());
		u.setEndereco(txtEndereco.getText());
		return u;
	}
}
