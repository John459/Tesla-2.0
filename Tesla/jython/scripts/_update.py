import refresh

from irc import IrcClient

class update():

	def doUpdate(self, ircClient, channel):
		reload(refresh)
		ircClient.loadModules("jython/scripts/", "scripts")
		ircClient.privMsg("Scripts refreshed.", channel)

