import echo
import reverse

from irc.commands import Command

class update(Command):
    
    def execute(self, client, nick, cmdInput, loc):
        try:
            reload(echo)
            reload(reverse)
            client.privMsg("Scripts updated.", loc)
        except BaseException, e:
            print e
            client.privMsg(str(e), loc)