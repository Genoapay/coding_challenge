## Latitude BNPL Coding Challenge
### To create build
Having maven setup, cd /coding_challange and 
run ```mvn clean install``` to generate api classes, mapstruct impl and dependency
### To unit test application
Having maven setup, cd /coding_challange and
run ```mvn test```
### To run application using jar
Go to your maven local repo and browse to below folder
cd ~/.m2/repository/com/latitude/genoapay/coding-challenge/0.0.1-SNAPSHOT
run ```java -jar -Dserver.port=<server port> coding-challenge-0.0.1-SNAPSHOT.jar```
### To run application using docker container
Having docker setup, cd /coding_challange and
Run ```mvn jib:dockerBuild```
After image is build in local docker daemon
Run ``` docker run -p8086:8086 coding-challenge:1.0.0```
### Accessing rest endpoints to calculate max profit
Endpoint url is:
http://localhost:{server port}/stock/getmaxprofit
Access using curl client-
```
curl --location --request POST 'http://localhost:8086/stock/getmaxprofit' --header 'Content-Type: application/json' --data-raw '{
    "name": "test stock",
    "startDate": "2022-01-15T23:37:06.359Z",
    "endDate": "2022-01-15T23:37:06.359Z",
    "stockPrices": [
        4, 9, 2, 5, 1, 6
    ]
}'
```




