package fr.univ_amu.iut.DAO.entities;

public class Typology {
    private int id;
    private int idThemeOfUse;
    private int idDiscipline;
    private int degree;
    private int academy;
    private int academicRegion;
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

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getAcademy() {
        return academy;
    }

    public void setAcademy(int academy) {
        this.academy = academy;
    }

    public int getAcademicRegion() {
        return academicRegion;
    }

    public void setAcademicRegion(int academicRegion) {
        this.academicRegion = academicRegion;
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
