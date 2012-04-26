package com.ieky.main.client;

import java.util.ArrayList;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Banner {
	
	interface Binder extends UiBinder<HTMLPanel, Banner> {}
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField ImageResource about;
	@UiField ImageResource animatedSloganArea;
	@UiField ImageResource bottom;
	@UiField ImageResource contact;
	@UiField ImageResource floatingBarArea;
	@UiField ImageResource home;
	@UiField ImageResource left;
	@UiField ImageResource logo;
	@UiField ImageResource menuBottom;
	@UiField ImageResource menuTop;
	@UiField ImageResource right;
	@UiField ImageResource services;
	
	private HTMLPanel bannerWrapper;
	
	private final AbsolutePanel animPanel = new AbsolutePanel();
	private final ArrayList<Image> images = new ArrayList<Image>();
	private final SloganAnimation anim = new SloganAnimation();
	
	private final double polarOrigin = 1314;
	private double radsBetween;
	
	public Banner() {
		bannerWrapper = binder.createAndBindUi(this);
	}
	
	public HTMLPanel getBanner() {
		return bannerWrapper;
	}
	
	public void setupSloganAnimation() {
		images.add(new Image(Resources.INSTANCE.slogan1()));
		images.add(new Image(Resources.INSTANCE.slogan2()));
		images.add(new Image(Resources.INSTANCE.slogan3()));
		images.add(new Image(Resources.INSTANCE.slogan4()));
		images.add(new Image(Resources.INSTANCE.slogan5()));
		images.add(new Image(Resources.INSTANCE.slogan6()));
		images.add(new Image(Resources.INSTANCE.slogan7()));
		
		animPanel.setSize(((Integer)images.get(0).getWidth()).toString(), 
				((Integer)images.get(0).getHeight()).toString());
		
		radsBetween = (2 * Math.PI) / images.size();
		for(int i=0;i<images.size();i++) {
			double theta = i * radsBetween;
			int x = (int) (polarOrigin * Math.cos(theta) - polarOrigin);
			int y = (int) (polarOrigin * Math.sin(theta));
			animPanel.add(images.get(i), x, y);
		}
		
		Timer t = new Timer() {
			@Override
			public void run() {
				anim.run(1000);
			}
		};
		
		t.scheduleRepeating(5000);
		
		RootPanel.get("slogans_area").add(animPanel);
	}
	
	protected class SloganAnimation extends Animation {
		@Override
		protected void onUpdate(double progress) {
			double offset = radsBetween * progress;
			for(int i=0;i<images.size();i++) {
				double theta = (i * radsBetween) + offset;
				int x = (int) (polarOrigin * Math.cos(theta) - polarOrigin);
				int y = (int) (polarOrigin * Math.sin(theta));
				animPanel.setWidgetPosition(images.get(i), x, y);
			}
		}
		
		@Override
		protected void onComplete() {
			Image tmp = images.get(images.size()-1);
			for(int i=images.size()-1;i>0;i--)
				images.set(i, images.get(i-1));
			images.set(0, tmp);
		}
	}

}
