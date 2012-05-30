package irc;

import irc.events.IrcEvent;

import java.io.IOException;


public class IrcClient extends IrcProtocol implements Runnable {
	
	private Thread thread;
	
	public void handleInput(String input) {
		if (this.getInputEventListener() == null) return;
		IrcEvent ircEvent = new IrcEvent(new IrcMessage(input));
		String command = ircEvent.getSource().getCommand();
		if (command.equalsIgnoreCase("privmsg")) {
			this.getInputEventListener().handlePrivMsg(ircEvent);
		} else if (command.equalsIgnoreCase("ping")) {
			this.getInputEventListener().handlePing(ircEvent);
		} else if (command.equalsIgnoreCase("invite")) {
			this.getInputEventListener().handleInvite(ircEvent);
		} else if (command.equalsIgnoreCase("kick")) {
			this.getInputEventListener().handleKick(ircEvent);
		}
	}
	
	@Override
	public void run() {
		String str;
		try {
			while ((str = this.getIn().readLine()) != null) {
				System.out.println(str);
				handleInput(str);
				Thread.sleep(20);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IrcClient(String host, int ip, String nick, String user, String pass) throws IOException {
		super(host, ip, nick, user, pass);
		this.setInputEventListener(new Input(this));
		thread = new Thread(this);
		thread.start();
	}
	
}