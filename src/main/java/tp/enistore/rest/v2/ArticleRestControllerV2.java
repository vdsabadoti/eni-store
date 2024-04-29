package tp.enistore.rest.v2;

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
@Tag(name = "Eni-Srtore API v2")
public class ArticleRestControllerV2 {

    private ArticleService articleService;

    ArticleRestControllerV2(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Create a new article")
    @PostMapping("/api/v2/save")
    public ServiceResponse<Article> create(
            @RequestBody  Article article
    ) {
        return articleService.addArticle(article);
    }

    @Operation(summary = "Update an article")
    @PostMapping("/api/v2/update")
    public ServiceResponse<Article> update(
            @RequestBody  Article article
    ) {
        return articleService.addArticle(article);
    }

    @Operation(summary = "Get an article")
    @GetMapping("/api/v2/article/{id}")
    public ServiceResponse<Article> getArticle(
            @Parameter(description = "The UID of the article", required = true)
            @PathVariable String id
    ) {
        return articleService.getArticleByUid(id);
    }

    @Operation(summary = "Get all articles")
    @GetMapping("/api/v2/articles")
    public ServiceResponse<List<Article>> getAllArticles() {
        return articleService.getAllArticles();
    }

    @Operation(summary = "Delete an article")
    @DeleteMapping("/api/v2/delete/{id}")
    public ServiceResponse<Article> delete(
            @Parameter(description = "The UID of the article", required = true)
            @PathVariable String id) {
        return articleService.deleteArticle(id);
    }


}

