from irc.adaptors import InputHandlerAdapter


class echo(InputHandlerAdapter):
        
    def handleUserCommand(self, ircEvent):
        msg = ircEvent.getSource()
	if (msg.getUserCommand() == "echo"):
            try:
                self.getIrcClient().privMsg(msg.getUserParam(), msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])
