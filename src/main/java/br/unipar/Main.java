package br.unipar;
import org.postgresql.util.PGmoney;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

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
        boolean menu=true,menuUsuario,menuCliente,menuProduto,menuVenda;

        while(menu) {
            System.out.println();
            System.out.println("[ Menu Principal ]");
            System.out.println("Escolha uma tabela para acessar seu menu:");
            System.out.println("1 - Usuário");
            System.out.println("2 - Cliente");
            System.out.println("3 - Produto");
            System.out.println("4 - Venda");
            System.out.println("5 - Sair");
            int escolha = scanner.nextInt();

            switch(escolha) {
                case 1:
                    menuUsuario=true;
                    while (menuUsuario) {
                        System.out.println();
                        System.out.println("[ Menu de Usuário ]");
                        System.out.println("Escolha uma opção: ");
                        System.out.println("1 - Inserir");
                        System.out.println("2 - Alterar");
                        System.out.println("3 - Listar");
                        System.out.println("4 - Excluir");
                        System.out.println("5 - Voltar");
                        int escolhaUsuario = scanner.nextInt();

                        switch (escolhaUsuario) {
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
                                menuUsuario = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }
                    break;
                case 2:
                    menuCliente = true;
                    while (menuCliente){
                        System.out.println();
                        System.out.println("[ Menu de Cliente ]");
                        System.out.println("Escolha uma opção: ");
                        System.out.println("1 - Inserir");
                        System.out.println("2 - Alterar");
                        System.out.println("3 - Listar");
                        System.out.println("4 - Excluir");
                        System.out.println("5 - Voltar");
                        int escolhaCliente = scanner.nextInt();

                        switch (escolhaCliente) {
                            case 1:
                                System.out.println();
                                inserirCliente(scanner);
                                break;
                            case 2:
                                System.out.println();
                                alterarCliente(scanner);
                                break;
                            case 3:
                                System.out.println();
                                listarTodosClientes();
                                break;
                            case 4:
                                System.out.println();
                                excluirCliente(scanner);
                                break;
                            case 5:
                                menuCliente = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }
                    break;
                case 3:
                    menuProduto = true;
                    while (menuProduto){
                        System.out.println();
                        System.out.println("[ Menu de Produto ]");
                        System.out.println("Escolha uma opção: ");
                        System.out.println("1 - Inserir");
                        System.out.println("2 - Alterar");
                        System.out.println("3 - Listar");
                        System.out.println("4 - Excluir");
                        System.out.println("5 - Voltar");
                        int escolhaProduto = scanner.nextInt();

                        switch (escolhaProduto) {
                            case 1:
                                System.out.println();
                                inserirProduto(scanner);
                                break;
                            case 2:
                                System.out.println();
                                alterarProduto(scanner);
                                break;
                            case 3:
                                System.out.println();
                                listarTodosProdutos();
                                break;
                            case 4:
                                System.out.println();
                                excluirProduto(scanner);
                                break;
                            case 5:
                                menuProduto = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }
                        break;
                case 4:
                    menuVenda = true;
                    while (menuVenda){
                        System.out.println();
                        System.out.println("[ Menu de Venda ]");
                        System.out.println("Escolha uma opção: ");
                        System.out.println("1 - Inserir");
                        System.out.println("2 - Alterar");
                        System.out.println("3 - Listar");
                        System.out.println("4 - Excluir");
                        System.out.println("5 - Voltar");
                        int escolhaVenda = scanner.nextInt();

                        switch (escolhaVenda) {
                            case 1:
                                System.out.println();
                                inserirVenda(scanner);
                                break;
                            case 2:
                                System.out.println();
                                alterarVenda(scanner);
                                break;
                            case 3:
                                System.out.println();
                                listarTodasVendas();
                                break;
                            case 4:
                                System.out.println();
                                excluirVenda(scanner);
                                break;
                            case 5:
                                menuVenda = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }

                    }
                        break;
                case 5:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
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
                System.out.println("Usuário já existente.");
                return;
            }

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT into usuario (username, password, nome, nascimento)"
                            + "VALUES (?,?,?,?)"
            );
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, Date.valueOf(nascimento));
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
        try {
            Connection conn = connection();
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

            if (usuarioExistente(conn, novoUsuario)) {
                System.out.println("Usuário já existente.");
                return;
            }
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE usuario SET username = ?, password = ?, nome = ?, nascimento = ? WHERE codigo = ?");
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
            System.out.println("Erro ao alterar usuário.");
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
        try {
             Connection conn = connection();
             System.out.print("Digite o código do usuário para exclusão: ");
             int codigo = scanner.nextInt();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "DELETE FROM usuario WHERE codigo = ?");
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

    public static void inserirCliente(Scanner scanner) {
        try {
            Connection conn = connection();
            scanner.nextLine();
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite seu CPF: ");
            String CPF = scanner.nextLine();

            if (clienteExistente(conn, CPF)) {
                System.out.println("CPF já existente.");
                return;
            }
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

    private static boolean clienteExistente(Connection conn, String CPF) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM cliente WHERE CPF = ?")) {
            preparedStatement.setString(1, CPF);
            try (ResultSet resultado = preparedStatement.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static void alterarCliente(Scanner scanner) {
        try {
            Connection conn = connection();
            System.out.print("Digite o código do cliente para alteração: ");
            int id_cliente = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o novo nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo CPF: ");
            String novoCPF = scanner.nextLine();

            if (clienteExistente(conn, novoCPF)) {
                System.out.println("CPF já existente.");
                return;
            }
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE cliente SET nome = ?, CPF = ? WHERE id_cliente = ?");
            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, novoCPF);
            preparedStatement.setInt(3, id_cliente);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente atualizado!");
            } else {
                System.out.println("O cliente procurado não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar cliente.");
            e.printStackTrace();
        }
    }

    public static void listarTodosClientes(){
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM cliente");
            while(result.next()){
                System.out.println("Código: " + result.getInt("id_cliente"));
                System.out.println("Nome: " + result.getString("nome"));
                System.out.println("CPF: " + result.getString("CPF"));
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void excluirCliente(Scanner scanner) {
        try {
            Connection conn = connection();
            System.out.print("Digite o código do cliente para exclusão: ");
            int id_cliente = scanner.nextInt();
            PreparedStatement preparedStatement = conn.prepareStatement(
                        "DELETE FROM cliente WHERE id_cliente = ?");
            preparedStatement.setInt(1, id_cliente);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente excluído!");
            } else {
                System.out.println("O cliente não foi encontrado.");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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

    public static void inserirProduto(Scanner scanner) {
        try {
            Connection conn = connection();
            scanner.nextLine();
            System.out.print("Digite a descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Digite o valor: ");
            BigDecimal valor = new BigDecimal(scanner.nextLine());

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

    public static void alterarProduto(Scanner scanner){
        try {
            Connection conn = connection();
            System.out.print("Digite o código do produto para alteração: ");
            int id_produto = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite a nova descrição: ");
            String novaDescricao = scanner.nextLine();
            System.out.print("Digite o novo valor: ");
            BigDecimal novoValor = new BigDecimal(scanner.nextLine());

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE produto SET descricao = ?, valor = ? WHERE id_produto = ?");
            preparedStatement.setString(1, novaDescricao);
            preparedStatement.setBigDecimal(2, novoValor.setScale(2, BigDecimal.ROUND_HALF_UP));
            preparedStatement.setInt(3, id_produto);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto atualizado!");
            } else {
                System.out.println("O produto procurado não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar produto.");
            e.printStackTrace();
        }
    }

    public static void listarTodosProdutos(){
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM produto");
            while(result.next()){
                System.out.println("Código: " + result.getInt("id_produto"));
                System.out.println("Descrição: " + result.getString("descricao"));
                System.out.println("Valor: " + result.getString("valor"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void excluirProduto(Scanner scanner){
        try {
            Connection conn = connection();
            System.out.print("Digite o código do produto para exclusão: ");
            int id_produto = scanner.nextInt();
             PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM produto WHERE id_produto = ?");
            preparedStatement.setInt(1, id_produto);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto excluído!");
            } else {
                System.out.println("O produto não foi encontrado.");
            }
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

    public static void inserirVenda(Scanner scanner){
        try {
            Connection conn = connection();
            scanner.nextLine();
            System.out.print("Digite o ID do cliente: ");
            int cliente = scanner.nextInt();
            System.out.print("Digite o ID do produto: ");
            int produto = scanner.nextInt();

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO venda (cliente, produto) VALUES (?, ?)");
                preparedStatement.setInt(1, cliente);
                preparedStatement.setInt(2, produto);

            preparedStatement.executeUpdate();
            System.out.println("Venda inserida!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarVenda(Scanner scanner){
        try {
            Connection conn = connection();
            System.out.print("Digite o código da venda para alteração: ");
            int id_venda = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o ID do novo cliente: ");
            int novoCliente = scanner.nextInt();
            System.out.print("Digite o ID do novo produto: ");
            int novoProduto = scanner.nextInt();

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE venda SET cliente = ?, produto = ? WHERE id_venda = ?");
            preparedStatement.setInt(1, novoCliente);
            preparedStatement.setInt(2, novoProduto);
            preparedStatement.setInt(3, id_venda);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Venda atualizada!");
            } else {
                System.out.println("A venda procurada não foi encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar venda.");
            e.printStackTrace();
        }
    }

    public static void listarTodasVendas(){
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM venda V, cliente C, produto P WHERE V.cliente = C.id_cliente AND V.produto = P.id_produto");
            while(result.next()){
                System.out.println("Código da Venda: " + result.getInt("id_venda"));
                System.out.println("Código do Cliente: " + result.getInt("cliente"));
                System.out.println("Nome do Cliente: " + result.getString("nome"));
                System.out.println("Código do Produto: " + result.getInt("produto"));
                System.out.println("Descrição do Produto: " + result.getString("descricao"));
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void excluirVenda(Scanner scanner){
        try {
            Connection conn = connection();
            System.out.print("Digite o código da venda para exclusão: ");
            int id_venda = scanner.nextInt();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM venda WHERE id_venda = ?");
            preparedStatement.setInt(1, id_venda);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Venda excluída!");
            } else {
                System.out.println("A venda não foi encontrada.");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
