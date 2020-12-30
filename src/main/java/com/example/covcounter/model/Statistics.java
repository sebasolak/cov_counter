package com.example.covcounter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int simulationDay;
    private double Pi;//liczba zakazonych
    private double Pv;//zdrowi podatni
    private double Pm;//zmarli
    private double Pr;//wyleczeni

    public Statistics(int simulationDay, double Pi, double Pv, double Pm, double Pr) {
        this.simulationDay = simulationDay;
        this.Pi = Pi;
        this.Pv = Pv;
        this.Pm = Pm;
        this.Pr = Pr;
    }

    public Statistics() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSimulationDay() {
        return simulationDay;
    }

    public void setSimulationDay(int simulationDay) {
        this.simulationDay = simulationDay;
    }

    public double getPi() {
        return Pi;
    }

    public void setPi(double pi) {
        Pi = pi;
    }

    public double getPv() {
        return Pv;
    }

    public void setPv(double pv) {
        Pv = pv;
    }

    public double getPm() {
        return Pm;
    }

    public void setPm(double pm) {
        Pm = pm;
    }

    public double getPr() {
        return Pr;
    }

    public void setPr(double pr) {
        Pr = pr;
    }
}
