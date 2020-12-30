package com.example.covcounter.controller;

import com.example.covcounter.model.Simulation;
import com.example.covcounter.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cov")
@Validated
public class SimulationController {

    private final SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping
    public Simulation postSimulationObject(@Valid @RequestBody Simulation simulation) {
        return simulationService.postSimulationObject(simulation);
    }

    @GetMapping
    public List<Simulation> getAllSimulations() {
        return simulationService.selectAllSimulations();
    }

    @GetMapping(path = "{simulationId}")
    public Simulation getSimulationById(@PathVariable int simulationId) {
        return simulationService.selectSimulationById(simulationId);
    }

    @PutMapping(path = "{simulationId}")
    public Simulation updateSimulationById(@PathVariable int simulationId,
                                           @Valid @RequestBody Simulation simulation) {
        return simulationService.updateSimulationById(simulationId, simulation);
    }

    @DeleteMapping(path = "{simulationId}")
    public void removeSimulationById(@PathVariable int simulationId) {
        simulationService.removeSimulationById(simulationId);
    }

}

