package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from; // Sirve para usar con un filtro de mapa
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListarUsuariosSteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^Se conecta a la API users$")
    public void seConectaALaAPIUsers() {
        request = given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON);
    }
    @When("^obtiene la lista (.*)$")
    public void obtieneLaLista(int id) {
        response = request
                .when()
                .get("api/users?page={id}",id);
    }
    @Then("^se obtiene el status (.*)$")
    public void seObtieneElStatus(int status) {
        json = response
                .then()
                .statusCode(status);

    }
    @Then("^validamos los (.*) items de la lista$")
    public void validamosLosItemsDeLaLista(int item) {
        /*
     //En jsonResponse se almacena la lista de usuarios, en get list se pone el nombre del arreglo que se  muestra en el response
           "data": [  {
      "id": 7,
         */
        List<String> jsonResponse = response.jsonPath().getList("data");
        //Estamos almacenando la cantidad de items
        int actualSize = jsonResponse.size();
        assertEquals(item,actualSize);
    }
    @Then("^verificamos (.*) en la lista$")
    public void verificamosUsuarioEnLaLista(String usuario) {
       /*
       validamos si existe usuario ingresado por parámetro en el index 0 del arreglo
        List<String> usuarioResponse = response.jsonPath().getList("data.first_name");
        String validUser = usuarioResponse.get(0);
        assertEquals(validUser,usuario);
        */
        // Aquí validamos si el usuario existe en toda la lista usuarios con un true o un false
        List<String> usuarioResponse = response.jsonPath().getList("data.first_name");
        assertTrue(usuarioResponse.contains(usuario));

        List<Map> usersWithIdGreatThan10 = from((InputStream) usuarioResponse).get("data.findAll {user -> user.id > 10}");

    }
}


