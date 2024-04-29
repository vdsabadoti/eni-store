package tp.enistore.rest.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tp.enistore.bo.Article;
import tp.enistore.service.ArticleService;
import tp.enistore.service.ServiceResponse;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "Eni-Srtore API v1")
public class ArticleRestController {

    private ArticleService articleService;

    ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Create a new article")
    @PostMapping("/api/v1/save")
    public String createDepreceated(
            @RequestBody  Article article
    ) {
        try {
            articleService.addArticleDepreceated(article);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "OK";
    }

    @Operation(summary = "Update an article")
    @PostMapping("/api/v1/update")
    public String updateDepreceated(
            @RequestBody  Article article
    ) {
        try {
            articleService.addArticleDepreceated(article);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "OK";
    }


    @Operation(summary = "Get an article")
    @GetMapping("/api/v1/article/{id}")
    public Article getArticleDepreceated(
            @Parameter(description = "The UID of the article", required = true)
            @PathVariable String id
    ) {
        return articleService.getArticleByIdDepreceated(id);
    }

    @Operation(summary = "Get all articles")
    @GetMapping("/api/v1/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticlesDepreceated();
    }

    @Operation(summary = "Delete an article")
    @DeleteMapping("/api/v1/delete/{id}")
    public String delete(
            @Parameter(description = "The UID of the article", required = true)
            @PathVariable String id) {
        try {
            articleService.deleteArticleDepreceated(id);
            return "OK";
        }catch (Exception e) {
            return e.getMessage();
        }
    }


}
