from cmd import cmd

from irc.adaptors import InputHandlerAdapter


class calc(InputHandlerAdapter):
    
    def handleUserCommand(self, event):
        msg = event.getSource()
        if (msg.getUserCommand() == "calc" and msg.getNick() == "john"):
            try:
                command = "echo '" + msg.getUserParam() + "' | bc -l"
                output = cmd.execute(cmd(), command)
                self.getIrcClient().privMsg(output[0], msg.getParams()[0])
                self.getIrcClient().privMsg(output[1], msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])
