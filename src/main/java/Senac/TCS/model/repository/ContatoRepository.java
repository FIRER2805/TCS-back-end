package Senac.TCS.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Senac.TCS.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

	

}
