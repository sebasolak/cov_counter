package com.example.covcounter.dao;

import com.example.covcounter.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("simulationDao")
public interface SimulationDao extends JpaRepository<Simulation, Integer> {
}
