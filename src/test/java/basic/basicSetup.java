package basic;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class basicSetup {

    @BeforeClass
    public static void baseUri(){
        RestAssured.baseURI = "https://reqres.in";

    }

    public static void basePath (String path){
     RestAssured.basePath =  path;
     }
}
