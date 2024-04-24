package tp.enistore.dao.jpa.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.enistore.bo.Article;
import tp.enistore.dao.jpa.ArticleDAO;
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
    public List<Article> findAll() {
        return articleMongoRepository.findAll();
    }

    @Override
    public Article findByUid(String id) {
        return articleMongoRepository.findByUid(id);
    }

    @Override
    public void save(Article article) throws Exception {
        if (article.getUid() != null){
            Article articleToUp = articleMongoRepository.findByUid(article.getUid());
            if (articleToUp == null){
                throw new Exception("Article does not exist in DBB");
            }
            article.setId(articleToUp.getId());
        } else {
            UUID uuid = UUID.randomUUID();
            article.setUid(uuid.toString());
        }
        articleMongoRepository.save(article);
    }

    @Override
    public void deleteByUid(String id) {
        articleMongoRepository.deleteByUid(id);
    }

}
