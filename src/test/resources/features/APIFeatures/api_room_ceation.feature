@TC01_Api
Feature: Room Creation

  Scenario: TC01_Post_Room
    Given user sends post request for room data
    #kullanıcı room data icin post request
    Then user gets the room data and assert