## Latitude BNPL Coding Challenge
### To create build
Having maven setup, cd /coding_challange and 
run ```mvn clean install``` to generate api classes, mapstruct impl and dependency
### To unit test application
Having maven setup, cd /coding_challange and
run ```mvn test```
### To run application
Go to your maven local repo and browse to below folder
cd ~/.m2/repository/com/latitude/genoapay/coding-challenge/0.0.1-SNAPSHOT
run ```java -jar -Dserver.port=<server port> coding-challenge-0.0.1-SNAPSHOT.jar```
### Accessing rest endpoints to calculate max profit
Endpoint url is:
http://localhost:<server port>/stock/getmaxprofit
Access using curl client-
```
curl --location --request POST 'http://localhost:8083/stock/getmaxprofit' --header 'Content-Type: application/json' --data-raw '{
    "name": "test stock",
    "startDate": "2022-01-15T23:37:06.359Z",
    "endDate": "2022-01-15T23:37:06.359Z",
    "stockPrices": [
        4, 9, 2, 5, 1, 6
    ]
}'
```




