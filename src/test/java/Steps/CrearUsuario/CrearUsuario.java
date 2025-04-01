package Steps.CrearUsuario;

import basic.basicSetup;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import static io.restassured.RestAssured.given;


public class CrearUsuario extends basicSetup {

    public static RequestSpecification peticion;
    public Response response;

    @Given("^que el usuario ya tiene acceso a la API$")
    public void queElUsuarioYaTieneAccesoALaAPI() {

      peticion = given()
              .contentType(ContentType.JSON);
    }

    @When("^crear el usuario$")
    public void crearElUsuario() {

        File bodyCrear = new File("src/test/resources/BodyJson/Request/bodyPost.json");
        response = peticion
                .when()
                .body(bodyCrear)
                .post("/users");
    }

    @Then("^responde con el status (.*)$")
    public void RespondeConElStatus(int numStatus) {
        response.then().statusCode(numStatus);
    }
}
