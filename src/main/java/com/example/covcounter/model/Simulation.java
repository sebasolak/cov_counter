package com.example.covcounter.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String N;//nazwa symulacji(str)
    @Min(value = 0)
    private int P;//wielkosc populacji
    @Min(value = 0)
    private int I;//poczatkowa liczba zarazonych
    @Min(value = 0)
    private double R;//wspol zarazania
    @Min(value = 0)
    @Max(value = 1)
    private double M;//wspol smiertelnosci
    @Min(value = 1)
    private int Ti;//dni od zarazenia do wyzdrowienia
    @Min(value = 1)
    private int Tm;//dni od zarazenia do smierci
    @Min(value = 1)
    private int Ts;//ilos dni dla stymulacji
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Statistics> stats;


    public Simulation(String N, int P, int I, double R, double M, int Ti, int Tm, int Ts) {
        this.N = N;
        this.P = P;
        this.I = I;
        this.R = R;
        this.M = M;
        this.Ti = Ti;
        this.Tm = Tm;
        this.Ts = Ts;
        stats = new ArrayList<>();
    }

    public Simulation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N = n;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public int getI() {
        return I;
    }

    public void setI(int i) {
        I = i;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getM() {
        return M;
    }

    public void setM(double m) {
        M = m;
    }

    public int getTi() {
        return Ti;
    }

    public void setTi(int ti) {
        Ti = ti;
    }

    public int getTm() {
        return Tm;
    }

    public void setTm(int tm) {
        Tm = tm;
    }

    public int getTs() {
        return Ts;
    }

    public void setTs(int ts) {
        Ts = ts;
    }

    public List<Statistics> getStats() {
        return stats;
    }

    public void setStats(List<Statistics> stats) {
        this.stats = stats;
    }
}
