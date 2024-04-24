package tp.enistore.service;

import org.springframework.stereotype.Service;
import tp.enistore.bo.Article;
import tp.enistore.dao.jpa.ArticleDAO;

import java.util.List;

@Service
public class ArticleService {

    private ArticleDAO articleDAO;

    ArticleService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<Article> getAllArticles() {
        return articleDAO.findAll();
    }

    public Article getArticleById(String id) {
        return articleDAO.findByUid(id);
    }

    public void addArticle(Article article) throws Exception {
        articleDAO.save(article);
    }

    public void deleteArticle(String id) {
        if (articleDAO.findByUid(id) == null){
            throw new RuntimeException("Article not found");
        }
        articleDAO.deleteByUid(id);
    }

}
