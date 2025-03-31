package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class GetListUser {

    //Esta funciona accede a la url, al pat y también declaro los filtros log para no estar colocando .log.all
    public void setup(){
        RestAssured.baseURI =  "https://reqres.in";
        RestAssured.basePath = "/api/" ;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
    private static RequestSpecification request;
    private Response response;
    @After
    public void cleanRestAssured(){
        RestAssured.reset();
    }

    @Given("^que he accedido a api List Users$")
    public void queHeAccedidoAApiListUsers() {
        request =  given();
    }

    @When("^solicito lista por (.*)$")
    public void solicitoListaPorPagina(int pag) {
        response =  request
                .when()
                .get("users?page={pag}",pag);
    }

    @Then("^el status es (.*)$")
    public void elStatusEs(int estadoResponse) {
        //Validamos el estado de la respuesta y el tiempo de la respuesta milesengundos
        // si la respuesta es mayor a 200 milesegundos este va a terminar en error
        response.then()
                .statusCode(estadoResponse)
                .body("data.id[0]", equalTo(7),//Comparamos si en la posición 0 viene el id 7
                        "data.first_name", hasItems("Michael","Lindsay")) //comparamos si en first_name vienen los nombres Michel y Lindsay
                .time(lessThan(2000l), TimeUnit.MILLISECONDS);
    }

    @And("^la lista de usuarios$")
    public void laListaDeUsuarios() {

        //comparamos la respuesta Json con el esquema que tenemos en el archivo bodyResponseListUser.json
        //El esquema es los tipos de datos de cada campo de la estructura, ejem: string, number, integer, etc.
       response.then()
               .body(matchesJsonSchemaInClasspath("BodyJson/Response/bodyResponseListUser.json")).log().all();

       assertEquals(response.jsonPath().getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!");

    }
}
