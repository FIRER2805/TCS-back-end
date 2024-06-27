use tcs;

-- relatório de opções mais utilizados
select
	mh.conteudo as "Opção",
    count(mh.conteudo) as "Utilizações"
from mensagem_historico mh
join contato c on
c.id =  mh.id_contato
join usuario u on
u.id = c.id_usuario
where u.id = 1
group by mh.conteudo;

-- opções mais utilizadas por contatos
select
	mh.conteudo as "Opção",
    coalesce(c.nome ,c.numero) as "contato",
    count(mh.conteudo) as "Utilizações"
from mensagem_historico mh
join contato c on
c.id =  mh.id_contato
join usuario u on
u.id = c.id_usuario
where u.id = 1
group by c.nome, c.numero, mh.conteudo, "Utilizações";