import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.*;

public class TheCatCadastro {
    @Test
    public void thecatCadastro (){
                given()
                        .contentType("application/json")
                        .body("{\"email\": \"denis.dabliu@gmail.com\", \"appDescription\": \"Denis Wilson Teste\", \"opted_into_mailing_list\": true}")
                .when()
                        .post("https://api.thecatapi.com/v1/user/passwordlesssignup")
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
                    .post("https://api.thecatapi.com/v1/user/passwordlesssignup")
                .then()
                        .log().all()
                        //Validar se está apresentando a mensagem de email obrigatório, quando não enviar no body
                        .body("message", containsString ("\"email\" is required") )
                        .statusCode(400);
    }

}
