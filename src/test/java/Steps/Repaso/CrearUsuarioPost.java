package Steps.Repaso;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class CrearUsuarioPost {
    @Before
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
    public static RequestSpecification request;
    public Response response;
    @After
    public void RestApiPost(){
        RestAssured.reset();
    }

    @Given("^que el usuario tiene acceos a la api crear usuario$")
    public void queElUsuarioTieneAcceosALaApiCrearUsuario() {
        request = given()
                .contentType(ContentType.JSON);
    }
    @When("^envía los datos por archivo json$")
    public void enviaLosDatosPorArchivoJson() {
        File crearUsuarioBody = new File("src/test/resources/BodyJson/Request/CrearUsuarioBody.json");

        response =  request
                .when()
                .body(crearUsuarioBody)
                .post("/users");
    }
    @Then("^la api responde con el (.*)$")
    public void laApiRespondeConEl(int estatusCode) {
        response
                .then()
                .statusCode(estatusCode);
    }
    @And("^valida el (.*) del nuevo usuario en el response$")
    public void validaElNombreDelNuevoUsuarioEnElResponse(String nombre) {
        response
                .then()
                .body("name", equalTo(nombre));
    }
}
