import echo
import reverse
import ircinput
import cmd
import calc
import chat

from irc.adaptors import InputHandlerAdapter

class refresh(InputHandlerAdapter):
    
    def handleUserCommand(self, ircEvent):
        msg = ircEvent.getSource()
        if (msg.getUserCommand() == "refresh"):
            try:
                self.getIrcClient().getInputEventListeners().clear()
                reload(echo)
                reload(reverse)
                reload(ircinput)
                reload(cmd)
                reload(calc)
                reload(chat)
                self.getIrcClient().loadModules("jython/scripts/", "scripts")
                self.getIrcClient().privMsg("Scripts refreshed.", msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])
