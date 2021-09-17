package it.apulia.esercitazione1bis;

import java.util.List;

public interface UserDAO {
        List<Utente> findAll();

        void insertUtente(Utente usr);

        void updateUtente(Utente usr);

        void executeUpdateUtente(Utente usr);

        public void deleteUtente(Utente usr);
}
