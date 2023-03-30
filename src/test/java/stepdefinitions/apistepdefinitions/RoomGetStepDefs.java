package stepdefinitions.apistepdefinitions;

import io.cucumber.java.en.Given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.Room;

import java.io.IOException;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.MedunnaRoomStepDefs.roomNumber;


/*
    "createdBy": "zeyoran",
        "createdDate": "2023-03-30T19:28:01.022887Z",
        "id": 32637,
        "roomNumber": 1416,
        "roomType": "SUITE",
        "status": true,
        "price": 7865.00,
        "description": "SUITE ODA"
    },


 */


public class RoomGetStepDefs {
    @Given("user sends get request and validate")
    public void user_sends_get_request_and_validate() throws IOException {
        //Set the url
        //https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first", "api", "second", "rooms").
                queryParam("sort", "createdDate,desc");
        //Set the expected data
        Room expectedData = new Room("Created By Selenium For Api Test", 123, roomNumber, "SUITE", true);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(200, response.statusCode());


        JsonPath jsonPath = response.jsonPath();
        //Data list dönüyor. O yüzden düzenleme olacaktır...
        String actualRoomNumber = jsonPath.getString("findAll{it.roomNumber==" + roomNumber + "}.roomNumber");
        String actualRoomType = jsonPath.getString("findAll{it.roomNumber==" + roomNumber + "}.roomType");
        boolean actualStatus = jsonPath.getBoolean("findAll{it.roomNumber==" + roomNumber + "}.status");
        String actualPrice = jsonPath.getString("findAll{it.roomNumber==" + roomNumber + "}.price");
        String actualDescription = jsonPath.getString("findAll{it.roomNumber==" + roomNumber + "}.description");

        assertEquals(expectedData.getRoomNumber(), actualRoomNumber);
        assertEquals(expectedData.getRoomType(), actualRoomType);
        assertEquals(expectedData.getStatus(), actualStatus);
        assertEquals(expectedData.getPrice(), actualPrice);
        assertEquals(expectedData.getDescription(), actualDescription);

    }
}

