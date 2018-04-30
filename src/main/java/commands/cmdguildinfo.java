package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class cmdguildinfo implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder info  = new EmbedBuilder().setColor(Color.GREEN);
        info.setTitle("ℹ Serverinfo von "+event.getGuild().getName());
        info.setThumbnail(event.getGuild().getIconUrl());
        info.addField("ID:",event.getGuild().getId(),false);
        info.addField("Guildname:",event.getGuild().getName(),false);
        info.addField("Members:",""+event.getGuild().getMembers().size(),false);
        info.addField("Textchannels:",""+event.getGuild().getTextChannels().size(),false);
        info.addField("Voicechannels:",""+event.getGuild().getVoiceChannels().size(),false);
        info.addField("Rollen:",""+event.getGuild().getRoles().size(),false);
        info.addField("Serverowner:",event.getGuild().getOwner().getEffectiveName()+"#"+event.getGuild().getOwner().getUser().getDiscriminator(),false);
        event.getTextChannel().sendMessage(info.build()).queue(msg -> {msg.delete().queueAfter(1, TimeUnit.MINUTES);});
    }


    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        SimpleDateFormat date=new SimpleDateFormat(
                "HH:mm");
        String date1=date.format(new Date());
        EmbedBuilder log = new EmbedBuilder().setColor(Color.YELLOW).setTitle("LOG").setFooter("Um: "+date1,event.getJDA().getSelfUser().getAvatarUrl()).setAuthor(event.getAuthor().getName(),null,event.getAuthor().getAvatarUrl());
        log.setDescription("Command `serverinfo` wurde ausgeführt");
        event.getMessage().delete().queue();
        if (event.getGuild().getTextChannelsByName("log",false).size() >0){
            event.getGuild().getTextChannelsByName("log",false).get(0).sendMessage(log.build()).queue();
        }
    }


    public String help() {
        return null;
    }
}
