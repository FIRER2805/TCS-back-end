package Senac.TCS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import Senac.TCS.model.entity.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>, JpaSpecificationExecutor<Setor> {
	
	//criar uma query para buscar o setor de um usuário com specification ou @query

}
