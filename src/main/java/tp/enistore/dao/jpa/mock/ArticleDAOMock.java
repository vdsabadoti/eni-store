package tp.enistore.dao.jpa.mock;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.enistore.bo.Article;
import tp.enistore.bo.Category;
import tp.enistore.bo.Helpers;
import tp.enistore.dao.jpa.ArticleDAO;
import tp.enistore.service.ServiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Component
@Profile("mock")
public class ArticleDAOMock implements ArticleDAO {

    private List<Article> DB_ARTICLES;

    public ArticleDAOMock(){
        DB_ARTICLES = new ArrayList<Article>();
        Category category = new Category("1", "Boulangerie");

        DB_ARTICLES.add(new Article("1", "Beurre salé", category));
        DB_ARTICLES.add(new Article("2", "Beurre doux", category));
        DB_ARTICLES.add(new Article("3", "Croissant", category));
    }

    @Override
    public ServiceResponse<List<Article>> findAll() {
        ServiceResponse<List<Article>> response = new ServiceResponse<>();
        response.data = DB_ARTICLES;
        response.code = 200;
        response.message = "OK";
        return Helpers.logResponse(response);
    }

    @Override
    public ServiceResponse<Article> findByUid(String id) {
        ServiceResponse<Article> response = new ServiceResponse<>();
        //get article via predicate
        Optional<Article> foundArticle = DB_ARTICLES.stream().filter(article -> article.getUid().equals(id)).findFirst();

        return getArticleServiceResponse(response, foundArticle);
    }

    @Override
    public ServiceResponse<Article> findByTitle(String title) {
        ServiceResponse<Article> response = new ServiceResponse<>();
        //get article via predicate
        Optional<Article> foundArticle = DB_ARTICLES.stream().filter(article -> article.getTitle().equals(title)).findFirst();

        return getArticleServiceResponse(response, foundArticle);
    }

    private ServiceResponse<Article> getArticleServiceResponse(ServiceResponse<Article> response, Optional<Article> foundArticle) {
        if (foundArticle.isPresent()){
            response.data = foundArticle.get();
            response.code = 200;
            response.message = "OK";
            return Helpers.logResponse(response);
        }
        response.data = null;
        response.code = 702;
        response.message = "Not Found";
        return Helpers.logResponse(response);
    }

    @Override
    public ServiceResponse<Article> save(Article article) {
        ServiceResponse<Article> response = new ServiceResponse<>();
        Optional<Article> foundArticleByTitle = DB_ARTICLES.stream().filter(a -> a.getTitle().equals(article.getTitle())).findFirst();

        if (foundArticleByTitle.isPresent()){
            response.code = 701;
            response.data = article;
            response.message = "Title already exists";
            return Helpers.logResponse(response);
        }

        if (article.getUid() == null){
            article.setUid(UUID.randomUUID().toString());
            DB_ARTICLES.add(article);
            response.code = 200;
            response.message = "OK";
            response.data = article;
            return Helpers.logResponse(response);
        } else {
            Optional<Article> foundArticle = DB_ARTICLES.stream().filter(a -> a.getUid().equals(article.getUid())).findFirst();
            if (foundArticle.isPresent()){
                foundArticle.get().setTitle(article.getTitle());
                response.data = article;
                response.code = 200;
                response.message = "OK";
                return Helpers.logResponse(response);
            }
            response.code = 700;
            response.message = "Not found";
            response.data = null;
            return Helpers.logResponse(response);
        }
    }

    @Override
    public ServiceResponse<Boolean> deleteByUid(String id) {
        ServiceResponse<Boolean> response = new ServiceResponse<Boolean>();
        //condition générique, on peut donc l'utiliser où on le souhaite pour tester si elle est respectée
        Predicate<Article> predicate = article -> article.getUid().equals(id);
        response.data = DB_ARTICLES.removeIf(predicate);
        if (response.data){
            response.code = 200;
            response.message = "OK";
            return Helpers.logResponse(response);
        }
        response.code = 701;
        response.message = "Not Found";
        return Helpers.logResponse(response);
    }
}
