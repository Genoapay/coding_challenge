# Stock Max Profit Calculator

This project includes a stock service which has a max profit endpoint and a web application responding it.

## Local set up
1. Run the `build.sh` in the project root folder to build the stock service docker image.
2. Run the `run.sh` to run the docker instance and open the bundled static web page.
3. Add the trading time and price in the timeline then calculate 
the max profit information from the stock service.
## CI/CD integration
* CI building definition

   In the github workflow directory, there is a build.yml file which defined the CI steps 
when push or pull request to master. 

   The building steps include maven package and react app building. 
   If adding docker publishing and static resource distribution steps, it would be easily integrate with CD.


* CD definition

   A cloud formation template yaml file has also been introduced. With the template parameter replaced in the image 
and other sections, it will deploy the updated version to forgate.

