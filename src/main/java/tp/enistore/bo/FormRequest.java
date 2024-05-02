package tp.enistore.bo;

public class FormRequest<T> {

    private T data;
    private String idAssociation;

    public FormRequest(T data, String idAssociation) {
        this.data = data;
        this.idAssociation = idAssociation;
    }

    public FormRequest() {

    }

    public T getData() {
        return data;
    }

    public String getIdAssociation() {
        return idAssociation;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setIdAssociation(String idAssociation) {
        this.idAssociation = idAssociation;
    }
}
