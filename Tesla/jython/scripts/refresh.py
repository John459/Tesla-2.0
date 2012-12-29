import echo
import reverse
import ircinput
import cmd
import calc
import chat
import eightball
import hangman
from _update import update

from irc.adaptors import InputHandlerAdapter
from irc import IrcClient

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
		reload(eightball)
		reload(hangman)
		update.doUpdate(update(), self.getIrcClient(), msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])
