# TradeSoft
To start the Application : 
1. Docker :
   1. mvn clean install
   2. docker-compose -f docker-compose.yml up

2. Jar file: 
   1. set "spring.data.mongodb.host=localhost" in application.properties
   2. mvn clean install
   3. click run or java -jar <path to project root>/target/exchanges-services-0.0.1-SNAPSHOT.jar

To stop the application: 
1. docker-compose -f docker-compose.yml down

3 API created: 
1. @POST /exchanges/{exchange-name}/order-books
   Input: {
   "orderType": "ASK", // BIDS
   "sorting": "ASCENDING", // DESCENDING
   "type": "BLOCKCHAIN"
   }

2. @POST /exchange/{exchange-name}/metadata
   Input : exchange-name in Path Variable along with file.csv (order : exchangeName, description, address)

3. @GET /exchange/{exchange-name}/metadata
Input : exchange-name in Path Variable
