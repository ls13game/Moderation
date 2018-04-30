package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import anderes.Dev;
/**
 * Created by Oskar
 * on 23.03.2018
 * for Moderation
 * github.com/oskardevkappa/
 */


public class cmdEbsay implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder error = new EmbedBuilder().setColor(Color.RED).setTitle("Error");

            TextChannel tc = event.getTextChannel();
            User u = event.getAuthor();

        if (Dev.isDev(u)) {
            if (args.length < 1) {
                error(tc, help());
            } else if (event.getMessage().getContentDisplay().contains("_")) {

                String xmsg = event.getMessage().getContentDisplay();
                String ymsg = xmsg.replace(STATIC.PREFIX + "ebsay ", "");
                String[] zmsg = ymsg.split("_");

                mitkomma(zmsg, u, tc);

            } else {
                String xmsg = event.getMessage().getContentDisplay();
                String ymsg = xmsg.replace(STATIC.PREFIX + "ebsay ", "");
                ohnekomam(ymsg, u, tc);

            }
        } else {
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        }
    }



    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();

    }


    public String help() {
        return STATIC.PREFIX +  "ebsay Message \n" + STATIC.PREFIX + "ebsay Titel_Nachricht";
    }
    public void error(TextChannel tc, String content){
            tc.sendMessage(new EmbedBuilder().setColor(Color.RED).setDescription(content).build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
        }

     public EmbedBuilder mitkomma(String[] msg, User u, TextChannel tc ){

         EmbedBuilder eb = new EmbedBuilder()
                 .setTitle(String.valueOf(msg[0]))
                 .setColor(Color.lightGray)
                 .setDescription(String.valueOf(msg[1]))
                 .setAuthor(u.getName(), null, u.getAvatarUrl());
         tc.sendMessage(eb.build()).queue();

        return eb;
     }
     public EmbedBuilder ohnekomam(String msg, User u, TextChannel tc){

         EmbedBuilder eb = new EmbedBuilder()
                 .setColor(Color.lightGray)
                 .setDescription(String.valueOf(msg))
                 .setAuthor(u.getName(), null, u.getAvatarUrl());
         tc.sendMessage(eb.build()).queue();

        return eb;
     }
    }

