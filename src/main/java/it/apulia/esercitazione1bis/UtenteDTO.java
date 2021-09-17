package it.apulia.esercitazione1bis;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
public class UtenteDTO {

	@Size(min = 3, max = 100, message
			= "About Me must be between 3 and 200 characters")
	String nome;
	@Size(min = 3, max = 100, message
			= "About Me must be between 3 and 200 characters")
	String cognome;
	@Email(message = "Email should be valid")
	String email;
	@Size(min = 6, max = 25, message
			= "About Me must be between 6 and 25 characters")
	String password;
	String vpassword;
	@AssertTrue
	boolean privacy = false;

}
