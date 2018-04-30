package commands;

import UTIL.STATIC;
import anderes.Dev;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class cmdanleitung implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder anleitung = new EmbedBuilder();
        EmbedBuilder error = new EmbedBuilder().setColor(Color.RED).setTitle("Error");
        if (event.getMember().hasPermission(Permission.ADMINISTRATOR)|| Dev.isDev(event.getAuthor())) {
            anleitung.setColor(Color.GREEN);
            anleitung.setTitle("Anleitung");
            anleitung.setDescription("Hallo ich bin der Moderationsbot\n" +
                    "Am besten Setzt du die Channel #reports und #support nur für dein Team sichtbar\n" +
                    "Was mach ich?");
            anleitung.addField("Automatischer Link report", "Ich schreibe automatisch in den report channel wenn ein link gepostet wurde (ausgenommen er hat ein .png, .jpg oder .gif)", false);
            anleitung.addField("User report", "Mit " + STATIC.PREFIX + "report @User können deine User andere User reporten", false);
            anleitung.addField("Support anfrage", "Mit " + STATIC.PREFIX + "support Können deine User im jeweiligen channel Support anfragen im jeweiligen channel anfragen", false);
            event.getTextChannel().sendMessage(anleitung.build()).queue(msg -> {msg.delete().queueAfter(1, TimeUnit.MINUTES); });
        } else {
            error.setDescription("Keine Rechte");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }


    public String help() {
        return null;
    }
}
