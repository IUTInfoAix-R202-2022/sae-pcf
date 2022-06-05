package fr.univ_amu.iut.DAO.entities;

import java.util.List;
import java.util.Objects;

public class AcademicRegion {
    private int id;
    private String name;

    public static AcademicRegion findByName(List<AcademicRegion> academies, String name){
        for (AcademicRegion academicRegion : academies){
            if (Objects.equals(academicRegion.getName(),name)){
                return academicRegion;
            }
        }
        return null;
    }

    public AcademicRegion(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
