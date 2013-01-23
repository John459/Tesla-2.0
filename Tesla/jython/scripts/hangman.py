from irc.adaptors import InputHandlerAdapter
from games.hangman.hman import hman
import random

class hangman(InputHandlerAdapter):

	games = dict()
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
			self.getIrcClient().privMsg("Hangman has started!. Type the command 'hman [guess]' to start guessing letters.", channel)
			word = self.words[pos]
			word = word[:len(word)-1]
			self.games[channel] = hman(word)
			self.getIrcClient().privMsg(self.games[channel].guess, channel)
			return
		if (not(channel in self.games)):
			return
		elif (msg.getUserParams()[0] == "stop"):
		 	self.getIrcClient().privMsg("The game has been stopped. The word was: " + self.games[channel].word, channel)
			self.games[channel].gameover()
			del self.games[channel]
		 	return
		elif (msg.getUserParams()[0] == "word"):
		  	self.getIrcClient().privMsg(self.games[channel].guess, channel)
		elif (msg.getUserParams()[0] == "guesses"):
		 	s = ""
		 	for guess in self.games[channel].guesses:
				s = s + str(guess) + ", "
			s = s[:len(s)-2]
			self.getIrcClient().notice(s, nick)
		else:
		 	guess = msg.getUserParams()[0]
		 	response = self.games[channel].doGuess(nick, guess)
			key = response[:2]
			response = response[2:]
			if (key == "n:"):
				self.getIrcClient().notice(response, nick)
			else:
				self.getIrcClient().privMsg(response, channel)
			if (response == self.games[channel].word):
				self.getIrcClient().privMsg(nick + " wins!", channel)
				self.games[channel].gameover()
				del self.games[channel]
			elif (response == "Game over"):
			 	self.getIrcClient().privMsg("The word was: " + self.games[channel].word, channel)
			 	self.games[channel].gameover()
			 	del self.games[channel]


