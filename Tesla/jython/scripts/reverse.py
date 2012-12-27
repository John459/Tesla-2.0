from irc.adaptors import InputHandlerAdapter
from java.lang import StringBuilder

class reverse(InputHandlerAdapter):
    
    def handleUserCommand(self, ircEvent):
        msg = ircEvent.getSource()
        if (msg.getUserCommand() == "reverse"):
            try:
                self.getIrcClient().privMsg(StringBuilder(msg.getUserParam()).reverse().toString(), msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])