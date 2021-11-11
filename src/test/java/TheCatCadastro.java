import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import org.junit.*;
import java.io.IOException;
import java.nio.file.*;



public class TheCatCadastro {
    String uri =  "https://api.thecatapi.com/v1/user/passwordlesssignup";
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void thecatCadastro () throws IOException {
        String jsonCadastro = lerJson("db/cadastro.json");
                given()
                        .contentType("application/json")
                        .body(jsonCadastro)
                .when()
                        .post(uri)
                .then()
                        .statusCode(400)
                        .log().all();
    }

    @Test
    public void thecatCampoObrigatorio (){
                given()
                    .contentType("application/json")
                    .body("{\"appDescription\": \"Denis Wilson Teste\", \"opted_into_mailing_list\": true}")
                .when()
                    .post(uri)
                .then()
                        .log().all()
                        //Validar se está apresentando a mensagem de email obrigatório, quando não enviar no body
                        .body("message", containsString ("\"email\" is required") )
                        .statusCode(400);
    }
    /*
    @Test
    public void thecatCadastro () throws IOException {
                String jsonCadastro = lerJson("db/cadastro.json");
                given()
                    .log().all()
                    .contentType("application/json")
                    .body(jsonCadastro)
                .when()
                    .post(uri)
                .then()
                    .statusCode(400)
                    .log().all();
    }*/
}
