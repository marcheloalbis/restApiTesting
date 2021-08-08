package basicTest;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredSchemaValidation {

    @Test
    public void restAssuredSchema(){
        JSONObject body= new JSONObject();
        body.put("Content","MarceloCheck");
        body.put("Icon",1);

        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

        Response response=given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(body.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("MarceloCheck"))
                .body("Icon",equalTo(1))
                .log()
                .all();

        response.then()
                .body(matchesJsonSchemaInClasspath("validationSchemaCreateProject.json").using(jsonSchemaFactory));


    }
}
