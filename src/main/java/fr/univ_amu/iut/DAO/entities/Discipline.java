package fr.univ_amu.iut.DAO.entities;

public class Discipline {
    private int id;
    private String nom;

    public Discipline(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
