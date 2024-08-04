package model;

public class Film {
    private int id;
    private String titre;
    private String description;
    private int duree;
    private int anneeSortie;
    private String paysProduction;
    private String acteursPrincipaux;
    private String image; // Changed from images to image
    private String genre;
    private double note;

    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getPaysProduction() {
        return paysProduction;
    }

    public void setPaysProduction(String paysProduction) {
        this.paysProduction = paysProduction;
    }

    public String getActeursPrincipaux() {
        return acteursPrincipaux;
    }

    public void setActeursPrincipaux(String acteursPrincipaux) {
        this.acteursPrincipaux = acteursPrincipaux;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
