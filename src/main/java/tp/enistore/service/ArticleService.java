package tp.enistore.service;

import org.springframework.stereotype.Service;
import tp.enistore.bo.Article;
import tp.enistore.bo.Category;
import tp.enistore.bo.FormRequest;
import tp.enistore.dao.jpa.ArticleDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private ArticleDAO articleDAO;
    private CategoryService categoryService;

    ArticleService(ArticleDAO articleDAO, CategoryService categoryService) {
        this.articleDAO = articleDAO;
        this.categoryService = categoryService;
    }

    @Deprecated
    public List<Article> getAllArticlesDepreceated() {
        return getAllArticles().data;
    }

    @Deprecated
    public Article getArticleByIdDepreceated(String id) {
        return getArticleByUid(id).data;
    }

    @Deprecated
    public void addArticleDepreceated(Article article) {
        FormRequest<Article> request = new FormRequest<Article>();
        request.setData(article);
        request.setIdAssociation("eukrgbr");
        addArticle(request);
    }

    @Deprecated
    public void deleteArticleDepreceated(String id) {
        deleteArticle(id);
    }

    public ServiceResponse<List<Article>> getAllArticles() {
        return articleDAO.findAll();
    }

    public ServiceResponse<Article> getArticleByUid(String uid) {
        return articleDAO.findByUid(uid);
    }

    public ServiceResponse<Article> addArticle(FormRequest<Article> resquest) {
        Category category = this.categoryService.findByUid(resquest.getIdAssociation());
        Article article = resquest.getData();

        article.setCategory(category);
        return articleDAO.save(article);
    }

    public ServiceResponse<Article> deleteArticle(String uid) {
        return articleDAO.deleteByUid(uid);
    }

    private ServiceResponse<List<String>> articleCheck(Article article){
        ServiceResponse<List<String>> response = new ServiceResponse<List<String>>();
        response.data = new ArrayList<String>();
        if (article.getTitle().length() < 10){
            response.data.add("Le titre doit contenir plus de 10 caractères ");
            response.code = 710;
            response.message = "Controle de surface non valide";
        }
        return response;
    }

}
