package commands;

import UTIL.STATIC;
import anderes.Dev;
import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zekro on 12.04.2017 / 11:05
 * supremeBot/commands
 * © zekro 2017
 */

public class cmdsay implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
/**
        if (event.getMessage().getMentionedChannels().size() >0){
            String content = String.join(" ",event.getMessage().getContentDisplay().replace(STATIC.PREFIX,"").replace("say","").replace(args[1],""));
            event.getMessage().getMentionedChannels().get(0).sendMessage(content).queue();
            return;
        }
 */
        if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)|| Dev.isDev(event.getAuthor())) {
            String content = String.join(" ", args);
            event.getTextChannel().sendMessage(content).queue();
        } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `say` wurde ausgeführt");
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }

    @Override
    public String help() {
        return null;
    }

}