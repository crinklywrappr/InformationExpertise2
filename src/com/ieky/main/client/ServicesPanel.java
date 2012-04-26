package com.ieky.main.client;

import java.util.LinkedList;

import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.StackPanel;

public class ServicesPanel extends StackPanel {
	
	public static class StackItem {
		private String header;
		private HTML content;
		
		public StackItem(String header, TextResource content) {
			this.header = header;
			this.content = new HTML(content.getText());
		}
		
		public String getHeader() {
			return header;
		}
		
		public HTML getContent() {
			return content;
		}
	}
	
	public interface ServicesPanelHistoryIssuer {
		public void issueNewToken(int index);
	}
	
	private ServicesPanelHistoryIssuer historyIssuer;
	
	public ServicesPanel(LinkedList<StackItem> items, ServicesPanelHistoryIssuer historyIssuer) {
		super();
		for(StackItem item : items)
			this.add(item.getContent(), item.getHeader(), true);
		this.historyIssuer = historyIssuer;
	}
	
	@Override
	public void onBrowserEvent(Event event) {
		if(DOM.eventGetType(event)==Event.ONCLICK) {
			Element target = DOM.eventGetTarget(event);
			int index = myFindDividerIndex(target);
			if(index != -1) {
				historyIssuer.issueNewToken(index);
			}
		}
		super.onBrowserEvent(event);
	}
	
	private int myFindDividerIndex(Element elem) {
		while (elem != getElement()) {
			String expando = DOM.getElementProperty(elem, "__index");
			if (expando != null) {
				// Make sure it belongs to me!
				int ownerHash = DOM.getElementPropertyInt(elem, "__owner");
				if (ownerHash == hashCode()) {
					// Yes, it's mine.
					return Integer.parseInt(expando);
				} else {
					// It must belong to some nested StackPanel.
					return -1;
				}
			}
			elem = DOM.getParent(elem);
		}
		return -1;
	}


}
