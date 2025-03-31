package Steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UpdateUserPatchSteps {
    @Before
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    private static RequestSpecification request;
    private Response response;

    @Given("^tengo conexion con el api usuario$")
    public void tengoConexionConElApiUsuario() {
        request = given()
                .contentType(ContentType.JSON);
    }

    @When("^actualice una (.*)")
    public void actualiceUnaDescripcion(String updateDescripcion) {
        File requestBody =  new File("src/test/resources/BodyJson/Request/RequestBodyPatch.json");
        response =  request
                .when()
                .body(requestBody).patch("users/2");
    }

    @Then("^obtengo el (.*)$")
    public void obtengoElStatus(int statusCode) {
        /*   int statusApi =  response.statusCode();
        assertEquals(statusCode,statusApi);
        System.out.println("aqui estoy");

         */

        response.then().statusCode(statusCode);
    }
}
