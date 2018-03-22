package listeners;

import UTIL.STATIC;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.commons.validator.UrlValidator;

import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class linklistener extends ListenerAdapter {


    public void onMessageReceived(MessageReceivedEvent event) {
        List argsList = Arrays.asList(event.getMessage().getContentDisplay());
        StringBuilder sb = new StringBuilder();
        argsList.forEach(s -> sb.append(s + " "));
        if (!event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().getId().equals("264445053596991498")) {
                if (!event.getGuild().getId().equals("387812458661937152")) {
                    if (event.getGuild().getTextChannelsByName("reports",false).size()>0) {
                        if (!event.getMessage().getContentDisplay().contains(".png")) {
                            if (!event.getMessage().getContentDisplay().contains(".jpg")) {
                                if (!event.getMessage().getContentDisplay().contains(".gif")) {
                                    if (event.getMessage().getContentDisplay().contains("http")) {
                                        EmbedBuilder report = new EmbedBuilder();
                                        //event.getMessage().addReaction(STATIC.OK).complete();
                                        report.setColor(Color.CYAN);
                                        report.setAuthor(event.getMessage().getAuthor().getName(), null, event.getAuthor().getAvatarUrl());
                                        report.setTitle("Automod");
                                        report.setDescription("In dieser nachricht wurde ein Link festegesstellt");
                                        report.addField("Message inhalt","`"+sb+"`",false);
                                        report.addField("Channel", event.getTextChannel().getAsMention(), false);
                                        report.addField("Zum löschen führe in dem jeweiligen channel", "`" + STATIC.PREFIX + "delete " + event.getMessage().getId() + "` aus", false);
                                        Message message = event.getGuild().getTextChannelsByName("reports", false).get(0).sendMessage(report.build()).complete();
                                        message.addReaction(STATIC.OK).complete();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
