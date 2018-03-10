package commands;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
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
        help.setColor(Color.green);
        help.setTitle("Hilfe");
        help.addField("","**Allgemein**",false);
        help.addField(STATIC.PREFIX+"ping","Zeigt den derzeitigen Bot ping",false);
        help.addField(STATIC.PREFIX+"report @User","Reportet einen User",false);
        help.addField(STATIC.PREFIX+"support","Benachrichtigt die Supporter das du Hilfe brauchst",false);
        help.addField(STATIC.PREFIX+"info","Zeigt bot informationen",false);
        help.addField("","**Moderation**",false);
        help.addField(STATIC.PREFIX+"say","Sagt etwas als bot",false);
        help.addField(STATIC.PREFIX+"clear","löscht eine bestimmte anzahl an Nachrichten",false);
        help.addField(STATIC.PREFIX+"kick @User","Kickt einen User",false);
        help.addField(STATIC.PREFIX+"createchannels","Erstellt die benötigten channels",false);
        help.addField("","",false);
        help.addField("Weitere Commands folgen","",false);
        help.setFooter("Coded by ls13game®#8562","https://cdn.discordapp.com/avatars/235395943619493888/20dd0409d45d90ddf394b3287633373e.png");
        event.getTextChannel().sendMessage(help.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
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
