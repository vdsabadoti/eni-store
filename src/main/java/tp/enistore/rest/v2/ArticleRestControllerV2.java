package tp.enistore.rest.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tp.enistore.bo.Article;
import tp.enistore.bo.Category;
import tp.enistore.bo.FormRequest;
import tp.enistore.service.ArticleService;
import tp.enistore.service.CategoryService;
import tp.enistore.service.ServiceResponse;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "Eni-Srtore API v2")
public class ArticleRestControllerV2 {

    private ArticleService articleService;
    private CategoryService categoryService;

    ArticleRestControllerV2(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @Operation(summary = "Create a new article")
    @PostMapping("/api/v2/save")
    public ServiceResponse<Article> create(
            @RequestBody FormRequest<Article> request
    ) {
        return articleService.addArticle(request);
    }

    @Operation(summary = "Update an article")
    @PostMapping("/api/v2/update")
    public ServiceResponse<Article> update(
            @RequestBody  FormRequest<Article> request
    ) {
        return articleService.addArticle(request);
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
    @DeleteMapping("/api/v2/admin/delete/{id}")
    public ServiceResponse<Boolean> delete(
            @Parameter(description = "The UID of the article", required = true)
            @PathVariable String id) {
        return articleService.deleteArticle(id);
    }

    @Operation(summary = "Create a category (admin)")
    @PostMapping("/api/v2/admin/add-category")
    public ServiceResponse<Category> createCategory(
            @RequestBody Category category
    ){
        return this.categoryService.addCategory(category);
    }

    @Operation(summary = "Update a category (admin)")
    @PatchMapping("/api/v2/admin/update-category")
    public ServiceResponse<Category> updateCategory(
            @RequestBody Category category
    ){
        return this.categoryService.updateCategory(category);
    }

    @Operation(summary = "Delete a category (admin)")
    @DeleteMapping("/api/v2/admin/delete-category")
    public ServiceResponse<Category> deleteCategory(
            @Parameter(description = "The UID of the category", required = true)
            @PathVariable String uid
    ){
        return this.categoryService.deleteCategory(uid);
    }

}

