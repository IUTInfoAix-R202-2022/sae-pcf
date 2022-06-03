package fr.univ_amu.iut.DAO.entities;

import java.util.List;
import java.util.Objects;

public class Discipline {
    private int id;
    private String name;

    public static Discipline findByName(List<Discipline> disciplines, String name){
        for (Discipline discipline : disciplines){
            if (Objects.equals(discipline.getName(),name)){
                return discipline;
            }
        }
        return null;
    }

    public Discipline(){

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

    public void setName(String name) {
        this.name = name;
    }
}
