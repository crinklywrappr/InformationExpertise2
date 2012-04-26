package com.ieky.main.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {
	
	public static final Resources INSTANCE = GWT.create(Resources.class);
	
	@Source("pages/home.html")
	public TextResource home();
	
	@Source("pages/business_services.html")
	public TextResource businessService();
	
	@Source("pages/home_services.html")
	public TextResource homeService();
	
	@Source("pages/about.html")
	public TextResource about();
	
	@Source("pages/contact.html")
	public TextResource contact();

	@Source("images/slogan1.png")
	public ImageResource slogan1();
	
	@Source("images/slogan2.png")
	public ImageResource slogan2();
	
	@Source("images/slogan3.png")
	public ImageResource slogan3();
	
	@Source("images/slogan4.png")
	public ImageResource slogan4();
	
	@Source("images/slogan5.png")
	public ImageResource slogan5();

	@Source("images/slogan6.png")
	public ImageResource slogan6();
	
	@Source("images/slogan7.png")
	public ImageResource slogan7();
	
	@Source("images/card.png")
	public ImageResource card();
	
	@Source("Footer.html")
	public TextResource footer();
	
	@Source("images/footer.png")
	public ImageResource footerImages();
	
	@Source("images/ms_cert.png")
	public ImageResource homeSidebar();
	
}
