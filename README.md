# AVaaSSimulator
IE 2022/2023 tool to simulate the Cars Activity for AVaaS, using Kafka Producer 

The tool starts by discovering all the topic available in the kafka cluster and then randomize messages for all that discovered topics.
You are free to customize the tool accordingly with your needs. If so, create a pull request to change this repository.

Verify if JAVA 17 is available using the command: 

```
java -version
```

Then, to execute the generator of messages use the following command from the target directory:
```
java -jar AVaaSSimulator.jar 
```
```
The usage of the Message Producer for AVaaS 2023, for Enterprise Integration 2023 course, is the following.

AVaaSSimulator --broker-list <brokers> --throughput <value> --typeMessage <value> --filterprefix <value> 
where, 
--broker-list: is a broker list with ports (e.g.: kafka02.example.com:9092,kafka03.example.com:9092), default value is localhost:9092
--throughput: is the approximate maximum messages to be produced by minute, default value is 10
--typeMessage: is the type of message to be produced: JSON or XML, default value is JSON
--filterprefix: is the prefix to be filtered. Only the topics starting with this prefix will be considered to sending messages.
```

One example of an AV_Event message sent, in JSON, to the dicovered topic in Kafka is:
```
{"AV_Event":
	{
		"TimeStamp":"2023-04-12 17:48:34.665",
		"AV_ID":"Toyota-with-driver259876",
		"Speed":"45",
		"BatteryLevel":"39",
		"DriverTirenessLevel":"4",
		"Location":"38.73730834347317, -9.302641438338373",
		"EnvironmentalLightning":"Excelent",
		"RainConditions":"Light Rain",
		"FogConditions":"Dense Fog",
		"TractionWheelsLevel":"60",
		"TrafficLight":"Red",
		"ObstacleProximity":"68",
		"PedestrianProximity":"44",
		"AverageConsumptionLevel":"0.1565"
	}
}
```