from irc.commands import Command

from java.lang import StringBuilder

class reverse(Command):
    
    def execute(self, client, nick, cmdInput, loc):
        try:
            client.privMsg(StringBuilder(cmdInput).reverse().toString(), loc)
        except BaseException, e:
            print e
            client.privMsg(str(e), loc)
