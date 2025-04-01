package Steps.Repaso;

import basic.basicSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.urlEncodingEnabled;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class GetParameters extends basicSetup {

    public static RequestSpecification request;
    public Response response;

    @Given("^el usuario ha tenido acceso a la api$")
    public void elUsuarioHaTenidoAccesoALaApi() {
        base_Uri_Path_Json();
        filtrosLog();
        request = given();
    }
    @When("^solicita la pagina (.*)$")
    public void solicitaLaPagina(int numPagina) {
        response = request
                .when()
                .queryParam("page", numPagina)
                .get("users?page={page}",numPagina);
    }
    @Then("^se muestra el estatus (.*)$")
    public void seMuestraElEstatus(int numStatus) {
        response.then().statusCode(numStatus)
                .body("data.email", hasItem("rachel.howell@reqres.in"))
                .body("data.last_name[5]",equalTo("Howell"))
                .time(lessThan(2000l), TimeUnit.MILLISECONDS)
                .body(matchesJsonSchemaInClasspath("BodyJson/Response/bodyResponseListUser.json"));
        response.then().body(matchesJsonSchemaInClasspath("BodyJson/Response/bodyResponseListUser.json"));
        String capturaVariable =  response.jsonPath().getString("data.last_name[5]");
        System.out.println("Aquí se muestra la variable almacenada: "+capturaVariable);

        restApi();
    }
}
