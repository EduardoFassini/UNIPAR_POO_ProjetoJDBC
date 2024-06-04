package br.unipar;
import org.postgresql.util.PGmoney;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;


//usar metodo usuario existente para as pesquisas de exclusao e alteracao



public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        criarTabelaUsuario();
        criarTabelaCliente();
        criarTabelaProduto();
        criarTabelaVenda();

        while (true) {
            System.out.println();
            System.out.println("Menu de Usuário:");
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Inserir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Listar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Sair");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println();
                    inserirUsuario(scanner);
                    break;
                case 2:
                    System.out.println();
                    alterarUsuario(scanner);
                    break;
                case 3:
                    System.out.println();
                    listarTodosUsuarios();
                    break;
                case 4:
                    System.out.println();
                    excluirUsuario(scanner);
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
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

            System.out.println("Tabela de usuários criada com sucesso.");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void inserirUsuario(Scanner scanner) {
        try {
            Connection conn = connection();
            scanner.nextLine();
            System.out.print("Digite seu usuário: ");
            String usuario = scanner.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = scanner.nextLine();
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite sua data de nascimento(AAAA-MM-DD): ");
            String nascimento = scanner.nextLine();

            if (usuarioExistente(conn, usuario)) {
                System.out.println("Usuário existente.");
                return;
            }

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT into usuario (username, password, nome, nascimento)"
                            + "VALUES (?,?,?,?)"
            );
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(nascimento));
            preparedStatement.executeUpdate();
            System.out.println("Usuário Inserido!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean usuarioExistente(Connection conn, String usuario) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM usuario WHERE username = ?")) {
            preparedStatement.setString(1, usuario);
            try (ResultSet resultado = preparedStatement.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static void alterarUsuario(Scanner scanner) {
        System.out.print("Digite o código do usuário para alteração: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o novo usuário: ");
        String novoUsuario = scanner.nextLine();
        System.out.print("Digite a nova senha: ");
        String novaSenha = scanner.nextLine();
        System.out.print("Digite o novo nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite a nova data de nascimento(AAAA-MM-DD): ");
        String novaDataNascimento = scanner.nextLine();

        try (Connection conn = connection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "UPDATE usuario SET username = ?, password = ?, nome = ?, nascimento = ? WHERE codigo = ?")) {
            preparedStatement.setString(1, novoUsuario);
            preparedStatement.setString(2, novaSenha);
            preparedStatement.setString(3, novoNome);
            preparedStatement.setDate(4, Date.valueOf(novaDataNascimento));
            preparedStatement.setInt(5, codigo);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário atualizado!");
            } else {
                System.out.println("O usuário procurado não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário.");
            e.printStackTrace();
        }
    }

    public static void listarTodosUsuarios(){

        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usuario");
            while(result.next()){
                System.out.println("Código: " + result.getInt("codigo"));
                System.out.println("Usuário: " + result.getString("username"));
                System.out.println("Nome: " + result.getString("nome"));
                System.out.println("Data de Nascimento: " + result.getDate("nascimento"));
                System.out.println("-------------------------");
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void excluirUsuario(Scanner scanner) {
        System.out.print("Digite o código do usuário para exclusão: ");
        int codigo = scanner.nextInt();

        try (
             Connection conn = connection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "DELETE FROM usuario WHERE codigo = ?")) {
            preparedStatement.setInt(1, codigo);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário excluído!");
            } else {
                System.out.println("O usuário não foi encontrado.");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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

            System.out.println("Tabela de clientes criada com sucesso.");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void inserirCliente(String nome, String CPF) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT into cliente (nome, CPF)"
                            + "VALUES (?,?)"

            );
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, CPF);

            preparedStatement.executeUpdate();

            System.out.println("Cliente Inserido!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosClientes(){

        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM cliente");
            while(result.next()){
                System.out.println(result.getInt("id_cliente"));
                System.out.println(result.getString("nome"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void criarTabelaProduto() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS produto ("
                    + "id_produto SERIAL CONSTRAINT PK_PRODUTO PRIMARY KEY,"
                    + "descricao VARCHAR(255) NOT NULL,"
                    + "valor MONEY NOT NULL"
                    + ");";

            statement.executeUpdate(sql);

            System.out.println("Tabela de produtos criada com sucesso.");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void inserirProduto(String descricao, BigDecimal valor) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT into produto (descricao, valor)"
                            + "VALUES (?,?)"

            );
            preparedStatement.setString(1, descricao);
            preparedStatement.setBigDecimal(2, valor.setScale(2, BigDecimal.ROUND_HALF_UP));

            preparedStatement.executeUpdate();

            System.out.println("Produto Inserido!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void listarTodosProdutos(){

        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM produto");
            while(result.next()){
                System.out.println("ID: ["+result.getInt("id_produto")+
                        "], Descrição: ["+result.getString("descricao")+
                        "], Valor: ["+result.getString("valor")+"]");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
