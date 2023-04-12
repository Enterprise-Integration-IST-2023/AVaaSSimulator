
public class Message {

	private String timeStamp;
	private String seqkey;
	private String AV_ID;
	private String Speed;
	private String BatteryLevel;
	private String DriverTirenessLevel;
	private String Location;
	private String EnvironmentalLightning;
	private String RainConditions;
	private String FogConditions;
	private String TractionWheelsLevel;
	private String TrafficLight;
	private String ObstacleProximity;
	private String PedestrianProximity;
	private String AverageConsumptionLevel;


	
	public String getAsText() {
		return AsText;
	}
	public void setAsText(String asText) {
		AsText = asText;
	}
	private String AsText;

	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getSeqkey() {
		return seqkey;
	}
	public void setSeqkey(String seqkey) {
		this.seqkey = seqkey;
	}
	public String getAV_ID() {
		return AV_ID;
	}
	public void setAV_ID(String aV_ID) {
		AV_ID = aV_ID;
	}
	public String getSpeed() {
		return Speed;
	}
	public void setSpeed(String speed) {
		Speed = speed;
	}
	public String getBatteryLevel() {
		return BatteryLevel;
	}
	public void setBatteryLevel(String batteryLevel) {
		BatteryLevel = batteryLevel;
	}
	public String getDriverTirenessLevel() {
		return DriverTirenessLevel;
	}
	public void setDriverTirenessLevel(String driverTirenessLevel) {
		DriverTirenessLevel = driverTirenessLevel;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getEnvironmentalLightning() {
		return EnvironmentalLightning;
	}
	public void setEnvironmentalLightning(String environmentalLightning) {
		EnvironmentalLightning = environmentalLightning;
	}
	public String getRainConditions() {
		return RainConditions;
	}
	public void setRainConditions(String rainConditions) {
		RainConditions = rainConditions;
	}
	public String getFogConditions() {
		return FogConditions;
	}
	public void setFogConditions(String fogConditions) {
		FogConditions = fogConditions;
	}
	public String getTractionWheelsLevel() {
		return TractionWheelsLevel;
	}
	public void setTractionWheelsLevel(String tractionWheelsLevel) {
		TractionWheelsLevel = tractionWheelsLevel;
	}	
	public String getTrafficLight() {
		return TrafficLight;
	}
	public void setTrafficLight(String trafficLight) {
		TrafficLight = trafficLight;
	}
	public String getObstacleProximity() {
		return ObstacleProximity;
	}
	public void setObstacleProximity(String obstacleProximity) {
		ObstacleProximity = obstacleProximity;
	}
	public String getPedestrianProximity() {
		return PedestrianProximity;
	}
	public void setPedestrianProximity(String pedestrianProximity) {
		PedestrianProximity = pedestrianProximity;
	}
	public String getAverageConsumptionLevel() {
		return AverageConsumptionLevel;
	}
	public void setAverageConsumptionLevel(String averageConsumptionLevel) {
		AverageConsumptionLevel = averageConsumptionLevel;
	}

	
	public Message() {
		super();
	}



	
	public Message(String timeStamp, String seqkey, String aV_ID, String speed, String batteryLevel,
			String driverTirenessLevel, String location, String environmentalLightning, String rainConditions,
			String fogConditions, String tractionWheelsLevel, String trafficLight, String obstacleProximity,
			String pedestrianProximity, String averageConsumptionLevel) {
		this.timeStamp = timeStamp;
		this.seqkey = seqkey;
		AV_ID = aV_ID;
		Speed = speed;
		BatteryLevel = batteryLevel;
		DriverTirenessLevel = driverTirenessLevel;
		Location = location;
		EnvironmentalLightning = environmentalLightning;
		RainConditions = rainConditions;
		FogConditions = fogConditions;
		TractionWheelsLevel = tractionWheelsLevel;
		TrafficLight = trafficLight;
		ObstacleProximity = obstacleProximity;
		PedestrianProximity = pedestrianProximity;
		AverageConsumptionLevel = averageConsumptionLevel;
	}
	@Override
	public String toString() {
		return "Message [timeStamp=" + timeStamp + ", seqkey=" + seqkey + ", AV_ID=" + AV_ID + ", Speed=" + Speed
				+ ", BatteryLevel=" + BatteryLevel + ", DriverTirenessLevel=" + DriverTirenessLevel + ", Location="
				+ Location + ", EnvironmentalLightning=" + EnvironmentalLightning + ", RainConditions=" + RainConditions
				+ ", FogConditions=" + FogConditions + ", TractionWheelsLevel=" + TractionWheelsLevel
				+ ", TrafficLight=" + TrafficLight + ", ObstacleProximity=" + ObstacleProximity
				+ ", PedestrianProximity=" + PedestrianProximity + ", AverageConsumptionLevel="
				+ AverageConsumptionLevel + ", AsText=" + AsText + "]";
	}


	
		
}
