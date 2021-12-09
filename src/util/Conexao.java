package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
	public static Connection conectaSqlite() {
		Connection con = null;
		try {
			File arquivo = new File("database/acervoDig.sqlite");
			if(arquivo.exists()) {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:database/acervoDig.sqlite");
			}
		} catch (Exception e) {
			e.printStackTrace();//Tratamento de Excessao comum do Java;; toda seq que foi executada, vai ser mostrada
		}
		return con;
	}

}

//TESTE CONEXAO...
/*
@FXML
public void testeconexao() {
	Connection con = Conexao.conectaSqlite();
	if(con != null)
		System.out.println("Ok");
	else
		System.out.println("EROU!!!");
}
*/