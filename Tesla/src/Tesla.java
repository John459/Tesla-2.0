import irc.IrcClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class Tesla {
	
	private static final String CONFIG = "config";
	
	public static void main(String[] args) {
		List<String> data = new ArrayList<String>();
		File file = new File(CONFIG);
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Config file does not exist");
			return;
		}
		String str;
		try {
			while ((str = in.readLine()) != null) {
				data.add(str);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to process config file");
			return;
		}
		int port;
		try {
			port = Integer.parseInt(data.get(1));
		} catch (NumberFormatException e) {
			System.out.println("Port must be an integer");
			return;
		}
		IrcClient tesla;
		try {
			tesla = new IrcClient(data.get(0), port, data.get(2), data.get(3), null);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to create the irc client");
			return;
		}
		if (data.size() < 5) return;
		for (int i = 4; i < data.size(); i++) {
			tesla.join("#" + data.get(i));
		}
	}
	
}