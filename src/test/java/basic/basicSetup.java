package basic;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.requestSpecification;

public class basicSetup {

    public static void base_Uri_Path_Json(){
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath =  "api/";
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
    public static void filtrosLog(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    public void restApi(){
        RestAssured.reset();
    }
}
