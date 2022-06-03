package fr.univ_amu.iut.DAO.entities;

import java.util.List;
import java.util.Objects;

public class Academy {
    private int id;
    private String name;

    public static Academy findByName(List<Academy> academies, String name){
        for (Academy academy : academies){
            if (Objects.equals(academy.getName(),name)){
                return academy;
            }
        }
        return null;
    }

    public Academy(){

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
