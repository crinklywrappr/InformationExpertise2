package com.ieky.main.client;

import org.codingventures.api.client.ClientHistoryHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class InformationExpertise2 implements EntryPoint {
	
	interface Binder extends UiBinder<DecoratorPanel, InformationExpertise2> {}
	private static final Binder binder = GWT.create(Binder.class);
	
	DecoratorPanel decorator;
	@UiField VerticalPanel appWrapper;
	
	@Override
	public void onModuleLoad() {
		// put a decoration around the webpage
		decorator = binder.createAndBindUi(this);
		
		// setup the address bar listener
		ClientHistoryHandler.setup(IEStateHandler.stateHandler);
		
		// generate the banner and add it to the wrapper
		Banner banner = new Banner();
		appWrapper.add(banner.getBanner());

		// generate the pages and the handler for them, add the pages 
		// to the wrapper
		Pages pages = new Pages();
		ClientHistoryHandler pagesHandler = new ClientHistoryHandler();
		pagesHandler.addClientWidget(pages);
		appWrapper.add(pages.getPages());
		
		// set up the footer & add it
		Image footerImages = new Image(Resources.INSTANCE.footerImages());
		HTMLPanel footerPanel = new HTMLPanel(Resources.INSTANCE.footer().getText());
		footerPanel.add(footerImages, "footer-images");
		appWrapper.add(footerPanel);
		
		// add the handlers
		History.addValueChangeHandler(pagesHandler);
		
		// add the wrappers to the application div in our rootpanel
		RootPanel.get("application").add(decorator);
		
		// set up the slogan animation
		banner.setupSloganAnimation();
		
		// start the application
		IEStateHandler.stateHandler.init();
	}
}
