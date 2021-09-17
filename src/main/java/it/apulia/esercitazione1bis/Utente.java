package it.apulia.esercitazione1bis;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
public class Utente {

	//String userId;
	String nome;
	String cognome;
	String email;
	String password;

	/*
	public Utente(String userId, String nome, String cognome, String email, String password) {
		this.userId = userId;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}*/

	public Utente(){

	}
	public Utente(String nome, String cognome, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}
}
