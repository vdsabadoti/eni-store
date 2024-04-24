package tp.enistore.dao.jpa;

import tp.enistore.bo.Article;

import java.util.List;

public interface ArticleDAO {

    public List<Article> findAll();

    public Article findByUid(String id);

    public void save(Article article) throws Exception;

    public void deleteByUid(String id);

}
