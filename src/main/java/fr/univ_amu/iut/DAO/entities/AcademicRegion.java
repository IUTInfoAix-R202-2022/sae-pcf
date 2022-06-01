package fr.univ_amu.iut.DAO.entities;

public class AcademicRegion {
    private int id;
    private String name;

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
