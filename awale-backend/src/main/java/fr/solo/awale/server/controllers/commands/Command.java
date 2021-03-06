package fr.solo.awale.server.controllers.commands;

import fr.solo.awale.server.controllers.GameController;
import org.springframework.boot.json.JsonParser;
import fr.solo.awale.server.models.GamesModel;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;

public abstract class Command {
	protected GamesModel gameService;
	private JsonParser jsonParser;
	protected GameController controller;

	public Command(GameController controller) {
		this.gameService = GamesModel.getInstance();
		jsonParser = JsonParserFactory.getJsonParser();
		this.controller = controller;
	}

	public abstract boolean execute(String payload, WebSocketSession session);

	/**
	 * @param payload Le JSON à valider
	 * @param keys    Les clés du JSON à vérifier
	 * @return {@code true}/{@code false} si le JSON n'est pas valide
	 */
	protected boolean isJsonNotValid(Map<String, Object> json, String... keys) {
		return !json.keySet().containsAll(List.of(keys));
	}

	public Map<String, Object> getJsonMap(String payload) {
		return jsonParser.parseMap(payload);
	}
}
