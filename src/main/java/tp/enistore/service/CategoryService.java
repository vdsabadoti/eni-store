package tp.enistore.service;

import org.springframework.stereotype.Service;
import tp.enistore.bo.Category;
import tp.enistore.dao.jpa.CategoryDAO;

import java.util.UUID;

@Service
public class CategoryService {

    private CategoryDAO categoryDAO;

    CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public Category findByUid(String id) {
        return categoryDAO.findByUid(id);
    }

    public ServiceResponse<Category> addCategory(Category category) {
        ServiceResponse<Category> response = new ServiceResponse<>();
        if (category == null) {
            response.code = 710;
            response.message = "El campo no puede ser nulo";
            return response;
        }
        UUID uuid = UUID.randomUUID();
        category.setUid(uuid.toString());
        this.categoryDAO.save(category);
        response.message = "El campo se ha registrado correctamente";
        response.code = 200;
        response.data = category;
        return response;
    }

    public ServiceResponse<Category> updateCategory(Category category) {
        ServiceResponse<Category> response = new ServiceResponse<>();
        if (category == null) {
            response.code = 710;
            response.message = "El campo no puede ser nulo";
            return response;
        }
        this.categoryDAO.save(category);
        response.message = "El campo se ha actualizado correctamente";
        response.code = 200;
        response.data = category;
        return response;
    }

    public ServiceResponse<Category> deleteCategory(String uid) {
        ServiceResponse<Category> response = new ServiceResponse<>();
        Category category = this.categoryDAO.findByUid(uid);
        if (category == null) {
            response.code = 710;
            response.message = "El campo no existe";
            return response;
        }
        this.categoryDAO.delete(uid);
        response.message = "El campo se ha eliminado correctamente";
        response.code = 200;
        response.data = category;
        return response;
    }

}
