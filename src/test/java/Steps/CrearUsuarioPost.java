package Steps;

import basic.basicSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CrearUsuarioPost extends basicSetup {

    public static RequestSpecification request;
    public Response response;

    @Given("^usuario ya tiene acceso a la api$")
    public void usuarioYaTieneAccesoALaApi() {
        request =  given()
                .contentType(ContentType.JSON);
    }

    @When("^envía los datos del nuevo usuario a registrar$")
    public void enviaLosDatosDelNuevoUsuarioARegistrar() {
        File bodyPost =  new File("src/test/resources/BodyJson/Request/bodyPost.json");
        response =  request
                .when()
                .body(bodyPost)
                .post("/register");
    }

    @Then("^la api responde el estado (.*)$")
    public void laApiRespondeElEstado(int num) {
        response.then().statusCode(num)
                .body("id", equalTo(4), "token",equalTo("QpwL5tke4Pnpja7X4"));
    }
}
