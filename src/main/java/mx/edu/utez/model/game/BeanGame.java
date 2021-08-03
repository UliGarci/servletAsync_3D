package mx.edu.utez.model.game;

import mx.edu.utez.model.category.BeanCategory;

import java.io.File;

public class BeanGame {
    private int idgame;
    private String nombregame;
    private String imggame;
    private BeanCategory category_idcategory;
    private String datepremiere;
    private int status;

    public BeanGame() {
    }

    public BeanGame(int idgame, String nombregame, String imggame, BeanCategory category_idcategory, String datepremiere, int status) {
        this.idgame = idgame;
        this.nombregame = nombregame;
        this.imggame = imggame;
        this.category_idcategory = category_idcategory;
        this.datepremiere = datepremiere;
        this.status = status;
    }

    public int getIdgame() {
        return idgame;
    }

    public void setIdgame(int idgame) {
        this.idgame = idgame;
    }

    public String getNombregame() {
        return nombregame;
    }

    public void setNombregame(String nombregame) {
        this.nombregame = nombregame;
    }

    public String getImggame() {
        return imggame;
    }

    public void setImggame(String imggame) {
        this.imggame = imggame;
    }

    public BeanCategory getCategory_idcategory() {
        return category_idcategory;
    }

    public void setCategory_idcategory(BeanCategory category_idcategory) {
        this.category_idcategory = category_idcategory;
    }

    public String getDatepremiere() {
        return datepremiere;
    }

    public void setDatepremiere(String datepremiere) {
        this.datepremiere = datepremiere;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCategory_idcategory(int idcategory) {
    }
}
