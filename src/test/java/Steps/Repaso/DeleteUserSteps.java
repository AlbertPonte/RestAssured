package Steps.Repaso;

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
import static io.restassured.RestAssured.given;

public class DeleteUserSteps extends basicSetup {

    public static RequestSpecification request;
    public Response respuesta;

    @Given("^que el usuario tiene acceso a la api$")
    public void queElUsuarioTieneAccesoALaApi() {
        request =  given()
                .contentType(ContentType.JSON);
    }
    @When("^envía el (.*) del usuario a eliminar$")
    public void enviaElIdDelUsuarioAEliminar(int Id) {
        respuesta = request
                .when()
                .delete("{Id}",Id);
    }

    @Then("^el response envía el (.*)")
    public void elResponseEnviaElEstatusCode(int statusCode) {
        respuesta
                .then()
                .statusCode(statusCode);
    }
}
