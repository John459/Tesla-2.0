import java.io.IOException;

import irc.IrcClient;



public class Tesla {
	
	private static final String NICK = "TeslaV2";
	
	private static final String USER = "Tesla";
	
	public static void main(String[] args) {
		int port = -1;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.out.println("port must be a number");
			return;
		}
		if (args.length < 2) return;
		IrcClient tesla;
		try {
			tesla = new IrcClient(args[0], port, NICK, USER, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		if (args.length >= 3 && args[2].contains(",")) {
			String[] channels = args[2].split(",");
			for (String channel : channels) {
				tesla.join("#" + channel);
			}
		}
	}
	
}