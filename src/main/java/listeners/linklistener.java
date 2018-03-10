package listeners;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.commons.validator.UrlValidator;

import java.awt.*;

public class linklistener extends ListenerAdapter {


    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getGuild().getTextChannelsByName("reports",false)== null){
            return;
        }
        if (event.getGuild().getId().equals("264445053596991498")) {
            return;
        }
        if (event.getGuild().getId().equals("387812458661937152")){
            return;
        }

        if (event.getMessage().getContentDisplay().contains(".png")) {
            return;
        }
        if (event.getMessage().getContentDisplay().contains(".jpg")) {
            return;
        }
        if (event.getMessage().getContentDisplay().contains(".gif")){
            return;
        }
        if (event.getMessage().getContentDisplay().contains("http" + "://")) {
            EmbedBuilder report = new EmbedBuilder();
            //event.getMessage().addReaction(STATIC.OK).complete();
            report.setColor(Color.CYAN);
            report.setAuthor(event.getMessage().getAuthor().getName(), null, event.getAuthor().getAvatarUrl());
            report.setTitle("Automod");
            report.setDescription("In dieser nachricht wurde ein Link festegesstellt");
            report.addField("Channel", event.getTextChannel().getAsMention(), false);
            report.addField("Zum löschen führe in dem jeweiligen channel","`"+STATIC.PREFIX+"delete "+event.getMessage().getId()+"` aus",false);
            Message message = event.getGuild().getTextChannelsByName("reports", false).get(0).sendMessage(report.build()).complete();
            message.addReaction(STATIC.OK).complete();
        }
    }
}
