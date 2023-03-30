package stepdefinitions.apistepdefinitions;

import io.cucumber.java.en.Given;
import pojos.Room;

import static base_urls.MedunnaBaseUrl.spec;

public class RoomGetStepDefs {
    @Given("user sends het request and validate")
    public void user_sends_het_request_and_validate() {
        // Set the url
        // https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first","api","second","rooms").
                queryParam("sort","createdDate,desc");
        // Set the expected data
        Room expectedData = new Room();
    }
}

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