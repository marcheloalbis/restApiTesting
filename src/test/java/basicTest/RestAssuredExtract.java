package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredExtract {

    @Test
    public void restAssuredVerification(){
        JSONObject body= new JSONObject();
        body.put("Content","MarceloCheck");
        body.put("Icon",1);

        given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(body.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .statusCode(200)
                .body("Content", equalTo("MarceloCheck"))
                .body("Icon",equalTo(1))
                .log()
                .all();
    }


    @Test
    public void restAssuredExtractor(){
        JSONObject body= new JSONObject();
        body.put("Content","MarceloCheck");
        body.put("Icon",1);

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

        int id = response.then().extract().path("Id");
        System.out.println("****** ID ***** : "+id);
    }
}
