from irc.adaptors import InputHandlerAdapter
import random

class eightball(InputHandlerAdapter):

	responses = ["It is certain","It is decidedly so","Without a doubt",
			"Yes - definitely","You may rely on it","As I see it, yes"
			"Most likely","Outlook good","Yes","Signs point to yes",
			"Reply hazy, try again","Ask again later",
			"Better not tell you now","Cannot predict now",
			"Concentrate and ask again","Don't count on it",
			"My reply is no","My sources say no","Outlook not so good",
			"Very doubtful"]

	def handleUserCommand(self, ircEvent):
		msg = ircEvent.getSource()
		if (msg.getUserCommand() != "8ball"):
			return
		try:
			pos = int(random.random() * len(self.responses))
			response = self.responses[pos]
			self.getIrcClient().privMsg(response, msg.getParams()[0])
		except BaseException, e:
			print e
			self.getIrcClient().privMsg(str(e), msg.getParams()[0])
