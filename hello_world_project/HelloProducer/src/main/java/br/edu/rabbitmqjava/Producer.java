package br.edu.rabbitmqjava;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 5672;
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException{
		var helloMessage = new HelloMessage();
		
		var factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		
		try (	var connection = factory.newConnection();
				var channel = connection.createChannel()) {
			
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			
			while (true) {
				String message = helloMessage.getMessage();
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");
				Thread.sleep(1000);
			}
		}
	}
}
