package tp.enistore.rest.v1;

import org.springframework.web.bind.annotation.*;
import tp.enistore.bo.Article;
import tp.enistore.service.ArticleService;

import java.util.List;

@RestController
@CrossOrigin
public class ArticleRestController {

    private ArticleService articleService;

    ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/api/v1/save")
    public String create(
            @RequestBody  Article article
    ) {
        try {
            articleService.addArticle(article);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "OK";
    }

    @GetMapping("/api/v1/article/{id}")
    public Article getArticle(@PathVariable String id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/api/v1/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @DeleteMapping("/api/v1/delete/{id}")
    public String delete(@PathVariable String id) {
        try {
            articleService.deleteArticle(id);
            return "OK";
        }catch (Exception e) {
            return e.getMessage();
        }
    }


}
