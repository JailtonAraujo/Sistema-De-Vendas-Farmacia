package sistemafarmacia;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.projeto.sistemafarmacia.dao.GenericDAO;
import com.projeto.sistemafarmacia.model.Contato;
import com.projeto.sistemafarmacia.model.Usuario;

public class HibernateTest {

	@Test
	public void testSalvar() {
		Usuario usuario = new Usuario();
		
		usuario.setNome("JAiltfon");
		usuario.setUserName("jgjaild");
		usuario.setPassWord("1g23");
		
		//Contato contato = new Contato();
		
		//contato.setEmail("jainjkfdd");
		//contato.setTelefone("5456465");
		//contato.setPessoa(usuario);
		
		//List<Contato> conta = new ArrayList<Contato>();
		
		//conta.add(contato);
		
		//usuario.setContatos(conta);
		
		GenericDAO dao = new GenericDAO();
		
		dao.Salvar(usuario);
		
	}
}
