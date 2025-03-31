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

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DeleteUserSteps {
    @Before
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/users";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    private static RequestSpecification request;
    private Response response;

    @Given("^tenemos acceso a la api delete$")
    public void tenemosAccesoALaApiDelete() {
        request = given()
                .contentType(ContentType.JSON);
    }

    @When("^ingreso el (.*) a eliminar$")
    public void ingresoElUsuarioAEliminar(int idUsuario) {
        response = request
                .when()
                .delete("/{idUsuario}",idUsuario);
    }

    @Then("^obtengo un status cod (.*)$")
    public void obtengoUnStatusCod(int statusCode) {
        assertEquals(response.statusCode(),statusCode);
    }

}
