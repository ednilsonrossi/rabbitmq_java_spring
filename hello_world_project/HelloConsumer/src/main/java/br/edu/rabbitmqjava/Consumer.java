package br.edu.rabbitmqjava;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Consumer {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 5672;
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException{
		var factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		
		var connection = factory.newConnection();
		var channel = connection.createChannel();

		DeliverCallback deliverCallback = new DeliverCallback() {
			@Override
			public void handle(String consumerTag, Delivery message) throws IOException {
				String receivedMessage = new String(message.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + receivedMessage + "'");
			}
		};
		
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
	}
}
