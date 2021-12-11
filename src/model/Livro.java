package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Livro {
	
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty genero = new SimpleStringProperty("");
	private StringProperty editora = new SimpleStringProperty("");
	private StringProperty autor = new SimpleStringProperty("");
	public final IntegerProperty idProperty() {
		return this.id;
	}
	//Metodos getter and setter do javaFX
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
	
	public final StringProperty generoProperty() {
		return this.genero;
	}
	
	public final String getGenero() {
		return this.generoProperty().get();
	}
	
	public final void setGenero(final String genero) {
		this.generoProperty().set(genero);
	}
	
	public final StringProperty editoraProperty() {
		return this.editora;
	}
	
	public final String getEditora() {
		return this.editoraProperty().get();
	}
	
	public final void setEditora(final String editora) {
		this.editoraProperty().set(editora);
	}
	
	public final StringProperty autorProperty() {
		return this.autor;
	}
	
	public final String getAutor() {
		return this.autorProperty().get();
	}
	
	public final void setAutor(final String autor) {
		this.autorProperty().set(autor);
	}
	
	
	
	
	
}
