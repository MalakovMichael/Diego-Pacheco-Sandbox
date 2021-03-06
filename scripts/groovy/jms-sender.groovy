@Grab(group='javax.jms', module='jms', version='1.1')
import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.DeliveryMode
import javax.jms.Destination
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage

@Grab(group='org.apache.activemq', module='activemq-core', version='5.4.0')
import org.apache.activemq.ActiveMQConnectionFactory

int  TIMES   = 1
int  TTL     = 0
String QUEUE = "pacheco.test.queue"
String BURL  = "tcp://127.0.0.1:61616"
String MSG   = """
{
  "born" : 1316446371817,
  "male" : true,
  "tweets" : 4560,
  "marriedWith" : {
    "born" : 1316446371815,
    "male" : false,
    "tweets" : 400,
    "name" : "Marry"
  },
  "name" : "Paul"
}

"""		

def send(String destinationName,String message,int times,int ttl,String brokerUrl){

    ConnectionFactory cf  = new ActiveMQConnectionFactory(brokerUrl)    
    Connection connection = cf.createConnection()
    connection.start()

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)    
    Destination destination = session.createQueue(destinationName)
    
    times.times {
        MessageProducer producer = session.createProducer(null)            
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT)
        
        TextMessage txtMessage = session.createTextMessage()
        txtMessage.setText( message )
       
        producer.setTimeToLive(ttl)
        producer.send(destination,txtMessage)
    }                        

    session.close()
    connection.close()                        
}

send(QUEUE,MSG,TIMES,TTL,BURL)
println "Message Sent at ${java.text.DateFormat.getInstance().format(new java.util.Date())}"