package Passagens;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseCreatorListener implements ServletContextListener {

    private void createCategoriaVoo(Statement s) {
        try {
            s.execute("CREATE TABLE categoria_voo(\n"
                    + "    id INTEGER NOT NULL GENERATED ALWAYS \n"
                    + "        AS IDENTITY (START WITH 1, INCREMENT BY 1)\n"
                    + "    , categoria numeric(2) not null\n"
                    + "    , categoria_voo varchar(50) not null\n"
                    + ")");
            System.out.println("Criada tabela categoria_voo.");
        } catch (Exception ex2) {
            System.err.println("Ero ao criar a categoria_voo: " + ex2.getMessage());
        }
    }

    private void createUsersTable(Statement s) {
        try {
            s.execute("CREATE TABLE users(\n"
                    + "    cpf INTEGER NOT NULL GENERATED ALWAYS \n"
                    + "        AS IDENTITY (START WITH 1, INCREMENT BY 1)\n"
                    + "    , name varchar(75) not null\n"
                    + "    , email varchar(50)  not null\n"
                    + "    , nacionalidade varchar(45) not null\n"
                    + "    , genero varchar(11) not null\n"
                    + ")");
            System.out.println("Criada tabela users.");
            s.execute("INSERT INTO users VALUES("
                    + "default"
                    + ", 'Administrador do Sistema'"
                    + ", 'admin@gmail.com'"
                    + ", 'Brasileiro'"
                    + ", 'Masculino'"
                    + ")");
            System.out.println("Usuário admin criado");
        } catch (Exception ex2) {
            System.err.println("Ero ao criar a users: " + ex2.getMessage());
        }
    }

    private void createVooTable(Statement s) {
        try {
            s.execute("CREATE TABLE voo(\n"
                    + "      cv INTEGER NOT NULL GENERATED ALWAYS \n"
                    + "        AS IDENTITY (START WITH 1, INCREMENT BY 1)\n"
                    + "    , id_origem_voo varchar(50) not null\n"
                    + "    , id_destino_voo varchar(50)  not null\n"
                    + "    , voo numeric(4)\n"
                    + "    , acentos integer(4)\n"
                    + "    , ct_voo  integer(2)\n"
                    + "    , dateida timestamp not null\n"
                    + "    , hourida timestamp not null\n"
                    + ")");
            System.out.println("Criada tabela Voo.");
        } catch (Exception ex2) {
            System.err.println("Ero ao criar a Voo: " + ex2.getMessage());
        }
    }

    private void createLocalTable(Statement s) {
        try {
            s.execute("CREATE TABLE local(\n"
                    + "      local INTEGER NOT NULL GENERATED ALWAYS \n"
                    + "        AS IDENTITY (START WITH 1, INCREMENT BY 1)\n"
                    + "    , name varchar(50) not null\n"
                    + ")");
            System.out.println("Criada tabela Local.");
        } catch (Exception ex2) {
            System.err.println("Ero ao criar a tabela Local: " + ex2.getMessage());
        }
    }
    

    private void createVooUsuarioTable(Statement s) {
        try {
            s.execute("CREATE TABLE voo_usuario(\n"
                    + "      cdv INTEGER NOT NULL GENERATED ALWAYS \n"
                    + "        AS IDENTITY (START WITH 1, INCREMENT BY 1)\n"
                    + "    , idVoo integer(5) not null\n"
                    + "    , idUsu integer(11) not null\n"
                    + "    , qtAdul integer(2) not null\n"
                    + "    , qtCrianca integer(2) not null\n"
                    + ")");
            
            System.out.println("Criada tabela vehicles_stays.");
        }catch(Exception ex2){
            System.err.println("Ero ao criar a vehicles_stays: "+ex2.getMessage());
        }
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String url = "jdbc:derby:c:/derby/Projeto05Db;create=true";
            Connection c =DriverManager.getConnection(url);
            Statement s = c.createStatement();
            System.out.println(new Date());
            System.out.println("Iniciando a criação do BD.");
            
            //CRIANDO TABELAS
            createCategoriaVoo(s);
            createUsersTable(s);
            createVooTable(s);
            createLocalTable(s);
            createVooUsuarioTable(s); 
            s.close();
            c.close();
            DriverManager.getConnection
            ("jdbc:derby:c:/derby/Projeto05Db;shutdown=true");
        }catch(Exception ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}