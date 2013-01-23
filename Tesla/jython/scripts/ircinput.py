from irc.adaptors import InputHandlerAdapter

class ircinput(InputHandlerAdapter):

    def handleUserCommand(self, ircEvent):
	try:
		msg = ircEvent.getSource()
		if (msg.getUserCommand() == "irc" and msg.getNick() == "john"):
			s = str()
			for st in msg.getUserParams():
				s += st + " "
			self.getIrcClient().write(s[0:len(s)-1])
	except BaseException, e:
	   	print e


    def handleInvite(self, ircEvent):
        try:
            self.getIrcClient().join(ircEvent.getSource().getTrailing())
        except BaseException, e:
            print e
            
    def handleKick(self, ircEvent):
        try:
           msg = ircEvent.getSource()
	   if (msg.getTrailing() == self.getIrcClient().getNick()):
		   self.getIrcClient().join(msg.getParams()[0])
        except BaseException, e:
            print e
    
    def handlePing(self, ircEvent):
        try:
            self.getIrcClient().write("PONG :" + ircEvent.getSource().getTrailing())
        except BaseException, e:
            print e
            
