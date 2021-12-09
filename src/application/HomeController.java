package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HomeController {

	@FXML
	TabPane pane;

	@FXML
	public void abreCadastroLivros() {

		try {
			boolean aberta = false;
			for (Tab tb : pane.getTabs()) {
				if (tb.getText().equals("Cadastro de Livros")) {
					aberta = true;
					pane.getSelectionModel().select(tb);
				}
			}
			
			if (!aberta) {

				Tab tab = new Tab("Cadastro de Livros");
				tab.setClosable(true);
				pane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource("CadLivros.fxml")));
				pane.getSelectionModel().select(tab);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void abreCadastroUsuarios() {

		try {
			boolean aberta = false;
			for (Tab tb : pane.getTabs()) {
				if (tb.getText().equals("Cadastro de Usuários")) {
					aberta = true;
					pane.getSelectionModel().select(tb);
				}
			}
			
			if (!aberta) {

				Tab tab = new Tab("Cadastro de Usuários");
				tab.setClosable(true);
				pane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource("CadUser.fxml")));
				pane.getSelectionModel().select(tab);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void abreAcervo() {

		try {
			boolean aberta = false;
			for (Tab tb : pane.getTabs()) {
				if (tb.getText().equals("Consulta Acervo")) {
					aberta = true;
					pane.getSelectionModel().select(tb);
				}
			}
			
			if (!aberta) {

				Tab tab = new Tab("Consulta Acervo");
				tab.setClosable(true);
				pane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource("ConsultaAcervo.fxml")));
				pane.getSelectionModel().select(tab);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
	




	