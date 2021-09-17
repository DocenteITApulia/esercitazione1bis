package it.apulia.esercitazione1bis;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Utente> {
    @Override
    public Utente mapRow(ResultSet rs, int arg1) throws SQLException {
        Utente usr = new Utente();
      //  usr.setUserId(rs.getString("userId"));
        usr.setNome(rs.getString("nome"));
        usr.setCognome(rs.getString("cognome"));
        usr.setEmail(rs.getString("email"));
        usr.setPassword(rs.getString("password"));

        return usr;
    }
}
