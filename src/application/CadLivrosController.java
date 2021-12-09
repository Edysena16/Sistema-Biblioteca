package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Livros;
import util.Conexao;
public class CadLivrosController {
	//Todos componentes a serem mapeados na tela
	@FXML TextField txtNome;//mapear area nome livro em sceneBuild
	@FXML TextField txtGenero;//mapear area Genero livro em sceneBuild
	@FXML TextField txtEditora;//mapear area Editora livro em sceneBuild
	@FXML TextField txtAutor;//mapear area author livro em sceneBuild
	
		
	//private ArrayList<Livros> livros = new ArrayList<Livros>();
/*	@FXML
	private void buscaLivros() {
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
*/
	
	
	@FXML
	public void add() {
		addLivros();
	}
	
	private void addLivros() {
		//livros = new ArrayList<Livros>();
		Livros l = lerTela();//Qual livro que ele vai add>> Livros l = lerTela();
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
	

	private Livros lerTela() {
		Livros l = new Livros();
		l.setNome(txtNome.getText());//Pega o text desse componente e jogar aqui dentro
		l.setGenero(txtGenero.getText());
		l.setEditora(txtEditora.getText());
		l.setAutor(txtAutor.getText());
		return l;
	}
}