package basicTest;

import org.json.JSONObject;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RestAssuredBody {

    @Test
    public void createProjectString(){
        given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body("{\n" +
                        "  \"Content\":\"UCB_MarceloREST\",\n" +
                        "  \"Icon\":\"5\"\n" +
                        "}")
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json")
                .then()
                .log()
                .all();
    }

    @Test
    public void createProjectExternalFile(){
        given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(new File("C:\\Users\\Marcelo\\Desktop\\UCB2021\\ucb_rest_api\\src\\test\\resources\\projectBody.json"))
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .log()
                .all();
    }


    @Test
    public void createProjectJSONObject(){
        JSONObject body= new JSONObject();
        body.put("Content","MarceloJSONObject");
        body.put("Icon",4);

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
                .log()
                .all();
    }

}
