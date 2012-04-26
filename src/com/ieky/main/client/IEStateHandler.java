package com.ieky.main.client;

import java.util.LinkedList;

import org.codingventures.api.client.StateHandler;

import com.google.gwt.user.client.History;

public class IEStateHandler extends StateHandler {
	
	public static final IEStateHandler stateHandler = new IEStateHandler();
	
	private final LinkedList<String> states = new LinkedList<String>();
	private String currentState;
	
	public IEStateHandler() {
		states.add("home"); // also null and ""
		states.add("business_services");
		states.add("home_services");
		states.add("about");
		states.add("contact");
		
		currentState = "home";
	}
	
	public void init() {
		History.fireCurrentHistoryState();
	}
	
	@Override
	public boolean compareTokens(String t1, String t2) {
		return t1.equals(t2);
	}

	@Override
	public String validateToken(String token) {
		if(states.contains(token)) {
			currentState = token;
			return token;
		}
		return currentState;
	}
	
	public void goHome() {
		currentState = "home";
		History.newItem(currentState);
	}
	
	public void goBusinessServices() {
		currentState = "business_services";
		History.newItem(currentState);
	}
	
	public void goHomeServices() {
		currentState = "home_services";
		History.newItem(currentState);
	}
	
	public void goAbout() {
		currentState = "about";
		History.newItem(currentState);
	}
	
	public void goContact() {
		currentState = "contact";
		History.newItem(currentState);
	}

	public String getCurrentState() {
		return currentState;
	}
	
}
