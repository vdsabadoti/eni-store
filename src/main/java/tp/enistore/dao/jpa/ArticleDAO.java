package tp.enistore.dao.jpa;

import tp.enistore.bo.Article;
import tp.enistore.service.ServiceResponse;

import java.util.List;

public interface ArticleDAO {

    public ServiceResponse<List<Article>> findAll();

    public ServiceResponse<Article> findByUid(String id);

    ServiceResponse<Article> findByTitle(String title);

    public ServiceResponse<Article> save(Article article);

    public ServiceResponse<Boolean> deleteByUid(String id);

}
