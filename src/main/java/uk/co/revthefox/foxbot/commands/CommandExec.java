package uk.co.revthefox.foxbot.commands;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.UtilEvalError;
import org.apache.commons.lang3.StringUtils;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;
import uk.co.revthefox.foxbot.FoxBot;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExec extends Command
{
    private static FoxBot foxbot;
    private static Interpreter interpreter;

    static
    {
        try
        {
            interpreter = new Interpreter();
            interpreter.getNameSpace().doSuperImport();
            interpreter.setStrictJava(false);
        }
        catch (Exception ex)
        {
            Logger.getLogger(CommandExec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CommandExec(FoxBot foxbot)
    {
        super("exec", "command.exec");
        this.foxbot = foxbot;
    }

    @Override
    public void execute(MessageEvent event, String[] args)
    {
        User sender = event.getUser();
        Channel channel = event.getChannel();
        PircBotX bot = foxbot.getBot();

        try
        {
            interpreter.getNameSpace().doSuperImport();
            interpreter.set("sender", sender);
            interpreter.set("channel", channel);
            interpreter.set("event", event);
            interpreter.set("bot", bot);
            interpreter.set("foxbot", foxbot);
            interpreter.eval(StringUtils.join(args, " ").trim());
        }
        catch (EvalError | UtilEvalError ex)
        {
            bot.sendMessage(channel, ex.getLocalizedMessage());
            Logger.getLogger(CommandExec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
