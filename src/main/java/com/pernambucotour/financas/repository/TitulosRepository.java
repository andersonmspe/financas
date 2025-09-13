package com.pernambucotour.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pernambucotour.financas.model.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long>  {

}
