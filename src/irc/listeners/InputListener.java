package irc.listeners;

import irc.events.IrcEvent;


public interface InputListener extends Listener {
	
	public void handleJoin(IrcEvent e);
	
	public void handlePart(IrcEvent e);
	
	public void handleMode(IrcEvent e);
	
	public void handleTopic(IrcEvent e);
	
	public void handleNames(IrcEvent e);
	
	public void handleList(IrcEvent e);
	
	public void handleInvite(IrcEvent e);
	
	public void handleKick(IrcEvent e);
	
	public void handlePrivMsg(IrcEvent e);
	
	public void handleNotice(IrcEvent e);
	
	public void handlePing(IrcEvent e);
	
}