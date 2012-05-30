from irc.commands import Command

class echo(Command):
        
    def execute(self, client, nick, cmdInput, loc):
        try:
            client.privMsg(cmdInput, loc)
        except BaseException, e:
            print e
            client.privMsg(str(e), loc)