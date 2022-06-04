package fr.univ_amu.iut.DAO.entities;

import java.util.List;
import java.util.Objects;

public class Degree {
    private int id;
    private String name;

    public static Degree findByName(List<Degree> academies, String name){
        for (Degree Degree : academies){
            if (Objects.equals(Degree.getName(),name)){
                return Degree;
            }
        }
        return null;
    }

    public Degree(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
}
