package tp.enistore.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.enistore.bo.Article;

@RestController
public class DemoRestController {

    @GetMapping("/test/re")
    public ResponseEntity<Article> test() {
        Article article = new Article();
        article.setTitle("Test");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(article);
    }

}
