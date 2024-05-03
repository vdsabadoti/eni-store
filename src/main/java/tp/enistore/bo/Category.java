package tp.enistore.bo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {

    private String id;

    private String uid;

    private String label;

    public Category(String id, String uid, String label) {
        this.id = id;
        this.uid = uid;
        this.label = label;
    }

    public Category(String uid, String label) {
        this.uid = uid;
        this.label = label;
    }

    public Category(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
