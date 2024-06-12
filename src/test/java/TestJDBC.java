import br.unipar.Main;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.fail;

public class TestJDBC {


    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @Before
    public void setUp() {
        System.setIn(systemIn);
    }

    @After
    public void tearDown() {
        System.setIn(systemIn);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testCriarTabelaUsuario() {
        Main.criarTabelaUsuario();
    }

    @Test
    public void testInserirUsuario() {
        String input = "\nusuario teste\nsenha teste\nUsuario Teste\n1991-11-11\n";
        Scanner scanner = new Scanner(input);
        try {
            Main.inserirUsuario(scanner);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertFalse(Boolean.parseBoolean("Falha ao inserir usuário: " + e.getMessage()));
        }
    }
    @Test
    public void testAlterarUsuario() {
        try {
            Main.alterarUsuario(new Scanner(new ByteArrayInputStream("1\nusuario teste 2\nsenha teste 2\nUsuario Teste 2\n1999-11-11\n".getBytes())));
        } catch (Exception e) {
            fail("Falha ao alterar usuário: " + e.getMessage());
        }
    }

    @Test
    public void testListarTodosUsuarios() {
        try {
            Main.listarTodosUsuarios();
        } catch (Exception e) {
            fail("Falha ao listar usuários: " + e.getMessage());
        }
    }

    @Test
    public void testExcluirUsuario() {
        try {
            Main.excluirUsuario(new Scanner(new ByteArrayInputStream("23\n".getBytes())));
        } catch (Exception e) {
            fail("Falha ao excluir usuário: " + e.getMessage());
        }
    }

    @Test
    public void testCriarTabelaCliente() {
        Main.criarTabelaCliente();
    }

    @Test
    public void testInserirCliente() {
        String input= "\nCliente Teste\n13253453299\n";
        Scanner scanner = new Scanner(input);
        try {
            Main.inserirCliente(scanner);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertFalse(Boolean.parseBoolean("Falha ao inserir cliente: " + e.getMessage()));
        }
    }

    @Test
    public void testAlterarCliente() {
        try {
            Main.alterarCliente(new Scanner(new ByteArrayInputStream("1\nCliente Teste 2\n38349483899\n".getBytes())));
        } catch (Exception e) {
            fail("Falha ao alterar cliente: " + e.getMessage());
        }
    }

    @Test
    public void testListarTodosClientes() {
        try {
            Main.listarTodosClientes();
        } catch (Exception e) {
            fail("Falha ao listar clientes: " + e.getMessage());
        }
    }

    @Test
    public void testExcluirCliente() {
        try {
            Main.excluirCliente(new Scanner(new ByteArrayInputStream("1\n".getBytes())));
        } catch (Exception e) {
            fail("Falha ao excluir cliente: " + e.getMessage());
        }
    }

    @Test
    public void testCriarTabelaProduto() {
        Main.criarTabelaProduto();
    }

    @Test
    public void testInserirProduto() {
        provideInput("\nproduto teste\n1.11\n");
        try {
            Main.inserirProduto(new Scanner(System.in));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertFalse(Boolean.parseBoolean("Falha ao inserir usuário: " + e.getMessage()));
        }
    }

    @Test
    public void testAlterarProduto() {
        try {
            Main.alterarProduto(new Scanner(new ByteArrayInputStream("1\nproduto teste 2\n2.22\n".getBytes())));
        } catch (Exception e) {
            Assert.assertFalse(Boolean.parseBoolean("Falha ao alterar produto: " + e.getMessage()));
        }
    }

    @Test
    public void testListarTodosProdutos() {
        try {
            Main.listarTodosProdutos();
        } catch (Exception e) {
            fail("Falha ao listar produtos: " + e.getMessage());
        }
    }

    @Test
    public void testExcluirProduto() {
        try {
            Main.excluirProduto(new Scanner(new ByteArrayInputStream("1\n".getBytes())));
        } catch (Exception e) {
            fail("Falha ao excluir produto: " + e.getMessage());
        }
    }

    @Test
    public void testCriarTabelaVenda() {
        Main.criarTabelaVenda();
    }

    @Test
    public void testInserirVenda() {
        provideInput("\n2\n2\n");
        try {
            Main.inserirVenda(new Scanner(System.in));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertFalse(Boolean.parseBoolean("Falha ao inserir venda: " + e.getMessage()));
        }
    }

    @Test
    public void testAlterarVenda() {
        try {
            Main.alterarVenda(new Scanner(new ByteArrayInputStream("3\n3\n3\n".getBytes())));
        } catch (Exception e) {
            Assert.assertFalse(Boolean.parseBoolean("Falha ao alterar produto: " + e.getMessage()));
        }
    }

    @Test
    public void testListarTodasVendas() {
        try {
            Main.listarTodasVendas();
        } catch (Exception e) {
            fail("Falha ao listar vendas: " + e.getMessage());
        }
    }

    @Test
    public void testExcluirVenda() {
        try {
            Main.excluirVenda(new Scanner(new ByteArrayInputStream("1\n".getBytes())));
        } catch (Exception e) {
            fail("Falha ao excluir venda: " + e.getMessage());
        }
    }
}