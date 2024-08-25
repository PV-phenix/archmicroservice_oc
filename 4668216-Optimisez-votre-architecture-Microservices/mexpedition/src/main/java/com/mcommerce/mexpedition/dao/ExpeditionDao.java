package com.mcommerce.mexpedition.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcommerce.mexpedition.model.Expedition;

@Repository
public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {

}