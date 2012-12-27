from irc.adaptors import InputHandlerAdapter

class chat(InputHandlerAdapter):
    
    def handlePrivMsg(self, ircEvent):
        msg = ircEvent.getSource()
        try:
            if msg.getTrailing().startswith('Tesla'):
                self.getIrcClient().privMsg("My ability to respond is being worked on.", msg.getParams()[0])
        except BaseException, e:
            self.getIrcClient().privMsg(str(e), msg.getParams()[0])