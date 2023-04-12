import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.lang.Long;
import java.sql.Timestamp;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer; 
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig; 
import org.apache.kafka.clients.producer.ProducerRecord; 
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.LongSerializer; 
import org.apache.kafka.common.serialization.StringSerializer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class AVaaSMessageProvider {

	static String brokerList = "localhost:9092";
	static int throughput = 10;
	static String typeMessage = "JSON";
	static String filterprefix = "";
	
	static Map<String, List<PartitionInfo> > topics;
	



	private static String RandomTopic() 
	{
		String AV = new String("");
		int index = (new Random()).nextInt(topics.size());		
		Set<String> keys = topics.keySet();
		Iterator<String> it = keys.iterator();
		for (int idx= 0 ; idx < index ;idx++) it.next();
		AV =  (String) it.next();
		System.out.println("AV randomized: " + AV);
		return AV;
	}


	
	private static Message CreateMessage(String m_typeMessage , String topicToSend , Timestamp ts)
	{
		Message newMessage;
		newMessage = new Message();
		newMessage.setTimeStamp(ts.toString());
		newMessage.setSeqkey(topicToSend + "_" + String.valueOf( ((Double) (Math.random() * 10)).intValue() )    );
		newMessage.setAV_ID(topicToSend);
		
		newMessage.setSpeed( 		String.valueOf( ((Double) (Math.random() * 120)).intValue() )    );
		newMessage.setBatteryLevel( String.valueOf( ((Double) (Math.random() * 100)).intValue() )    );
		newMessage.setDriverTirenessLevel( String.valueOf( ((Double) (Math.random() * 100)).intValue() )    );
		newMessage.setTractionWheelsLevel( String.valueOf( ((Double) (Math.random() * 100)).intValue() )   );
		
		String[] locationOptions = {"38.73704375907657, -9.138709213484344", "38.73730834347317, -9.302641438338373" , "38.81178965928624, -9.093170965989835",  "Unknown"};
		newMessage.setLocation(locationOptions[new Random().nextInt(locationOptions.length)]);
		
		String[] EnvironmentalLightningOptions = {"N/A", "Bad" , "Sufficient" , "Good" , "Very Good" , "Excelent"};
		newMessage.setEnvironmentalLightning( EnvironmentalLightningOptions[new Random().nextInt(EnvironmentalLightningOptions.length)] );
		String[] RainConditionsOptions = {"N/A", "Light Rain" , "Medium Rain" , "Heavy Rain"};
		newMessage.setRainConditions( RainConditionsOptions[new Random().nextInt(RainConditionsOptions.length)]  );
		String[] FogConditionsOptions = {"N/A", "None" , "Light Fog" , "Medium Fog" , "Dense Fog"};
		newMessage.setFogConditions( FogConditionsOptions[new Random().nextInt(FogConditionsOptions.length)] );
		
		String[] TrafficLightOptions = {"Red", "Yellow" , "Green"};
		newMessage.setTrafficLight( TrafficLightOptions[new Random().nextInt(TrafficLightOptions.length)] );

		newMessage.setObstacleProximity( String.valueOf( ((Double) (Math.random() * 100)).intValue() ) );

		newMessage.setPedestrianProximity( String.valueOf( ((Double) (Math.random() * 100)).intValue() ) );

		newMessage.setAverageConsumptionLevel( String.valueOf( (((Double) (Math.random() * 100)).intValue()  * 0.05 / 100)  + 0.15 )); //in kwh

			
		if (m_typeMessage.compareTo("JSON") == 0)
		{
			
			newMessage.setAsText(
				"{\"AV_Event\":{" +			
					"\"TimeStamp\":\""+ newMessage.getTimeStamp() + "\"," + 
					"\"AV_ID\":\"" + newMessage.getAV_ID() + "\"," + 
					"\"Speed\":\"" + newMessage.getSpeed() + "\"," + 
					"\"BatteryLevel\":\"" +  newMessage.getBatteryLevel() + "\"," + 
					"\"DriverTirenessLevel\":\"" + newMessage.getDriverTirenessLevel() + "\"," + 
					"\"Location\":\"" + newMessage.getLocation() + "\"," + 
					"\"EnvironmentalLightning\":\"" + newMessage.getEnvironmentalLightning() + "\"," +  
					"\"RainConditions\":\"" + newMessage.getRainConditions() + "\"," + 
					"\"FogConditions\":\"" + newMessage.getFogConditions() + "\"," + 
					"\"TractionWheelsLevel\":\"" + newMessage.getTractionWheelsLevel() +"\"," + 
					"\"TrafficLight\":\"" + newMessage.getTrafficLight() +"\"," + 
					"\"ObstacleProximity\":\"" + newMessage.getObstacleProximity() +"\"," + 
					"\"PedestrianProximity\":\"" + newMessage.getPedestrianProximity() +"\"," + 
					"\"AverageConsumptionLevel\":\"" + newMessage.getAverageConsumptionLevel() +"\"" +
				"}}"	
			);		
		}
		else if (m_typeMessage.compareTo("XML") == 0)
		{
			newMessage.setAsText(
				"<AV_Event>"+
				"<TimeStamp>"+ newMessage.getTimeStamp() + "</TimeStamp>" +
				"<AV_ID>" + newMessage.getAV_ID() + "</AV_ID>" + 
				"<Speed>" + newMessage.getSpeed() + "</Speed>" + 
				"<BatteryLevel>" +  newMessage.getBatteryLevel() + "</BatteryLevel>" + 
				"<DriverTirenessLevel>" + newMessage.getDriverTirenessLevel() + "</DriverTirenessLevel>" + 
				"<Location>" + newMessage.getLocation() + "</Location>" + 
				"<EnvironmentalLightning>" + newMessage.getEnvironmentalLightning() + "</EnvironmentalLightning>" +  
				"<RainConditions>" + newMessage.getRainConditions() + "</RainConditions>" + 
				"<FogConditions>" + newMessage.getFogConditions() + "</FogConditions>" + 
				"<TractionWheelsLevel>" + newMessage.getTractionWheelsLevel() + "</TractionWheelsLevel>" + 
				"<TrafficLight>" + newMessage.getTrafficLight() + "</TrafficLight>" + 
				"<ObstacleProximity>" + newMessage.getObstacleProximity() + "<ObstacleProximity>" + 
				"<PedestrianProximity>" + newMessage.getPedestrianProximity() + "<PedestrianProximity>" + 
				"<AverageConsumptionLevel>" + newMessage.getAverageConsumptionLevel() + "<AverageConsumptionLevel>" +
				"</AV_Event>"   );
		}
		else 
		{
			System.out.println("Type of message not identified.");
			return (null);
		}

		return (newMessage);
	}
	
	
	
	
	private static void CheckArguments()
	{
		System.out.println(  
							 "--broker-list=" + brokerList + "\n" +							 
							 "--throughput=" + throughput + "\n" +
							 "--typeMessage=" +  typeMessage + "\n" +
							 "--filterprefix=" + filterprefix);
	}
	
	
	private static boolean VerifyArgs(String[] cabecalho)
	{
		for (int i=0 ; i < cabecalho.length ; i=i+2)
		{
			if (cabecalho[i].compareTo("--broker-list") == 0) brokerList = cabecalho[i+1];
			else if (cabecalho[i].compareTo("--throughput") == 0) throughput = Integer.valueOf(cabecalho[i+1]).intValue();
			else if (cabecalho[i].compareTo("--typeMessage") == 0) typeMessage = cabecalho[i+1];
			else if (cabecalho[i].compareTo("--filterprefix") == 0) filterprefix = cabecalho[i+1]; 
			else 
			{
				System.out.println("Bad argument name: " + cabecalho[i]);
				return(false);
			}
		}		

		if (brokerList.length() == 0) System.out.println ("Broker-list argument is mandatory!");
		else return (true);
			
		return (false);
	}
	
	private static void SendMessage( Message msg ,  KafkaProducer<String, String> prd , String topicTarget)
	{		
		System.out.println("This is the message to send = " + msg.getAsText());
		String seqkey = new String("");
		seqkey = msg.getSeqkey();		
		System.out.println("Sending new message to Kafka, to the topic = " + topicTarget + ", with key=" + seqkey);	
		ProducerRecord<String, String> record = new ProducerRecord<>(topicTarget, seqkey, msg.getAsText());		
		prd.send(record);
		System.out.println("Sent...");
	}
	
	private static void CheckTopicsAvailable()
	{
		/*** check all topics in kafka cluster from JAVA  ******/				
		Properties props = new Properties();
		props.put("bootstrap.servers", brokerList);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		topics = consumer.listTopics();
		consumer.close();
		
		
		topics.remove("__consumer_offsets");
		System.out.println("Topics discovered: ");
		for( String topicName : topics.keySet() )	System.out.println("Topic = " + topicName);
		/******************************************************/

	}
	
	public static void main(String[] args) {


		String usage = "\nThe usage of the Message Producer for AVaaS 2023, for Enterprise Integration 2023 course, is the following.\n\n" 
				+ "AVaaSSimulator "
				+ "--broker-list <brokers> "
				+ "--throughput <value> "
				+ "--typeMessage <value> "
				+ "--filterprefix <value> "
				+ "\n"
				+ "where, \n"
				+ "--broker-list: is a broker list with ports (e.g.: kafka02.example.com:9092,kafka03.example.com:9092), default value is localhost:9092\n"
				+ "--throughput: is the approximate maximum messages to be produced by minute, default value is 10\n"
				+ "--typeMessage: is the type of message to be produced: JSON or XML, default value is JSON\n"
				+ "--filterprefix: is the prefix to be filtered. Only the topics starting with this prefix will be considered to sending messages.\n";
		
				
		Properties kafkaProps = new Properties();
		if (args.length == 0) System.out.println(usage);
		else 
		{
			if (VerifyArgs(args))
			{		
				System.out.println ("The following arguments are accepted:");
				CheckArguments();
				System.out.println ("------- Processing starting -------");
				
				kafkaProps.put("bootstrap.servers", brokerList); 
				kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer"); 
				kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer"); 
				KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);
			
				CheckTopicsAvailable();
				
				Timestamp mili;
				
				while (true)
				{
					try {
						mili = new Timestamp(System.currentTimeMillis());				
						
						if (!topics.isEmpty() )
						{
							String topic_to_send = RandomTopic();
							
							if (topic_to_send.startsWith(filterprefix))
							{							
								Message messageToSend =  CreateMessage(typeMessage , topic_to_send , mili );						
								if (messageToSend != null)	SendMessage( messageToSend , producer , topic_to_send );
							}
							else System.out.println("Topic = " + topic_to_send + " has been filtered. Therefore, not sending message.");
						}
						else System.out.println("Empty list of Topics. Therefore, no message to send.");
							
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						System.out.println("Waiting..." + timestamp );
						Thread.sleep(60000/throughput);  
						CheckTopicsAvailable();
					}
					catch (Exception e) { e.printStackTrace();}
					
					System.out.println("Fire-and-forget stopped.");
				}
			}
			else System.out.println("Application Arguments bad usage.\n\nPlease check syntax.\n\n" + usage);
		}
		
		
	}





}
