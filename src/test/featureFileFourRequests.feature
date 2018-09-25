Feature: Testing the 4 HTTP Requests

Scenario: Testing a Post Request
Given  I want to post a hotel that exists
When I include its name, description, city and rating 
Then It's posted to the website

Scenario: Testing a Get Request
Given I want to post a hotel that exists
When I get the id 
Then The location is equal to London

Scenario: Testing a Put Request
Given A hotel in Cardiff called JOHN exists 
When I change the rating to 4
Then The rating is now equal to 4

Scenario: Testing a Delete Request
Given A Hotel exists in Paris  
When I retrieve the hotel
Then the status code is not equal to 200