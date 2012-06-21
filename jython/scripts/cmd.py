import subprocess

from irc.adaptors import InputHandlerAdapter

class cmd(InputHandlerAdapter):
        
    def execute(self, command):
        process = subprocess.Popen(["/bin/sh", "-c", command],
                                   stdout=subprocess.PIPE,
                                   stderr=subprocess.PIPE)
        output = process.communicate()
        return [output[0], output[1]]
    
    def handleUserCommand(self, event):
        msg = event.getSource()
        if (msg.getUserCommand() == "cmd" and msg.getNick() == "john"):
            try:
                print msg.getUserParam()
                output = self.execute(msg.getUserParam())
                outputParts = output[0].rsplit("\n")
                errorParts = output[1].rsplit("\n")
                print output[0]
                for outputPart in outputParts:    
                    self.getIrcClient().privMsg(outputPart, msg.getParams()[0])
                for errorPart in errorParts:
                    self.getIrcClient().privMsg(errorPart, msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])