import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TheCatVotar {
    String idvotacao;

    @BeforeClass
    public static void apiURL(){
        baseURI = "https://api.thecatapi.com/v1";
    }

    //Chave: d5a319b2-705a-4542-aa64-867d2568a9e9

    @Test
    public void createVotes (){
                    given()
                        .contentType("application/json")
                        .header("x-api-key","81e6f295-3024-49c2-a0a2-d336473ac4ba")
                        .body("{\"image_id\": \"asf2\",\"sub_id\": \"my-user-1234\",\"value\": 1}")
                    .when()
                         .post("/votes")
                    .then()
                        .statusCode(200)
                        .body("message", is("SUCCESS"))
                        .log().all();
    }


        @Test
        public void pegarVotes (){
            Response response =
                    given()
                        .header("x-api-key","81e6f295-3024-49c2-a0a2-d336473ac4ba")
                    .when()
                        .get("/votes");
                    response.then()
                        .log().all()
                        .body("image_id", hasItems("asf2"));
                    idvotacao = response.jsonPath().getString("id");
                        //Se for um array é só passar a posição "id[0]"
                    System.out.println("Id retornado=>" + idvotacao);
        }

        @Test
        public void deletarVotes (){
                    createVotes();
                    pegarVotes();

                    given()
                            .contentType("application/json")
                            .header("x-api-key","81e6f295-3024-49c2-a0a2-d336473ac4ba")
                            .pathParam("vote_id",idvotacao)
                    .when()
                            .delete("/votes/{vote_id}")
                    .then()
                        //.statusCode(200)
                        .log().all();
        }
}



