
## Fav Quotes Readme

- Written in Java
- Spring framework
- Scenarios defined using Cucumber
- RestAssured library used for REST calls
- Hamcrest used for assertions

After cloning project - run the below mvn install command to resolve dependencies
```bash
mvn clean install -DskipTests=true
```

To run Scenarios
Right-click in feature file at the following levels and Run
- Feature
- Scenario

Alternatively, run the Cucumber main class from the Cucumber runner
- update the tags attribute to select particular selection of tagged tests
- currently set to run all tests (@FavQuotes or @ListQuotes)

### Tests

### Fav Quote endpoint

- Fav a quote **(scenario written)**
- Unfav a quote **(scenario written)**
- Attempt to fav a non-existent quote **(scenario written)**
- Attempt to unfav a non-existent quote
- Fav an already fav'd quote
- Unfav an already unfav'd quote
- Attempt to fav a private quote
- Attempt to unfav a private quote
- Attempt to fav a quote with no User Session token
- Attempt to unfav a quote with no User Session token
- Attempt to fav a quote with invalid User Session token
- Attempt to unfav a quote with invalid User Session token
- Attempt to fav a quote with no access token
- Attempt to fav a quote with invalid access token
- Attempt to unfav a quote with no access token
- Attempt to unfav a quote with invalid access token

### List Quotes endpoint

- Successfully get a list of quotes **(scenario written)**
- Verify that 25 quotes are returned per page **(scenario written)**
- Verify that user can specify which page number to return **(scenario written)**
- Verify list of quotes can be filtered by type author **(scenario written)**
- Attempt to get a list of quotes filtering by non-existent author **(scenario written)**
- Verify list of quotes can be filtered by type tag **(scenario written)**
- Filter list of quotes by type tag when tag doesn't exist
- Verify list of quotes can be filtered by type user
- Filter list of quotes by type user when user doesn't exist
- Get list of quotes that contain a particular word
- Get private quotes of current user when user has pro session
- Attempt to get private quotes of current user when user does not have pro session
- Get list of private quotes that contain a word
- Get list of private quotes filtering by author
- Get list of private quotes filtering by tag
- Get list of private quotes filtering by user
- Get list of quotes hidden by the user
- Get list of hidden quotes filtering by author
- Get list of hidden quotes filtering by tag
- Get list of hidden quotes filtering by user
- Get list of hidden quotes that contain a particular word
- Attempt to get a list of quotes with no access token
- Attempt to get a list of quotes with invalid access token
- Get list of quotes with different case
- Get list of quotes with left/right padding around filters