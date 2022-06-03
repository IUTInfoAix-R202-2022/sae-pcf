package fr.univ_amu.iut.DAO.entities;

public class Typology {
    private int id;
    private int idThemeOfUse;
    private int idDiscipline;
    private int idDegree;
    private int idAcademy;
    private int idAcademicRegion;
    private String actorType;
    private String link;
    private String resourceName;
    private String resourceType;
    private String Commentary;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public int getIdThemeOfUse() {
        return idThemeOfUse;
    }

    public void setIdThemeOfUse(int idThemeOfUse) {
        this.idThemeOfUse = idThemeOfUse;
    }

    public int getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public int getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(int id) {
        this.idDegree = id;
    }

    public int getIdAcademy() {
        return idAcademy;
    }

    public void setIdAcademy(int id) {
        this.idAcademy = id;
    }

    public int getIdAcademicRegion() {
        return idAcademicRegion;
    }

    public void setIdAcademicRegion(int id) {
        this.idAcademicRegion = id;
    }

    public String getActorType() {
        return actorType;
    }

    public void setActorType(String actorType) {
        this.actorType = actorType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCommentary() {
        return Commentary;
    }

    public void setCommentary(String commentary) {
        Commentary = commentary;
    }
}
