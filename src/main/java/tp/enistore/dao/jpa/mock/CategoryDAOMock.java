package tp.enistore.dao.jpa.mock;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.enistore.bo.Category;
import tp.enistore.dao.jpa.CategoryDAO;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("mock")
public class CategoryDAOMock implements CategoryDAO {

    private List<Category> CATEGORIES;

    CategoryDAOMock(){
        List<Category> CATEGORIES = new ArrayList<Category>();
        CATEGORIES.add(new Category("1", "Informatique"));
        CATEGORIES.add(new Category("2", "Boulangerie"));
        CATEGORIES.add(new Category("3", "Loisir"));
    }

    @Override
    public Category findByUid(String id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void delete(String uid) {

    }
}
