package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	//Metodos do JavaFX para trabalhar com table
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty endereco = new SimpleStringProperty("");
	public IntegerProperty idProperty() {
		return this.id;
	}
	public final int getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final StringProperty telefoneProperty() {
		return this.telefone;
	}
	
	public final String getTelefone() {
		return this.telefoneProperty().get();
	}
	
	public final void setTelefone(final String telefone) {
		this.telefoneProperty().set(telefone);
	}
	
	public final StringProperty emailProperty() {
		return this.email;
	}
	
	public final String getEmail() {
		return this.emailProperty().get();
	}
	
	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	public final StringProperty enderecoProperty() {
		return this.endereco;
	}
	
	public final String getEndereco() {
		return this.enderecoProperty().get();
	}
	
	public final void setEndereco(final String endereco) {
		this.enderecoProperty().set(endereco);
	}
	
	
}