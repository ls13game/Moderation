package commands;

import UTIL.STATIC;
import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zekro on 12.04.2017 / 11:05
 * supremeBot/commands
 * © zekro 2017
 */

public class cmdsay implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        if (event.getAuthor().getId().equals(STATIC.DEV)){
            List argsList = Arrays.asList(args);
            StringBuilder sb = new StringBuilder();
            argsList.forEach(s -> sb.append(s + " "));
            send(sb.toString(), event);
            return;
        }
        if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            List argsList = Arrays.asList(args);
            StringBuilder sb = new StringBuilder();
            argsList.forEach(s -> sb.append(s + " "));
            send(sb.toString(), event);
            return;
        } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }

    @Override
    public String help() {
        return null;
    }

    private void send(String msg, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(msg).queue();
    }
}