package fr.univ_amu.iut.DAO.entities;

public class ActorIdentity {
    private int id;
    private int idTypo;
    private String name;
    private String firstName;

    public ActorIdentity(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTypo() {
        return idTypo;
    }

    public void setIdTypo(int idTypo) {
        this.idTypo = idTypo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
