package irc.commands;

import irc.IrcClient;


public interface Command {
	
	public void execute(IrcClient client, String nick, String cmdInput, String loc);
	
}