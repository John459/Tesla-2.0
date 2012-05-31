package irc.adaptors;

import irc.IrcClient;
import irc.events.IrcEvent;
import irc.listeners.InputListener;


public abstract class InputHandlerAdapter implements InputListener {
	
	private IrcClient ircClient;
	
	public void setIrcClient(IrcClient ircClient) {
		this.ircClient = ircClient;
	}
	
	public IrcClient getIrcClient() {
		return ircClient;
	}
	
	@Override
	public void handleUserCommand(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleJoin(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePart(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMode(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleTopic(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleNames(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleList(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInvite(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKick(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePrivMsg(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleNotice(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePing(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}