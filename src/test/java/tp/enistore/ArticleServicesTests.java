package tp.enistore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tp.enistore.bo.Article;
import tp.enistore.bo.FormRequest;
import tp.enistore.service.ArticleService;
import tp.enistore.service.ServiceResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles(value = {"mock"})
public class ArticleServicesTests {

    @Autowired
    ArticleService articleService;

    /*
    Récupérer un article avec un uid existant "1" :
    le code métier doit retourner 200
    la data doit être non null
     */

    @Test
    public void test01(){
        ServiceResponse<Article> response = articleService.getArticleByUid("1");
        assertEquals(response.code, 200);
        assertNotNull(response.data);
    }

    /*
    Test 02
    Récupérer un article avec un uid inexistant "6" :
    le code métier doit retourner 702
    la data doit être null
     */
    @Test
    public void test02(){
        ServiceResponse<Article> response = articleService.getArticleByUid("6");
        assertEquals(response.code, 702);
        assertEquals(response.data, null);
    }

    /*
    Test 03
    Supprimer un article avec un uid inexistant "6" :
    le code métier doit retourner 701
    la data doit être false
     */
    @Test
    public void test03(){
        ServiceResponse<Boolean> delete = articleService.deleteArticle("6");
        assertEquals(delete.code, 701);
        assertEquals(delete.data, Boolean.FALSE);
    }

    /*
    Test 04
    Supprimer un article avec un uid existant "2" :

    le code métier doit retourner 200
    la data doit être true
    Essayer de récupérer l'article qui vient d'être supprimé ("2") :

    le code métier doit retourner 701
    la data doit être null
     */
    @Test
    public void test04(){
        ServiceResponse<Boolean> delete = articleService.deleteArticle("2");
        assertEquals(delete.code, 200);
        assertEquals(delete.data, Boolean.TRUE);
        ServiceResponse<Article> response = articleService.getArticleByUid("2");
        assertEquals(response.code, 702);
        assertEquals(response.data, null);
    }

    /*
    Test 05
    Créer un article avec un titre :

    le code métier doit retourner 200
    la data doit être non null
    Essayer de récupérer l'article qui vient d'être crée :

    le code métier doit retourner 200
    la data doit être non null
     */
    @Test
    public void test05(){
        ServiceResponse<Article> newArticle = articleService.addArticle(
                new FormRequest<Article>(
                        new Article("Pain au chocolat"),
                "1")
        );
        assertEquals(newArticle.code, 200);
        assertNotNull(newArticle.data);
        ServiceResponse<Article> response = articleService.getArticleByUid(newArticle.data.getUid());
        assertEquals(response.code, 200);
        assertNotNull(response.data);
    }

    /*
    Test 06
    Modifier l'article (1) avec un titre différent :

    le code métier doit retourner 200
    la data doit être non null
    Modifier l'article (1) avec le même titre que l'article (3) :

    le code métier doit retourner 701
     */
    @Test
    public void test06(){
        ServiceResponse<Article> updtArticle = articleService.addArticle(
                new FormRequest<Article>(
                        new Article("1","Pain au chocolat"),
                        "1")
        );
        assertEquals(updtArticle.code, 200);
        assertNotNull(updtArticle.data);

        ServiceResponse<Article> responseArticle3 = articleService.getArticleByUid("3");
        ServiceResponse<Article> changeTitleArticle = articleService.addArticle(
                new FormRequest<Article>(
                        new Article("1",responseArticle3.data.getTitle()),
                        "1")
        );

        assertEquals(changeTitleArticle.code, 701);
    }

}
