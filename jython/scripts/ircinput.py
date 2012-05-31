from irc.adaptors import InputHandlerAdapter

class ircinput(InputHandlerAdapter):
    
    def handleInvite(self, ircEvent):
        try:
            self.getIrcClient().join(ircEvent.getSource().getTrailing())
        except BaseException, e:
            print e
            
    def handleKick(self, ircEvent):
        try:
            self.getIrcClient().join(ircEvent.getSource().getParams()[0])
        except BaseException, e:
            print e
    
    def handlePing(self, ircEvent):
        try:
            self.getIrcClient().write("PONG :" + ircEvent.getSource().getTrailing())
        except BaseException, e:
            print e
            
    