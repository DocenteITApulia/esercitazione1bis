package it.apulia.esercitazione1bis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
	//HashMap<String, Utente> similDB;

	private final UserDAOImpl userRepository;

	@Autowired
	public UserService(UserDAOImpl usrRep) {
		this.userRepository = usrRep;
		//this.similDB = new HashMap<String, Utente>();
	}

	public boolean verifyEmail(String email) {
		List<Utente> tmp = new ArrayList<Utente>(this.userRepository.findUser(email));

		if(tmp.isEmpty())
			return false;

		return true;
	}

	public boolean saveUser(UtenteDTO utente) {
		if(!this.verifyEmail(utente.getEmail())){
			Utente temp = new Utente(utente.nome,utente.cognome,utente.email,utente.password);
			this.userRepository.insertUtente(temp);
			return true;
		}
		return false; //c'è già un utente
	}

	public boolean verifyPasswordUtente(LoginDTO loginDTO) {
		//controlla che l'utente sia presente all'interno della hashmap
		if(this.verifyEmail(loginDTO.getEmail()))
		{
			//verifica che le password siano uguali
			//if(this.similDB.get(loginDTO.getEmail()).getPassword().equals(loginDTO.getPassword()))
			if(this.userRepository.findUser(loginDTO.getEmail()).get(0).getPassword().equals(loginDTO.getPassword()))
			{
				return true;
			}
			return false; //password errata
		}
		return false; //l'utente non è presente all'interno del sistema
	}

	public List<Utente> getAllUsers(){
		return new ArrayList(this.userRepository.findAll());
	}

	/**
	 * metodo richiamato anche da altri metodi per verificare che la mappa contenga l'indirizzo passato
	 * @param email è l'email da verificare
	 * @return booleano con esito dell'operazione
	 *//*
	public boolean verifyEmail(String email) {

		return this.similDB.containsKey(email);
	}
	*/
	/***
	 * Metodo che serve per il salvataggio dell'utente all'interno della mappa
	 * @param utente è il parametro ricevuto nel momento in cui l'utente fa la post del form
	 * @return è un booleano che indica l'esito della registrazione
	 */
	/*
	public boolean saveUser(UtenteDTO utente) {
		if(!this.verifyEmail(utente.getEmail())){
			Utente temp = new Utente(utente.nome,utente.cognome,utente.email,utente.password);
			this.similDB.put(utente.getEmail(),temp);
			return true;
		}
		return false; //c'è già un utente
	}*/

	/**
	 * è il metodo che viene richiamato in seguito alla post del form di login
	 * @param loginDTO è l'oggetto relativo al formm di login
	 * @return un booleano che indica l'esito del login
	 *//*
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
*/

	/**
	 * verifica, al momento della registrazione, che le password siano equivalenti
	 * @param psw è il primo campo, quello che verrà poi salvato
	 * @param vpsw è il secondo campo, che viene confrontato con il primo
	 * @return ritorna un booleano che indica se le password siano uguali o meno
	 */
	public boolean verificaPassword(String psw, String vpsw) {
		return psw.equals(vpsw);
	}

	/**
	 * Utilizzato per mostrare l'elenco degli utenti registrati (per puro scopo informativo)
	 * @return la lista degli utenti presenti a sistema (può essere anche vuota)
	 */
	/*public List<Utente> getAllUsers(){
		return new ArrayList(this.similDB.values());
	}*/

}
