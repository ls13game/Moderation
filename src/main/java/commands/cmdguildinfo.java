package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmdguildinfo implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder info  = new EmbedBuilder().setColor(Color.GREEN);
        info.setTitle("ℹ Serverinfo von "+event.getGuild().getName());
        info.setThumbnail(event.getGuild().getIconUrl());
        info.addField("ID:",event.getGuild().getId(),false);
        info.addField("Guildname:",event.getGuild().getName(),false);
        info.addField("Members:",""+event.getGuild().getMembers().size(),false);
        info.addField("Textchannels:",""+event.getGuild().getTextChannels().size(),false);
        info.addField("Voicechannels:",""+event.getGuild().getVoiceChannels().size(),false);
        info.addField("Rolen:",""+event.getGuild().getRoles().size(),false);
        info.addField("Serverowner:",event.getGuild().getOwner().getEffectiveName()+"#"+event.getGuild().getOwner().getUser().getDiscriminator(),false);
        event.getTextChannel().sendMessage(info.build()).queue(msg -> {msg.delete().queueAfter(1, TimeUnit.MINUTES);});
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
