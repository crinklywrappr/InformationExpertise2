package com.ieky.main.client;

import java.util.HashMap;
import java.util.LinkedList;

import org.codingventures.api.client.ClientWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.StackPanel;
import com.ieky.main.client.ServicesPanel.ServicesPanelHistoryIssuer;
import com.ieky.main.client.ServicesPanel.StackItem;

public class Pages extends ClientWidget implements ServicesPanelHistoryIssuer {
	interface Binder extends UiBinder<DeckPanel, Pages> {}
	private static final Binder binder = GWT.create(Binder.class);
	
	// this is used to make sure that we load the first time
	private boolean init = true;
	
	private DeckPanel pages;
	private ServicesPanel services;
	
	private final HashMap<String, Integer> nameToIndex = 
		new HashMap<String, Integer>();
	
	public Pages() {
		pages = binder.createAndBindUi(this);
		
		// Remember that both services appear on the same panel
		nameToIndex.put("home", 0);
		nameToIndex.put("business_services", 1);
		nameToIndex.put("home_services", 1);
		nameToIndex.put("about", 2);
		nameToIndex.put("contact", 3);
		
		// set up the services page
		LinkedList<StackItem> items = new LinkedList<StackItem>();
		items.add(new StackItem("Home Services", Resources.INSTANCE.homeService()));
		items.add(new StackItem("Business Services", Resources.INSTANCE.businessService()));
		services = new ServicesPanel(items, this);
		
		// set up the home page
		Image side = new Image(Resources.INSTANCE.homeSidebar());
		HTMLPanel homePanel = new HTMLPanel(Resources.INSTANCE.home().getText());
		homePanel.add(side, "ms_cert");
		
		// set up the contact page
		Image card = new Image(Resources.INSTANCE.card());
		HTMLPanel contactPanel = new HTMLPanel(Resources.INSTANCE.contact().getText());
		contactPanel.add(card, "card");
		
		// wrap the other pages in StackPanels to gain a universal L&F
		StackPanel homeWrapper = new StackPanel();
		StackPanel aboutWrapper = new StackPanel();
		StackPanel contactWrapper = new StackPanel();
		homeWrapper.add(homePanel, "Home", true);
		aboutWrapper.add(new HTML(Resources.INSTANCE.about().getText()), "About", true);
		contactWrapper.add(contactPanel, "Contact", true);
		
		// set up the pages
		pages.add(homeWrapper);
		pages.add(services);
		pages.add(aboutWrapper);
		pages.add(contactWrapper);
		pages.setAnimationEnabled(true);
	}
	
	public DeckPanel getPages() {
		return pages;
	}
	
	public void handleServicesPanel(String token) {
		if(token.equals("business_services"))
			services.showStack(1);
		else if(token.equals("home_services"))
			services.showStack(0);
	}
	
	@Override
	public void issueNewToken(int index) {
		if(index==0)
			IEStateHandler.stateHandler.goHomeServices();
		else if(index==1)
			IEStateHandler.stateHandler.goBusinessServices();
	}

	@Override
	public void addWidgetToRootPanel() {
		/* do nothing */
	}

	@Override
	public boolean isTokenRelated(String token) {
		int newIndex = nameToIndex.get(token);
		handleServicesPanel(token);
		if(init || newIndex!=pages.getVisibleWidget()) {
			pages.showWidget(newIndex);
			init = false;
		}
		return false;
	}
}
