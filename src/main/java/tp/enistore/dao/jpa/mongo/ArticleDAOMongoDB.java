package tp.enistore.dao.jpa.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.enistore.bo.Article;
import tp.enistore.dao.jpa.ArticleDAO;
import tp.enistore.service.ServiceResponse;

import java.util.UUID;

import java.util.List;

@Component
@Profile("mongo")
public class ArticleDAOMongoDB implements ArticleDAO {

    private ArticleMongoRepository articleMongoRepository;

    ArticleDAOMongoDB(ArticleMongoRepository articleMongoRepository){
        this.articleMongoRepository = articleMongoRepository;
    }

    @Override
    public ServiceResponse<List<Article>> findAll() {
        ServiceResponse<List<Article>> response = new ServiceResponse<List<Article>>();
        response.data = articleMongoRepository.findAll();
        if (response.data.isEmpty()) {
            response.code = 722;
            response.message = "No articles found";
            return response;
        }
        response.code = 200;
        response.message = "Articles récupérés avec succès";
        return response;
    }

    @Override
    public ServiceResponse<Article> findByUid(String id) {
        ServiceResponse<Article> response = new ServiceResponse<Article>();
        response.data = articleMongoRepository.findByUid(id);
        if (response.data == null) {
            response.code = 702;
            response.message = "Article not found (id = " + id + ")";
            return response;
        }
        response.code = 200;
        response.message = "Article récupéré avec succès";
        return response;
    }

    @Override
    public ServiceResponse<Article> save(Article article) {
        ServiceResponse<Article> response = new ServiceResponse<>();
        if (article.getUid() != null){
            Article articleToUp = articleMongoRepository.findByUid(article.getUid());
            if (articleToUp == null){
                response.code = 710;
                response.message = "No such article with id " + article.getUid();
                response.data = null;
                return response;
            }
            article.setId(articleToUp.getId());
            response.message = "Article modifié avec succès";
        } else {
            UUID uuid = UUID.randomUUID();
            article.setUid(uuid.toString());
            response.message = "Article ajouté avec succès";
        }
        response.code = 200;
        response.data = article;
        articleMongoRepository.save(article);
        return response;
    }

    @Override
    public ServiceResponse<Article> deleteByUid(String id) {
        ServiceResponse<Article> response = new ServiceResponse<>();
        Article articleToDelete = articleMongoRepository.findByUid(id);
        if (articleToDelete != null){
            articleMongoRepository.deleteByUid(id);
            response.code = 200;
            response.data = articleToDelete;
            response.message = "L'article " + id + " a été supprimé avec succès";
            return response;
        }
        response.code = 702;
        response.message = "Impossible de supprimer un article dont l'UID n'existe pas";
        response.data = null;
        return response;
    }
}
