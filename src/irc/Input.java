package irc;

import irc.adaptors.InputHandlerAdapter;
import irc.commands.Command;
import irc.events.IrcEvent;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;


public class Input extends InputHandlerAdapter {
	
	private IrcClient ircClient;
	
	public Input(IrcClient ircClient) {
		this.ircClient = ircClient;
	}
	
	@Override
	public void handleInvite(IrcEvent e) {
		ircClient.join(e.getSource().getTrailing());
	}
	
	@Override
	public void handleKick(IrcEvent e) {
		ircClient.join(e.getSource().getParams()[0]);
	}
	
	@Override
	public void handlePing(IrcEvent e) {
		ircClient.write("PONG :" + e.getSource().getTrailing());
	}
	
	@Override
	public void handlePrivMsg(IrcEvent e) {
		IrcMessage msg = e.getSource();
		if (msg.getTrailing().startsWith("@")) {
			String command = msg.getUserCommand().toLowerCase();
			PythonInterpreter interpreter = new PythonInterpreter();
			try {
				interpreter.exec("from scripts." + command + " import " + command);
			} catch (Exception ex) {
				return;
			}
			PyObject pyObject = interpreter.get(command);
			PyObject newObj = pyObject.__call__();
			Command commandClass = (Command) newObj.__tojava__(Command.class);
			commandClass.execute(ircClient, msg.getNick(), msg.getUserParam(), msg.getParams()[0]);
		}
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