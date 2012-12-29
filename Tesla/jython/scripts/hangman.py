from irc.adaptors import InputHandlerAdapter
from games.hangman.hman import hman
import random

class hangman(InputHandlerAdapter):

	games = dict()
	word = str()
	words = list()

	def __init__(self):
		f = open('words')
		self.words = f.readlines()
		f.close()

	def handleUserCommand(self, ircEvent):
		try:
			self.playHangman(ircEvent)
		except BaseException, e:
			print e
			self.getIrcClient().privMsg(str(e), ircEvent.getSource().getParams()[0])

	def playHangman(self, ircEvent):
		msg = ircEvent.getSource()
		if (msg.getUserCommand() != "hman"):
			return
		nick = msg.getNick()
		channel = msg.getParams()[0]
		if (msg.getUserParams()[0] == "start" and not(channel in self.games)):
			pos = int(random.random() * len(self.words))
			self.getIrcClient().privMsg(nick + " has started hangman. Use the command 'hman join' to join. When ready, type the command 'hman begin' to begin the game.", channel)
			self.word = self.words[pos]
			self.word = self.word[:len(self.word)-1]
			self.games[channel] = hman(self.word)
			self.games[channel].addPlayer(nick)
			return
		if (not(channel in self.games)):
			return
		if (msg.getUserParams()[0] == "begin"):
		 	self.games[channel].play = True
		 	self.getIrcClient().privMsg("The game has started. Use the command 'hman [guess]' to start guessing letters.", channel)
			self.getIrcClient().privMsg(self.games[channel].guess, channel)
		elif (msg.getUserParams()[0] == "join"):
		 	if (self.games[channel].addPlayer(nick)):
				self.getIrcClient().privMsg(nick + " has joined the game.", channel)
		elif (msg.getUserParams()[0] == "stop"):
		 	self.getIrcClient().privMsg("The game has been stopped. The word was: " + self.word, channel)
			self.games[channel].gameover()
			del self.games[channel]
		 	return
		else:
		 	guess = msg.getUserParams()[0]
		 	response = self.games[channel].doGuess(nick, guess)
			key = response[:2]
			response = response[2:]
			if (key == "n:"):
				self.getIrcClient().notice(response, nick)
			else:
				self.getIrcClient().privMsg(response, channel)
			if (response == self.word):
				self.getIrcClient().privMsg(nick + " wins!", channel)
				self.games[channel].gameover()
				del self.games[channel]
			elif (response == "Game over"):
			 	self.getIrcClient().privMsg("The word was: " + self.word, channel)
			 	self.games[channel].gameover()
			 	del self.games[channel]


