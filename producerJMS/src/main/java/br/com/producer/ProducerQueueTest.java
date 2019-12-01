package br.com.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProducerQueueTest {

	private final static int TOTAL_MESSAGE_SEND = 100; 
	
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection("user", "senha");

		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("financeiro");

		MessageProducer messageProducer = session.createProducer(fila);
		
		
		
		for (int i = 0; i < TOTAL_MESSAGE_SEND; i++) {
			TextMessage textMessage = session.createTextMessage("<pedido><id>" + i + "</id></pedido>");
			messageProducer.send(textMessage);
		}
		
		
		//new Scanner(System.in).nextLine();
		
		connection.close();
		context.close();
		
		System.out.println("Enviando " + TOTAL_MESSAGE_SEND + " mensagens para a fila...");
	}

}
