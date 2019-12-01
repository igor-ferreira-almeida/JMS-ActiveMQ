package br.com.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProducerTopicTest {
	
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection("user", "senha");

		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination topico = (Destination) context.lookup("loja");

		MessageProducer messageProducer = session.createProducer(topico);
		
		Message message = session.createTextMessage("<pedido><id>125</id></pedido>");
		//message.setBooleanProperty("ebook", true);
		messageProducer.send(message);
		
		connection.close();
		context.close();
		
		System.out.println("Enviando mensagens para o tópico...");
	}

}
