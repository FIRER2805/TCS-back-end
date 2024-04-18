package Senac.TCS.model.repository;

import Senac.TCS.model.dto.MensagemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MensagemJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MensagemDto obterArvoreMensagem(long idSetor){
        String sql = "select * from mensagem where id_setor = ? AND id_mensagem_pai is null;";

        RowMapper<MensagemDto> rowMapper = (rs, row) -> {
            MensagemDto mensagem = MensagemDto.builder()
                    .key(rs.getLong(1))
                    .label(rs.getString(2))
                    .inputPai(rs.getString(3))
                    .children(this.obterMensagensFilhas(rs.getLong(1)))
                    .idSetor(idSetor)
                    .build();
            return mensagem;
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, idSetor);
    }

    private List<MensagemDto> obterMensagensFilhas(long idMensagemPai){
        String sql = "select * from mensagem where id_mensagem_pai = ?;";

        RowMapper<MensagemDto> rowMapper = (rs, row) -> {
            MensagemDto mensagem = MensagemDto.builder()
                    .key(rs.getLong(1))
                    .label(rs.getString(2))
                    .inputPai(rs.getString(3))
                    .children(this.obterMensagensFilhas(rs.getLong(1)))
                    .idSetor(rs.getLong(4))
                    .build();
            return mensagem;
        };
        return jdbcTemplate.query(sql, rowMapper, idMensagemPai);
    }
}
