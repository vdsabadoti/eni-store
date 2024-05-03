package tp.enistore.dao.jpa.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp.enistore.bo.Category;
import tp.enistore.dao.jpa.CategoryDAO;

import java.util.List;

@Component
@Profile("mongo")
public class CategoryDAOMongoDB implements CategoryDAO {

    private CategoryMongoRepository categoryMongoRepository;

    public CategoryDAOMongoDB(CategoryMongoRepository categoryMongoRepository) {
        this.categoryMongoRepository = categoryMongoRepository;
    }

    @Override
    public Category findByUid(String id) {
        return categoryMongoRepository.findByUid(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryMongoRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryMongoRepository.save(category);
    }

    @Override
    public void delete(String uid) {
        categoryMongoRepository.deleteByUid(uid);
    }
}
