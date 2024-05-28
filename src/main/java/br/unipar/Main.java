package br.unipar;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static void main(String[] args) {
        criarTabelaUsuario();
    }

    public static Connection connection() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }

    public static void criarTabelaUsuario() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS usuario ("
                        + "codigo SERIAL PRIMARY KEY,"
                        + "username VARCHAR(50) NOT NULL UNIQUE,"
                        + "password VARCHAR(300) NOT NULL,"
                        + "nome VARCHAR(50) NOT NULL,"
                        + "nascimento DATE"
                        + ");";

            statement.executeUpdate(sql);

            System.out.println("Tabela de usuários criada com sucesso");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void InserirUsuario(String username, String password, String nome, String nascimento) {
        try {
            //Abre conexão
            Connection conn = connection();

            //Prepara a execução de um SQL
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT into usuario (username, password, nome, nascimento)"
                            + "VALUES (?,?,?,?)"

            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(nascimento));

            preparedStatement.executeUpdate();

            System.out.println("Usuário Inserido!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosUsuarios(){

        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usuarios");
            while(result.next()){
                System.out.println(result.getInt("codigo"));
                System.out.println(result.getString("username"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void criarTabelaCliente() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS cliente ("
                    + "id_cliente SERIAL CONSTRAINT PK_CLIENTE PRIMARY KEY,"
                    + "nome VARCHAR(255) NOT NULL,"
                    + "CPF VARCHAR(15) UNIQUE"
                    + ");";

            statement.executeUpdate(sql);

            System.out.println("Tabela de clientes criada com sucesso");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void criarTabelaProduto() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS cliente ("
                    + "id_produto SERIAL CONSTRAINT PK_PRODUTO PRIMARY KEY,"
                    + "descricao VARCHAR(255) NOT NULL,"
                    + "valor MONEY NOT NULL"
                    + ");";

            statement.executeUpdate(sql);

            System.out.println("Tabela de clientes criada com sucesso");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void criarTabelaVenda() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS venda ("
                    + "id_venda SERIAL CONSTRAINT PK_VENDA PRIMARY KEY,"
                    + "cliente INTEGER CONSTRAINT FK_CLIENTE REFERENCES cliente(id_cliente),"
                    + "produto INTEGER CONSTRAINT FK_PRODUTO REFERENCES produto(id_produto)"
                    + ");";

            statement.executeUpdate(sql);

            System.out.println("Tabela de vendas criada com sucesso");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
