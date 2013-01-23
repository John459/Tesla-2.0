class player():
	guesses = 0
	nick = ""

	def __init__(self, nick):
		self.nick = nick

class hman():

	#blacklist = list()
	#players = dict()
	#guesses = list()
	#word = ""
	#guess = ""
	#play = False

	def __init__(self, word):
		self.word = word
		self.guess = self.makeGuess('', word, "")
		self.players = dict()
		self.blacklist = list()
		self.guesses = list()

	def makeGuess(self, char, word, lastGuess):
		guess = str()
		i = 0
		length = len(word)
		while (i < length):
			if (i < len(lastGuess) and lastGuess[i] != "-" and lastGuess[i] != " "):
				guess += lastGuess[i]
			else:
				if (word[i] == char):
					guess += char
				elif (word[i] == " "):
			 		guess += " "
				else:
			 		guess += "-"
			i = i + 1
		return guess

	def checkPlayer(self, nick):
		player = self.players[nick]
		if (player.guesses > 6):
			del self.players[nick]
			self.blacklist.append(nick)
			return "p:" + nick + " has been removed."
		return "n:You have been wrong " + str(player.guesses) + "/7 times."
		  
	def doGuess(self, nick, guess):
		if (nick in self.blacklist):
			return "n:You have already lost."
		if (not(nick in self.players)):
			self.players[nick] = player(nick)
		if (guess in self.guesses):
			return "n:"+ guess + " has already been guessed."
		if (len(guess) > 1):
			if (guess != self.word):
				self.players[nick].guesses += 1
				return self.checkPlayer(nick)
			return "p:" + self.word
		self.guesses.append(guess)
		newGuess = self.makeGuess(guess, self.word, self.guess)
		if (newGuess == self.guess):
			self.players[nick].guesses += 1
			return self.checkPlayer(nick)
		self.guess = newGuess
		return "p:" + newGuess

	def gameover(self):
		self.players.clear()
		del self.guesses[0:len(self.guesses)]
		del self.blacklist[0:len(self.blacklist)]
