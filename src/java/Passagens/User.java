package Passagens;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String login;
    private String passwordHash;
    
    public static User getUser(String login, String password) throws SQLException{
        String SQL = "SELECT * FROM users WHERE login=? AND pass_hash=?";
        PreparedStatement s = Database.getConnection().prepareStatement(SQL);
        s.setString(1, login);
        s.setString(2, password.hashCode()+"");
        ResultSet rs = s.executeQuery();
        User u = null;
        if(rs.next()){
            u = new User(rs.getInt("id")
                    , rs.getString("name")
                    , rs.getString("login")
                    , rs.getString("pass_hash"));
        }
        rs.close();
        s.close();
        return u;
    }

    public User(int id, String name, String login, String passwordHash) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}