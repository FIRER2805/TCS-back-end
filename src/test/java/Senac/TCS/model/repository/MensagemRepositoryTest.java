package Senac.TCS.model.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.repository.MensagemRepository;

@DataJpaTest
public class MensagemRepositoryTest {

	@Autowired
	MensagemRepository mensagemRepository;
	
	@Test
	public void testFindById() {
		Optional<Mensagem> mensagemEncontrada = mensagemRepository.findById(1l);
		
		assertNotNull(mensagemEncontrada);
	}
	
}
