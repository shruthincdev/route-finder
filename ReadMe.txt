Steps to run route-finder application

1. Import as maven project on eclipse
2. Right click on the application -> Maven -> update project
3. Modify the input file as per the test data
4. Right click on the application -> Run As -> Maven build... -> provide maven goals as clean install
5. Right click on the application -> Run As -> Java application -> Select main class as RouteFinderApplication

6. Run the curl commands
curl --location --request GET 'http://localhost:8080/connected?origin=A&destination=B'
