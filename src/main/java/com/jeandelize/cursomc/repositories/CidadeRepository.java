package com.jeandelize.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeandelize.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends  JpaRepository<Cidade, Integer>{

	

}
