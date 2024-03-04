package Senac.TCS.model.specification;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import Senac.TCS.model.entity.Mensagem;
import Senac.TCS.model.seletor.MensagemSeletor;
import jakarta.persistence.criteria.Predicate;

public class MensagemSpecification {
	
	public static Specification<Mensagem> proximaMensagem(MensagemSeletor seletor){
		return (root, query, cb) -> {
			ArrayList<Predicate> predicates = new ArrayList();

			predicates.add(cb.equal(root.get("idMensagemPai"), seletor.getIdMensagemPai()));
			predicates.add(cb.equal(root.get("idSetor"), seletor.getIdSetor()));
			predicates.add(cb.like(root.get("inputPai"), seletor.getInputPai()));
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
