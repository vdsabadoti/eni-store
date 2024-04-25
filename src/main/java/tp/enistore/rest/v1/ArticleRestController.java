package tp.enistore.rest.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tp.enistore.bo.Article;
import tp.enistore.service.ArticleService;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "Eni-Srtore API")
public class ArticleRestController {

    private ArticleService articleService;

    ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Create a new article")
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

    @Operation(summary = "Update an article")
    @PostMapping("/api/v1/update")
    public String update(
            @RequestBody  Article article
    ) {
        try {
            articleService.addArticle(article);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "OK";
    }

    @Operation(summary = "Get an article")
    @GetMapping("/api/v1/article/{id}")
    public Article getArticle(
            @Parameter(description = "The UID of the article", required = true)
            @PathVariable String id
    ) {
        return articleService.getArticleById(id);
    }

    @Operation(summary = "Get all articles")
    @GetMapping("/api/v1/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @Operation(summary = "Delete an article")
    @DeleteMapping("/api/v1/delete/{id}")
    public String delete(
            @Parameter(description = "The UID of the article", required = true)
            @PathVariable String id) {
        try {
            articleService.deleteArticle(id);
            return "OK";
        }catch (Exception e) {
            return e.getMessage();
        }
    }


}
