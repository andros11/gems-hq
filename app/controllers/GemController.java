package controllers;

import models.Gem;
import models.GemTree;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GemController extends Controller {

	public static Result getGem(Long id) {
		// DEBUG
		System.err.println("GET on id: "+ id);

		ObjectNode result = Json.newObject();
		GemTree theTree = GemTree.getInstance();
		Gem G = theTree.getGemById(id);
		if (G == null) {
			return notFound("Gem Not Found"); // 404
		}
		else {
			result.put("Gem", Json.toJson(G));
			return ok(result);
		}
	}

	public static Result getGems() {
		// DEBUG
		System.err.println("GET gems");

		ObjectNode result = Json.newObject();
		Gem[] theGems = GemTree.getInstance().getAllGems();
		if (theGems == null) {
			return notFound("Gem Not Found"); // 404
		}
		else {
			result.put("Gems", Json.toJson(theGems));
			return ok(result);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result storeGem() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode json = request().body().asJson();

			Gem newGem = mapper.readValue(json.toString(), Gem.class);
			GemTree theTree = GemTree.getInstance();
			newGem = theTree.addGem(newGem);
			ObjectNode result = Json.newObject();
			result.put("Gem", Json.toJson(newGem));
			return created(result);
		}
		catch(Exception e) {
			e.printStackTrace();
			return badRequest("Missing information");
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateGem(Long id) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode json = request().body().asJson();
			Gem updGem = mapper.readValue(json.toString(), Gem.class);
			GemTree theTree = GemTree.getInstance();
			updGem = theTree.updateGem(updGem);
			if (updGem == null) {
				return notFound("Gem Not Found"); // 404 
			}
			else {
				ObjectNode result = Json.newObject();
				result.put("Gem", Json.toJson(updGem));
				return ok(result);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return badRequest("Missing information");
		}
	}

	public static Result deleteGem(Long id) {
		GemTree theTree = GemTree.getInstance();
		boolean erased = theTree.deleteGem(id);
		if (erased) {
			// This is code 204 - OK with no content to return
			return noContent();
		}
		else {
			return notFound("Gem Not Found");
		}
	}

}
