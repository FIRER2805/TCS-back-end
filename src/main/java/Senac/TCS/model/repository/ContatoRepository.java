package Senac.TCS.model.repository;

import org.springframework.data.repository.CrudRepository;

import Senac.TCS.model.entity.Contato;

public interface ContatoRepository extends CrudRepository<Contato, Long>{

    public Contato findByNumero(String numero);

}
