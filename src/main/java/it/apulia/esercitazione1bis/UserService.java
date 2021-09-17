package it.apulia.esercitazione1bis;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
	HashMap<String, UtenteDTO> similDB;

	public UserService() {
		this.similDB = new HashMap<String, UtenteDTO>();
	}

	public boolean verifyEmail(String email) {
		return this.similDB.containsKey(email);
	}

	public boolean saveUser(UtenteDTO utente) {
		if(!this.verifyEmail(utente.getEmail())){
			this.similDB.put(utente.getEmail(),utente);
			return true;
		}
		return false; //c'è già un utente
	}

	public boolean verifyPasswordUtente(LoginDTO loginDTO) {
		//controlla che l'utente sia presente all'interno della hashmap
		if(this.verifyEmail(loginDTO.getEmail()))
		{
			//verifica che le password siano uguali
			if(this.similDB.get(loginDTO.getEmail()).getPassword().equals(loginDTO.getPassword()))
			{
				return true;
			}
			return false; //password errata
		}
		return false; //l'utente non è presente all'interno del sistema
	}


	public boolean verificaPassword(String psw, String vpsw) {
		return psw.equals(vpsw);
	}

	public List<UtenteDTO> getAllUsers(){
		return new ArrayList(this.similDB.values());
	}
}
