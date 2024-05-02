package tp.enistore.dao.jpa;

import tp.enistore.bo.Category;

import java.util.List;

public interface CategoryDAO {

    public Category findByUid(String id);

    public List<Category> findAll();

    public void save(Category category);

    public void delete(String uid);
}
