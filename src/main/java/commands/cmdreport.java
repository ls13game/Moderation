package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmdreport implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        if (event.getGuild().getTextChannelsByName("reports",true).get(0) ==  null){
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Das Report system ist auf diesem Server ausgeschaltet");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
            return;
        }
        if (args.length <1){
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Bitte wähle einen User aus");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
            return;
        }
        EmbedBuilder report = new EmbedBuilder();
        report.setColor(Color.GREEN);
        report.setTitle("User Report");
        report.setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        report.addField("User:",event.getMessage().getMentionedMembers().get(0).getAsMention(),false);
        report.addField("Im Channel:",event.getTextChannel().getAsMention(),false);
        if (event.getMessage().getMentionedMembers().get(0).getNickname() == null) {
            report.addField("Grund:", event.getMessage().getContentDisplay().replace(STATIC.PREFIX, "").replace("report", "").replace("@", "").replace(event.getMessage().getMentionedUsers().get(0).getName(), ""), false);
        } else {
            report.addField("Grund:", event.getMessage().getContentDisplay().replace(STATIC.PREFIX, "").replace("report", "").replace("@", "").replace(event.getMessage().getMentionedMembers().get(0).getNickname(), ""), false);
        }
        Message message = event.getGuild().getTextChannelsByName("reports",false).get(0).sendMessage(report.build()).complete();
        message.addReaction(STATIC.OK).complete();
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }


    public String help() {
        return null;
    }
}
