package core;

import UTIL.SECRETS;
import UTIL.STATIC;
import commands.*;
import listeners.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Main {
    public static JDABuilder builder;

    public static void main(String[] Args) {

        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(SECRETS.TOKEN);
        builder.setAutoReconnect(true);
        builder.setGame(Game.playing(STATIC.GAME));
        builder.setStatus(OnlineStatus.ONLINE);
        addListeners();
        addCommands();

        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void addCommands() {
        commandHandler.commands.put("ping",new cmdping());
        commandHandler.commands.put("clear",new cmdClear());
        commandHandler.commands.put("say",new cmdsay());
        commandHandler.commands.put("play",new cmdplay());
        commandHandler.commands.put("report",new cmdreport());
        commandHandler.commands.put("support",new cmdSupport());
        commandHandler.commands.put("help",new cmdhelp());
        commandHandler.commands.put("kick",new cmdkick());
        commandHandler.commands.put("stats",new cmdstats());
        commandHandler.commands.put("reset",new cmdreset());
        commandHandler.commands.put("guilds",new cmdguilds());
        commandHandler.commands.put("delete",new cmddelete());
        commandHandler.commands.put("ban",new cmdban());
        commandHandler.commands.put("createchannels",new cmdcheck());
        commandHandler.commands.put("info",new cmdinfo());
        //commandHandler.commands.put("votet",new cmdvotet());
        System.out.println("Commands Geladen");
    }

    public static void addListeners() {
        builder.addEventListener(new commandListener());
        builder.addEventListener(new reactionListener());
        builder.addEventListener(new linklistener());
        //builder.addEventListener(new selflistener());
        builder.addEventListener(new leavelistener());
        builder.addEventListener(new joinlistener());
        builder.addEventListener(new botlistspace());
        System.out.println("Listener Geladen");
    }

}