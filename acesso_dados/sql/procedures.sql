--procedures--

--inserção de dados--
delimiter //
create procedure p_incluir_aluno (pnome varchar(45), pcurso varchar(45), pmatricula int)
begin
insert into alunos (nome, curso, matricula) values (pnome, pcurso, pmatricula);
end;
//

