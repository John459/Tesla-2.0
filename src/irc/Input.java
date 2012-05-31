package irc;

import irc.adaptors.InputHandlerAdapter;
import irc.events.IrcEvent;


public class Input extends InputHandlerAdapter {
	
	public Input(IrcClient ircClient) {
		this.setIrcClient(ircClient);
	}
	
	@Override
	public void handleInvite(IrcEvent e) {
		this.getIrcClient().join(e.getSource().getTrailing());
	}
	
	@Override
	public void handleKick(IrcEvent e) {
		this.getIrcClient().join(e.getSource().getParams()[0]);
	}
	
	@Override
	public void handlePing(IrcEvent e) {
		this.getIrcClient().write("PONG :" + e.getSource().getTrailing());
	}
	
	@Override
	public void handlePrivMsg(IrcEvent e) {
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
	public void handleNotice(IrcEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}