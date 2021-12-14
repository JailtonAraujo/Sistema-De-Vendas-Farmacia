package sistemafarmacia;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import com.projeto.sistemafarmacia.dao.GenericDAO;
import com.projeto.sistemafarmacia.model.Contato;
import com.projeto.sistemafarmacia.model.Usuario;

public class HibernateTest {

	@Test
	public void testSalvar() {
		Usuario usuario = new Usuario();
		
		usuario.setNome("Jailtvcon de Araujgfo Santos");
		usuario.setUserName("Lego");
		usuario.setPassWord("74g1");
		
		//Contato contato = new Contato();
		
		//contato.setEmail("jainjkfdd");
		//contato.setTelefone("5456465");
		//contato.setPessoa(usuario);
		
		//List<Contato> conta = new ArrayList<Contato>();
		
		//conta.add(contato);
		
		//usuario.setContatos(conta);
		
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
	public void testSimple() {
		System.out.println(Usuario.class.getSimpleName().toLowerCase());
	}
}
