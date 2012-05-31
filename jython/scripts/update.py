import echo
import reverse
import ircinput

from irc.adaptors import InputHandlerAdapter

class update(InputHandlerAdapter):
    
    def handleUserCommand(self, ircEvent):
        msg = ircEvent.getSource()
        if (msg.getUserCommand() == "update"):
            try:
                self.getIrcClient().getInputEventListeners().clear()
                reload(echo)
                reload(reverse)
                reload(ircinput)
                '''self.getIrcClient().loadModule("update")
                self.getIrcClient().loadModule("ircinput")
                self.getIrcClient().loadModule("echo")
                self.getIrcClient().loadModule("reverse")'''
                self.getIrcClient().loadModules("jython/scripts/")
                self.getIrcClient().privMsg("Scripts updated.", msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])