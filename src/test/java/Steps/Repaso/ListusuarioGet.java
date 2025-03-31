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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class ListusuarioGet {

    @Before
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/";
    }
    public static RequestSpecification request;
    public Response response;

    @After
    public void ResetApi(){
        RestAssured.reset();
    }

    @Given("^que el usuario tiene conexión a la api$")
    public void queElUsuarioTieneConexionALaApi() {
        request = given()
                .contentType(ContentType.JSON);
    }

    @When("^envía el (.*) de la página de usuarios a listar$")
    public void enviaElIdDeLaPaginaDeUsuariosAListar(int id) {
        response =  request
                .when()
                .get("users?page={id}", id);
    }

    @Then("^recibe el estatus (.*)$")

    public void recibeElEstatus(int respuesta) {
        response
                .then()
                .statusCode(respuesta);
    }

    @And("^en el response se valida el (.*) del usuario$")
    public void enElResponseSeValidaElNombreDelUsuario(String nombreUsuario) {
        response.then().body("data.first_name[0]", equalTo(nombreUsuario));
        response.then().body("data.last_name", hasItem("Ferguson"));
    }
}
