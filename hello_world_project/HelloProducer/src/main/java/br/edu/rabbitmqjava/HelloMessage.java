package br.edu.rabbitmqjava;

import java.util.List;
import java.util.Random;

public class HelloMessage {

	private static Long ID_NUMBER = 0L;
	
	private final List<String> messages = List.of(
			"Olá, Mundo!", 
			"Hello, World!", 
			"¡Holla, Mundo!", 
			"Hallo, Welt!", 
			"Bonjour, le Monde!");
	
	public String getMessage() {
		var rand = new Random();
		ID_NUMBER += 1;
		int message_position = rand.nextInt(messages.size());
		return String.format("Hello Message ID: %d - Message : %s", ID_NUMBER, messages.get(message_position));
	}
	
}
