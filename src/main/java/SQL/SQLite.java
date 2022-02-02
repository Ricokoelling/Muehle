package SQL;

import java.io.File;
import java.sql.*;

public class SQLite {


    private Connection con;
    private Statement stat;


    SQLite() {
        connect();
    }


    //--------------------------------------------------------------------------------------Server Functions--------------------------------------------------------------------------------------\\

    /**         This function create
    *
    * @param username String username
    * @param pw    Int password as hash
    * @return     true -> successfull/
    *         false -> unsuccessfull
    */
    public boolean create(String username, int pw){
        ResultSet rs = queryUsername(username);
        try {
        if(!rs.next()){
            add(username, pw);
            return true;
        }else {
            System.err.println("Account Creation Failed");
            return false;
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**         This function checks the credentials of the user
    *
    * @param username String username
    * @param pw    Int password as hash
    * @return     true -> successfull/
    *         false -> unsuccessfull
    */
    public boolean login(String username, int pw){
        ResultSet rs = queryPassword(username);
        try {
            if(rs.next()){
                //check if returned hash matches the send one from the client
                if(rs.getInt("password")==pw){
                    System.out.println("Login Successfull");
                }return true;
            }else return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //--------------------------------------------------------------------------------------SQLite Functions--------------------------------------------------------------------------------------\\

    /**   This Function initializes the SQL Database (if it's not already created)
    *
    */
    public void init(){
        String start = "CREATE TABLE IF NOT EXISTS "+tablename()+"(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR, password INTEGER)";
        try{
            stat.execute(start);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

        /**   This function connects to the Database
        *
        */
        public void connect(){
        try{
            String url =   url();
            con = DriverManager.getConnection(url);
            stat = con.createStatement();
            init();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**   This function disconnects from the Database
    *
    */
    public void disconnect(){
        try{
            if(con!=null) con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
    *         This function adds a new user to the SQL Database
    * @param username String username
    * @param pw    Int password as hash
    */
    public void add(String username, int pw){
        String pass = "INSERT INTO "+tablename()+"(username, password) VALUES('"+username+"', "+pw+")";
        try{
            stat.execute(pass);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**           This function looks if a name is already taken and if so it returns the name
    *
    * @param username   String username
    * @return       Returns the ResultSet if a name already exists else it returns null
    */
    public ResultSet queryUsername(String username){
        String pass = "SELECT username FROM "+tablename()+" WHERE username = '"+username+"'";
        try{
            return stat.executeQuery(pass);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**         This function return the hash as ResultSet of the password of the username name send if it exists
    *
    * @param username String username
    * @return     Returns the password as hash in a Resultset if a username exists
    */
    public ResultSet queryPassword(String username){
        String pass = "SELECT password FROM "+tablename()+" WHERE username = '"+username+"'";
        try{
            return stat.executeQuery(pass);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //--------------------------------------------------------------------------------------Strings--------------------------------------------------------------------------------------\\

    private String tablename(){return "userData";}

    private String url(){return "jdbc:sqlite:data.db";}
}
