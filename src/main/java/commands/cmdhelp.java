package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmdhelp implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
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
        help.addField("","**Moderation**",false);
        help.addField(STATIC.PREFIX+"say","Sagt etwas als bot",false);
        help.addField(STATIC.PREFIX+"clear","löscht eine bestimmte anzahl an Nachrichten",false);
        help.addField(STATIC.PREFIX+"kick @User","Kickt einen User",false);
        help.addField(STATIC.PREFIX+"createchannels","Erstellt die benötigten channels",false);
        help.addField("","",false);
        help.addField("Weitere Commands folgen","",false);
        help.setFooter("Coded by ls13game®#8562 & oskar#7402", event.getJDA().getSelfUser().getAvatarUrl());
        event.getTextChannel().sendMessage(help.build()).queue(msg -> {msg.delete().queueAfter(2, TimeUnit.MINUTES);});
        if (event.getAuthor().getId().equals(STATIC.DEV)|| event.getAuthor().getId().equals(STATIC.DEV2)){
            owner.setColor(Color.cyan);
            owner.setTitle("Owner Help");
            owner.addField(STATIC.PREFIX+"guilds","Zeigt alle server wo der bot drauf läuft",false);
            owner.addField(STATIC.PREFIX+"game","Ändert die playingmessage",false);
            owner.addField(STATIC.PREFIX+"MsgOwner","Schreibt allen serverownern eine Nachricht",false);
            owner.addField(STATIC.PREFIX+"stop","Stopt den Bot",false);
            event.getTextChannel().sendMessage(owner.build()).queue(msg -> {msg.delete().queueAfter(2, TimeUnit.MINUTES);});
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
}
