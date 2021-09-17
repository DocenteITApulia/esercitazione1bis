package it.apulia.esercitazione1bis;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO{
    NamedParameterJdbcTemplate template;

    public UserDAOImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Utente> findAll() {
        return template.query("select * from users", new UserRowMapper());
    }

    public List<Utente> findUser(String email) {
        final String sql ="select * from users where email=:email";
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        return template.query(sql, param, new ResultSetExtractor<List<Utente>>() {
            @Override
            public List<Utente> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Utente> tempList = new ArrayList<Utente>();
                while(rs.next()){
                   // Utente usr = new Utente(rs.getString("userId"),rs.getString("nome"),
                    Utente usr = new Utente(rs.getString("nome"), rs.getString("cognome"),
                            rs.getString("email"),rs.getString("password"));
                    tempList.add(usr);
                    break;
                }
                return tempList;
            }
        });
    }

    @Override
    public void insertUtente(Utente usr) {

        //final String sql = "insert into users(userId, nome , cognome, email, password) values(:userId,:nome,:cognome,:email,:password)";
        final String sql = "insert into users(nome , cognome, email, password) values(:nome,:cognome,:email,:password)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                //.addValue("userId", usr.getUserId())
                .addValue("nome",usr.getNome())
                .addValue("cognome",usr.getCognome() )
                .addValue("email",usr.getEmail())
                .addValue("password",usr.getPassword());
        template.update(sql,param, holder);
    }

    @Override
    public void updateUtente(Utente usr) {
        //final String sql = "update users set userId=:userId, nome=:nome, cognome=:cognome, email=:email, password=:password, where userId=:userId";
        final String sql = "update users set nome=:nome, cognome=:cognome, email=:email, password=:password, where email=:email";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
              //  .addValue("userId", usr.getUserId())
                .addValue("nome",usr.getNome())
                .addValue("cognome",usr.getCognome() )
                .addValue("email",usr.getEmail())
                .addValue("password",usr.getPassword());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateUtente(Utente usr) {
        final String sql = "update users set nome=:nome, cognome=:cognome, email=:email, password=:password, where email=:email";

        Map<String,Object> map=new HashMap<String,Object>();
     //   map.put("userId",usr.getUserId());
        map.put("nome", usr.getNome());
        map.put("cognome", usr.getCognome());
        map.put("email", usr.getEmail());
        map.put("password", usr.getPassword());


        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });


    }

    @Override
    public void deleteUtente(Utente usr) {
        final String sql = "delete from users where email=:email";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("email",usr.getEmail());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });

    }
}
