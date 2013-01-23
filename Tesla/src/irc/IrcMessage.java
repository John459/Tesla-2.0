package irc;

/**
 * IrcMessage.java
 * @author John Zavidniak
 * Take in raw input from the irc server in the form of ":prefix command params :trailing",
 * and organize it
 */

public class IrcMessage {
	
    //identifier that appear at the beginning of a private message sent from a user to indicate a user command
	private static final String IDENTIFIER = "@";
	
	//the prefix portion of the input sent from the irc server
	private String prefix;
	
	//the command sent from the irc server
	private String command;
	
	//the parameters of the command sent from the irc server
	private String[] params;
	
	//the trailing portion of the input sent from the irc server
	private String trailing;
	
	/**
	 * Create the Object and parse the input
	 * @param input the raw input from the irc server
	 */
	public IrcMessage(String input) {
		parseInput(input);
	}
	
	/**
	 * Parse and store each individual component of the input sent from the irc server
	 * @param input the raw input from the irc server
	 */
	private void parseInput(String input) {
		int prefixEnd = -1, trailingStart;
		if (input.startsWith(":")) {
			prefixEnd = input.indexOf(" ");
			prefix = input.substring(1, prefixEnd);
		}
		trailingStart = input.indexOf(" :");
		if (trailingStart >= 0) {
			trailing = input.substring(trailingStart+2);
		} else {
			trailingStart = input.length();
		}
		String[] cmdAndParams = input.substring(prefixEnd+1, trailingStart).split(" ");
		command = cmdAndParams[0];
		if (cmdAndParams.length > 1) {
			params = new String[cmdAndParams.length-1];
			System.arraycopy(cmdAndParams, 1, params, 0, params.length);
		}
	}
	
	/**
	 * 
	 * @return a String representing the prefix sent from the irc server
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * 
	 * @return a String containing the command sent from the irc server
	 */
	public String getCommand() {
		return command;
	}
	
	/**
	 * 
	 * @return a String array containing the parameters of the command sent from the irc server
	 */
	public String[] getParams() {
		return params;
	}
	
	/**
	 * 
	 * @return a String representing the trailing part of the message from the irc server
	 */
	public String getTrailing() {
		return trailing;
	}
	
	/**
	 * 
	 * @return a String representing the user's nick
	 */
	public String getNick() {
		int index;
		if ((index = prefix.indexOf("!")) == -1) return null;
		return prefix.substring(0, index);
	}
	
	/**
	 * Get the command that the user typed
	 * @return a String representing the command the user typed
	 */
	public String getUserCommand() {
		if (trailing == null || !trailing.startsWith(IDENTIFIER)) return null;
		String noIdent = trailing.substring(1).trim(); //allows for both @command and @ command
		int index = noIdent.indexOf(" ");
		if (index == -1)
			index = noIdent.length();
		return noIdent.substring(0, index).toLowerCase().trim();
	}
	
	/**
	 * Returns the string following the command the user typed
	 * @return the string following the command the user typed
	 */
	public String getUserParam()
	{
	    String command = getUserCommand();
        if (command == null || command.length() +2 >= trailing.length())
            return null;
        return trailing.substring(command.length()+2).trim();
	}
	
	/**
	 * Returns the parameters of the command the user typed
	 * @return a String array containing the parameters of the user command
	 */
	public String[] getUserParams() {
		String param = getUserParam();
		if (param == null)
		{
		    return null;
		}
		else if (param.contains(" "))
		{
			return param.split(" ");
		}
		else
		{
			return new String[] {param};
		}
	}
	
}