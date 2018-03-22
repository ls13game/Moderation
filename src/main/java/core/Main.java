package core;

import MySQLTest.MySQLAccess;
import UTIL.SECRETS;
import commands.*;
import listeners.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Main {

    static public JDA jda;
    public static JDABuilder builder;
    public static String CommandSize;

    public static void main(String[] Args) throws Exception{

        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(SECRETS.TOKEN);
        builder.setAutoReconnect(true);
        addListeners();
        addCommands();

        try {
            jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //MYSQL
         /**   MySQLAccess sql = new MySQLAccess();
            sql.readDataBase();*/
        }





    public static void addCommands() {

        //EVERYONE
        commandHandler.commands.put("ping",new cmdping());
        commandHandler.commands.put("report",new cmdreport());
        commandHandler.commands.put("help",new cmdhelp());
        commandHandler.commands.put("support",new cmdSupport());
        commandHandler.commands.put("stats",new cmdstats());

        //MODS
        commandHandler.commands.put("clear",new cmdClear());
        commandHandler.commands.put("say",new cmdsay());



        commandHandler.commands.put("kick",new cmdkick());
        commandHandler.commands.put("MsgOwner", new cmdMsgOwner());
        commandHandler.commands.put("guilds",new cmdguilds());
        commandHandler.commands.put("delete",new cmddelete());
        commandHandler.commands.put("ban",new cmdban());
        commandHandler.commands.put("createchannels",new cmdcheck());
        commandHandler.commands.put("info",new cmdinfo());
        commandHandler.commands.put("userinfo",new cmduserinfo());
        commandHandler.commands.put("serverinfo",new cmdguildinfo());
        commandHandler.commands.put("stop",new cmdstop());
        //commandHandler.commands.put("ac",new cmdautochannel());
        commandHandler.commands.put("game", new cmdGame());
        CommandSize = String.valueOf(commandHandler.commands.size());
        //commandHandler.commands.put("votet",new cmdvotet());
        System.out.println(CommandSize + " Commands Geladen");
    }

    public static void addListeners() {
        builder.addEventListener(new commandListener());
        builder.addEventListener(new reactionListener());
        builder.addEventListener(new linklistener());
        builder.addEventListener(new selflistener());
        builder.addEventListener(new leavelistener());
        builder.addEventListener(new joinlistener());
        builder.addEventListener(new botlistspace());
        builder.addEventListener(new autochannelhandler());
        builder.addEventListener(new onReady());
        System.out.println("Listener Geladen");

    }

}