package irc.events;

import irc.IrcMessage;



public class IrcEvent extends Event<IrcMessage> {

	public IrcEvent(IrcMessage source) {
		super(source);
	}
	
}