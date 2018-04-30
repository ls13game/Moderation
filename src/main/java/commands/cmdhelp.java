package commands;

import UTIL.STATIC;
import anderes.Dev;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmdhelp implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder help = new EmbedBuilder();
        EmbedBuilder owner = new EmbedBuilder();
        help.setColor(Color.green);
        help.setTitle("ℹ Hilfe");
        help.addField("","**Allgemein**",false);
        help.addField(STATIC.PREFIX+"ping","Zeigt den derzeitigen Bot ping",false);
        help.addField(STATIC.PREFIX+"report @User","Reportet einen User",false);
        help.addField(STATIC.PREFIX+"support","Benachrichtigt die Supporter das du Hilfe brauchst",false);
        help.addField(STATIC.PREFIX+"info","Zeigt bot informationen",false);
        help.addField(STATIC.PREFIX+"userinfo","Zeigt informationen Über dich",false);
        help.addField(STATIC.PREFIX+"serverinfo","Zeigt informationen über den Server",false);
        help.addField(STATIC.PREFIX+"twitter","Zeigt meinen Twitter Account",false);
        help.addField("","**Moderation**",false);
        help.addField(STATIC.PREFIX+"ac","Autochannel Command",false);
        //help.addField(STATIC.PREFIX+"linkreport","Schaltet den Link Report An oder aus",false);
        help.addField(STATIC.PREFIX+"say","Sagt etwas als bot",false);
        help.addField(STATIC.PREFIX+"clear","löscht eine bestimmte anzahl an Nachrichten",false);
        help.addField(STATIC.PREFIX+"kick @User (Grund)","Kickt einen User",false);
        help.addField(STATIC.PREFIX+"createchannels","Erstellt die benötigten channels",false);
        help.addField(STATIC.PREFIX+"anleitung","Zeigt die Anleitung",false);
        help.addField("","",false);
        help.addField("Weitere Commands folgen","",false);
        help.setFooter("Coded by ls13game®#8562 & oskar#7402", event.getJDA().getSelfUser().getAvatarUrl());
        event.getTextChannel().sendMessage(help.build()).queue(msg -> {msg.delete().queueAfter(2, TimeUnit.MINUTES);});
        if (Dev.isDev(event.getAuthor())){
            owner.setColor(Color.cyan);
            owner.setTitle("Owner Help");
            owner.addField(STATIC.PREFIX+"guilds","Zeigt alle server wo der bot drauf läuft",false);
            owner.addField(STATIC.PREFIX+"game","Ändert die playingmessage",false);
            owner.addField(STATIC.PREFIX+"ebsay","Schreibt eine Message im Embedbuilder",false);
            owner.addField(STATIC.PREFIX+"MsgOwner","Schreibt allen serverownern eine Nachricht",false);
            owner.addField(STATIC.PREFIX+"stop","Stopt den Bot",false);
            owner.addField(STATIC.PREFIX+"perms","Zeigt die Rechte des Bots auf dem Server",false);
            owner.addField(STATIC.PREFIX+"channels","Zeigt an ob es den jeweiligen channel gibt",false);
            event.getTextChannel().sendMessage(owner.build()).queue(msg -> {msg.delete().queueAfter(2, TimeUnit.MINUTES);});
        }
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
    }


    public String help() {
        return null;
    }
}
