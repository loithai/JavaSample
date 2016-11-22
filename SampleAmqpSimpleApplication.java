/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.amqp;

import java.net.URISyntaxException;
import java.util.Date;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@RabbitListener(queues = "foo")
@EnableScheduling
public class SampleAmqpSimpleApplication {

	@Bean
	public Sender mySender() {
		return new Sender();
	}

	@Bean
	public Queue fooQueue() {
		return new Queue("foo");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("loi", false, true);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("loi.*");
	}

	@Bean(destroyMethod = "destroy")
	public ConnectionFactory rabbitConnectionFactory() throws URISyntaxException{
		CachingConnectionFactory factory = new CachingConnectionFactory("192.168.122.64");
		factory.setUsername("loi");
		factory.setPassword("Chris123");

		return factory;
	}

	@RabbitHandler
	public void process(@Payload String foo) {
		System.out.println(new Date() + ": " + foo);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleAmqpSimpleApplication.class, args);
	}

}
