package Steps;

import basic.basicSetup;
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

public class SingleUsersSteps extends basicSetup {

    private static RequestSpecification request;
    private Response response;

    @Given("^he accedido a la api single user$")
    public void heAccedidoALaApiSingleUser() {
        base_Uri_Path_Json();
        filtrosLog();
        request = given()
                .contentType(ContentType.JSON);
    }

    @When("^envio el (.*) del usario$")
    public void envioElIdDelUsario(int idUser) {
        response = request
                .when()
                .get("users/{idUser}", idUser);
    }

    @Then("^se muestra el (.*) de usuario$")
    public void seMuestraElNombreDeUsuario(String datoUser) {
        String valor = response.jsonPath().get("data.first_name");
        System.out.println("aquí hay: " + valor);
        assertEquals(datoUser, valor);
    }
}
