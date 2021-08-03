package mx.edu.utez.model.category;

public class BeanCategory {
    private int idcategory;
    private String namecategory;
    private int status;

    public BeanCategory() {
    }

    public BeanCategory(int idcategory, String namecategory, int status) {
        this.idcategory = idcategory;
        this.namecategory = namecategory;
        this.status = status;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
