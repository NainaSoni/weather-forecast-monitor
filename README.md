# Weather Forecast Monitor App

Its a simple weather forecast app to monitor weather of location for next 5 days provided in json file along with temperature limits.
with free third party weather forecast app  `https://openweathermap.org/forecast5`  along with following parameter (q=location, units=metrics & appid ={your api id}) 
Note:You can generate your api id from `https://home.openweathermap.org/users/sign_in`

## Requirements

- `Java 8+`
- `Maven 3+`
- `Spring boot 2.1.5.RELEASE `

 **Other packages used**

`lombok`  

## Installation (Build and run the app using maven)

1. Clone the repository: `git clone git@github.com:gauravs08/weather-forecast-monitor`

2. Install the application: `lombok` for you IDE  ![Steps] (https://projectlombok.org/setup/overview)

3. Make sure `maven` & `jdk/jre 8+` is installed in your running machine

4. Right click on project and Run-> Maven install  Or Using cmd.

	`$projectlocation> mvn package`

5. RUN the app

	`$projectLocation> java -jar target/weather-forecast-monitor-0.0.1-SNAPSHOT.jar`	 

6. Alternatively, you can run the app without packaging it using -

	`mvn spring-boot:run`

7. To run with specific parameter make sure you have your input-location-file.json file at running location

	`java -Dservice.refresh-rate=10000 -Dservice.output-weather-report-file=Report-Output.json -Dservice.input-location-file=file:input-location-file.json -jar target/weather-forecast-monitor-0.0.1-SNAPSHOT.jar	 ` 

### REST API request
`http://localhost:3000/forecast/report` 


## input-location-file
```json
[{ "city":"Helsinki",
	"temp":{
			"min": 10,
			"max": 20
			}
  }]
  ```
  
## Demo App run including rest api call

![Running App](http://g.recordit.co/UtsvHN5EIh.gif)

## Test Units Execution
![Test Unit](http://g.recordit.co/XArrdRP1ra.gif)
