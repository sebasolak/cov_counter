package com.example.covcounter.service;

import com.example.covcounter.dao.SimulationDao;
import com.example.covcounter.model.Simulation;
import com.example.covcounter.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimulationService {

    private final SimulationDao simulationDao;

    @Autowired
    public SimulationService(@Qualifier("simulationDao") SimulationDao simulationDao) {
        this.simulationDao = simulationDao;
    }

    public Simulation postSimulationObject(Simulation simulation) {
        covidCounter(simulation);

        simulationDao.save(simulation);
        return simulation;
    }

    public List<Simulation> selectAllSimulations() {
        return simulationDao.findAll();
    }

    public Simulation selectSimulationById(int simulationId) {
        return simulationDao.findById(simulationId).get();
    }

    public Simulation updateSimulationById(int simulationId, Simulation simulation) {

        simulationDao.findById(simulationId).orElseThrow(() ->
                new NoSuchElementException(String.format("Simulation %s is not present", simulationId)));

        covidCounter(simulation);

        simulation.setId(simulationId);
        simulationDao.save(simulation);

        return simulation;
    }


    public void removeSimulationById(int simulationId) {
        simulationDao.deleteById(simulationId);
    }

    private void covidCounter(Simulation simulation) {
        if (simulation.getI() > simulation.getP()) {
            throw new IllegalArgumentException(
                    String.format("Variable I cannot be bigger than P. " +
                            "I was %s and P was %s", simulation.getI(), simulation.getP()));
        }

        List<Statistics> statsList = new ArrayList<>();
        simulation.setStats(statsList);

        double p = simulation.getP() - simulation.getI();
        double i = simulation.getI();
        double it = i;
        double zm = 0;
        double cu = 0;
        Map<Integer, double[]> map = new HashMap<>();

        int y = 1;

        Map<Integer, Integer> deathDaysMap = new HashMap<>();

        for (int j = 1; j < simulation.getTi() / simulation.getTm() + 1; j++) {
            int val = simulation.getTm() * j;
            deathDaysMap.put(val, 1);
        }

        for (int j = 0; j < simulation.getTs(); j++) {

            map.put(j + 1, new double[]{p, i, it, zm, cu});
            Statistics statistics = new Statistics(j + 1,
                    Math.round(i * 100.0) / 100.0,
                    Math.round(p * 100.0) / 100.0,
                    Math.round(zm * 100.0) / 100.0,
                    Math.round(cu * 100.0) / 100.0);

            statsList.add(statistics);
            if (j >= Math.max(simulation.getTm() - 2, 0)) {
                for (Map.Entry<Integer, Integer> pair : deathDaysMap.entrySet()) {
                    Integer key = pair.getKey();
                    if (key - 2 <= j) {
                        double deathToday = map.get(deathDaysMap.get(key))[2] * simulation.getM();
                        map.get(deathDaysMap.get(key))[2] -= deathToday;
                        i -= deathToday;
                        zm += deathToday;
                        deathDaysMap.put(key, deathDaysMap.get(key) + 1);
                    }
                }
            }


            if (j >= Math.max(simulation.getTi() - 2, 0)) {
                double recoverToday = map.get(y++)[2];
                i -= recoverToday;
                cu += recoverToday;
            }

            double b = Math.min(p, i * simulation.getR());
            b = Math.max(0, b);
            p -= b;
            i += b;
            i = Math.max(0, i);
            it = b;
        }
    }


}
