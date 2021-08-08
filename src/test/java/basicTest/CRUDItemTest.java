package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDItemTest {

      @Test
      public void crudItemRestApi(){
          // Creacion
          JSONObject body= new JSONObject();
          body.put("Content","MarceloCRUD");
        //  body.put("Icon",1);

          Response response=given()
                  .auth()
                  .preemptive()
                  .basic("ucb@ucb2021.com","12345")
                  .body(body.toString())
                  .log()
                  .all()
          .when()
                  .post("http://todo.ly/api/items.json");

          response.then()
                  .statusCode(200)
                  .body("Content", equalTo("MarceloCRUD"))
                 // .body("Icon",equalTo(1))
                  .log()
                  .all();

          int id = response.then().extract().path("Id");

          // Actualizacion
          body.put("Content","MarceloCRUDUpdate");
         // body.put("Icon",4);


          response=given()
                  .auth()
                  .preemptive()
                  .basic("ucb@ucb2021.com","12345")
                  .body(body.toString())
                  .log()
                  .all()
          .when()
                  .put("http://todo.ly/api/items/"+id+".json");

          response.then()
                  .statusCode(200)
                  .body("Content", equalTo("MarceloCRUDUpdate"))
                 // .body("Icon",equalTo(4))
                  .log()
                  .all();
// Get
          response=given()
                  .auth()
                  .preemptive()
                  .basic("ucb@ucb2021.com","12345")
                  .log()
                  .all()
                  .when()
                  .get("http://todo.ly/api/items/"+id+".json");

          response.then()
                  .statusCode(200)
                  .body("Content", equalTo("MarceloCRUDUpdate"))
                 // .body("Icon",equalTo(4))
                  .log()
                  .all();
          // Delete
          response=given()
                  .auth()
                  .preemptive()
                  .basic("ucb@ucb2021.com","12345")
                  .log()
                  .all()
         .when()
                  .delete("http://todo.ly/api/items/"+id+".json");

          response.then()
                  .statusCode(200)
                  .body("Content", equalTo("MarceloCRUDUpdate"))
                 // .body("Icon",equalTo(4))
                  .body("Deleted",equalTo(true))
                  .log()
                  .all();




      }








}
