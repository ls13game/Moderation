package commands;

import core.permsCore;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class cmddelete implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        EmbedBuilder success = new EmbedBuilder();
        if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            if (args.length < 1) {
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte gebe eine ID an");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
                return;
            }
            event.getTextChannel().getMessageById(args[0]).complete().delete().queue();
            success.setColor(Color.GREEN);
            success.setTitle("Erfolgreich");
            success.setDescription("Nachricht erfolgreich gelöscht");
            event.getTextChannel().sendMessage(success.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
        } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `delete` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }

    public String help() {
        return null;
    }
}
