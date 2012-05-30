package irc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public abstract class IrcProtocol {
	
	private BufferedReader in;
	private PrintWriter out;
	private Input inputEventListener;
	
	public void privMsg(String output, String loc) {
		write("PRIVMSG " + loc + " :" + output);
	}
	
	public void notice(String output, String loc) {
		write("NOTICE " + loc + " :" + output);
	}
	
	public void join(String loc) {
		write("JOIN " + loc);
	}
	
	public void write(String output) {
		out.write(output + "\r\n");
		out.flush();
	}
	
	private void connect(String host, int ip) throws IOException {
		Socket s = new Socket(host, ip);
		in =  new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
	}
	
	public IrcProtocol(String host, int ip, String nick, String user, String pass) throws IOException {
		if (host == null || ip <= 0 || nick == null || user == null) {
			return;
		}
		connect(host, ip);
		write("NICK "+ nick);
		write("USER " + user + " * * :" + nick);
		if (pass != null)
			write("PRVMSG NICKSERV IDENTIFY " + pass);
	}
	
	public Input getInputEventListener() {
		return inputEventListener;
	}
	
	public void setInputEventListener(Input input) {
		this.inputEventListener = input;
	}
	
	public BufferedReader getIn() {
		return in;
	}
	
}