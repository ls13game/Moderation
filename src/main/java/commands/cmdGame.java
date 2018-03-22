package commands;

import UTIL.STATIC;
import listeners.gameHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oskar
 * on 19.03.2018
 * github.com/oskardevkappa/
 */

public class cmdGame implements Command {


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        TextChannel tc = event.getTextChannel();

     if (event.getAuthor().getId().equals(STATIC.DEV) || event.getAuthor().getId().equals(STATIC.DEV2)) {
            if (args.length < 1) {
                error(tc, help());

            } else {

                switch (args[0]) {
                    case "set":
                        gameHandler.stop();
                        set(args, event.getTextChannel(), event.getMember());
                        success(tc, "Successfully changed Game!");

                        break;
                    case "default":
                        gameHandler.start();
                        success(tc, "Successfully set game to default!");
                        break;
                    default:
                        error(tc, help());
                        break;
                }
            }
        } else
            error(tc, "Missing Permissions.");

    }



    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }

    @Override
    public String help() {
        String help = STATIC.PREFIX + "game set (Game) \n" +
                        STATIC.PREFIX + "game default \n";
        return help;
    }


    public void set(String[] args, TextChannel tc, Member m){
        if (args.length < 2 )
            tc.sendMessage(help()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        else {
            String out = String.join(" ", args);
            String game = out.replace("set ", "");
            gameHandler.CustomGame(game);
        }
    }
    public void error(TextChannel tc, String content) {
        tc.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription(content).build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
    }

    public void success(TextChannel tc, String content) {
        tc.sendMessage(new EmbedBuilder().setColor(Color.getHSBColor(130, 90, 68)).setDescription(content).build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});

    }
}
