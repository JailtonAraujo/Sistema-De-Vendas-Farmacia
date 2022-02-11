package sistemafarmacia;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import com.projeto.sistemafarmacia.dao.GenericDAO;
import com.projeto.sistemafarmacia.model.Cliente;
import com.projeto.sistemafarmacia.model.Contato;
import com.projeto.sistemafarmacia.model.Usuario;

public class HibernateTest {

	@Test
	public void testSalvar() {
		Usuario usuario = new Usuario();
		
		usuario.setNome("Jailftvcon ");
		///usuario.setCpf("45455f654");
		usuario.setPassWord("fsdfsd");
		usuario.setUserName("fdfd");
		Contato contato = new Contato();
		contato.setEmail("jaisdfsdtfsdfson#hjdfsd");
		contato.setTelefone("fdsdfsdffdfsf");
		contato.setPessoa(usuario);
		
		usuario.setContato(contato);
		
		GenericDAO<Usuario> dao = new GenericDAO<Usuario>();
		
		dao.Salvar(usuario);
		
	}
	
	@Test
	public void TestLogar() {
		
		GenericDAO<Usuario> dao = new GenericDAO<Usuario>();
		
		Usuario usuario = new Usuario();
		usuario.setUserName("jailton");
		usuario.setPassWord("123");
		
		
		Usuario logado = dao.Logar(usuario); 
		
		if(usuario.equals(logado)) {
			JOptionPane.showMessageDialog(null, "BEM VINDO "+logado.getUserName()+ "!");
		}else {
			JOptionPane.showMessageDialog(null, "INFORMAÇÕES DE LOGIN INCORRETAS!");
		}
		
	}
	
	
	@Test
	public void TestDeletar() {
		Cliente cliente = new Cliente();
		cliente.setID(32);
		
		Contato contato = new Contato();
		contato.setPessoa(cliente);
		
		GenericDAO<Cliente> genericDAO = new GenericDAO<Cliente>();
		
		genericDAO.Deletar(cliente, cliente.getID());
		
		
	}
	
	@Test
	public void testBuscar() {
		GenericDAO<Cliente> genericDAO = new GenericDAO<Cliente>();
		
		List<Cliente> lista = genericDAO.buscarUsuario(Cliente.class, "Jail");
		
		for (Cliente usuario : lista) {
			System.out.println(usuario);
		}
		
		
		
	}
	
	@Test
	public void testSimple() {
		try {
		
		Usuario usu = null;
	
		usu.setNome("jailton");
		
		System.out.println(usu);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
