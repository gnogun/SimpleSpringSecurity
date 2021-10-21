package com.spring.jwt.sample;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;

import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SimpleSpringSecurityApplicationTests {

	private String URL;
	private static final String SEND_CREATE_BOARD_ENDPOINT = "/app/create/";
	private static final String SEND_MOVE_ENDPOINT = "/app/move/";
	private static final String SUBSCRIBE_CREATE_BOARD_ENDPOINT = "/topic/board/";
	private static final String SUBSCRIBE_MOVE_ENDPOINT = "/topic/move/";

//	private CompletableFuture<GameState> completableFuture;

	@Before
	public void setup() {
//		completableFuture = new CompletableFuture<>();
		URL = "ws://localhost:" + 8083 + "/game";
	}


	@Test
	public void testCreateGameEndpoint() throws URISyntaxException, InterruptedException, ExecutionException, TimeoutException {
//		String uuid = UUID.randomUUID().toString();
//
//		WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
//		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//		StompSession stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {
//		}).get(1, SECONDS);
//
//		stompSession.subscribe(SUBSCRIBE_CREATE_BOARD_ENDPOINT + uuid, new CreateGameStompFrameHandler());
//		stompSession.send(SEND_CREATE_BOARD_ENDPOINT + uuid, null);
//
//		GameState gameState = completableFuture.get(10, SECONDS);
//
//		assertNotNull(gameState);
	}
//	private List<Transport> createTransportClient() {
////		List<Transport> transports = new ArrayList<>(1);
////		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
////		return transports;
//	}
}
