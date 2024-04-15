package Senac.TCS.model.specification;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import Senac.TCS.model.dto.MensagemRecebidaDTO;
import Senac.TCS.model.entity.Mensagem;
import jakarta.persistence.criteria.Predicate;

public class MensagemSpecification {

	public static Specification<Mensagem> proximaMensagem(MensagemRecebidaDTO mensagemDto){
		return (root, query, cb) -> {
			ArrayList<Predicate> predicates = new ArrayList();

			predicates.add(cb.equal(root.get("idSetor"), mensagemDto.getIdSetor()));
			predicates.add(cb.equal(root.get("idMensagemPai"), mensagemDto.getIdMensagemPai()));
			predicates.add(cb.like(root.get("inputPai"), mensagemDto.getInputPai()));


			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	public static Specification<Mensagem> mensagemRoot(MensagemRecebidaDTO mensagemDto){
		return (root, query, cb) -> {
			ArrayList<Predicate> predicates = new ArrayList();

			predicates.add(cb.equal(root.get("idSetor"), mensagemDto.getIdSetor()));
			predicates.add(cb.isNull(root.get("idMensagemPai")));
			predicates.add(cb.isNull(root.get("inputPai")));

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
