package listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class autochannelhandler extends ListenerAdapter {

    List<VoiceChannel> active = new ArrayList<>();

    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {

        HashMap<VoiceChannel, Guild> autochans = commands.cmdautochannel.getAutoChannels();
        VoiceChannel vc = event.getChannelJoined();
        Guild g = event.getGuild();

        if (autochans.containsKey(vc)) {
            VoiceChannel nvc = (VoiceChannel) g.getController().createCopyOfChannel(vc).setName(vc.getName()+"[TEMP]").complete();
            active.add(nvc);

            if (vc.getParent() != null)
                nvc.getManager().setParent(vc.getParent()).queue();

            g.getController().modifyVoiceChannelPositions().selectPosition(nvc).moveTo(vc.getPosition() + 1).queue();
            g.getController().moveVoiceMember(event.getMember(), nvc).queue();
        }
    }
    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {

        HashMap<VoiceChannel, Guild> autochans = commands.cmdautochannel.getAutoChannels();
        Guild g = event.getGuild();

        VoiceChannel vc = event.getChannelJoined();

        if (autochans.containsKey(vc)) {
            VoiceChannel nvc = (VoiceChannel) g.getController().createCopyOfChannel(vc).setName(vc.getName()+"[TEMP]").complete();
            active.add(nvc);

            if (vc.getParent() != null)
                nvc.getManager().setParent(vc.getParent()).queue();

            g.getController().modifyVoiceChannelPositions().selectPosition(nvc).moveTo(vc.getPosition() + 1).queue();
            g.getController().moveVoiceMember(event.getMember(), nvc).queue();
        }

        vc = event.getChannelLeft();

        if (active.contains(vc) && vc.getMembers().size() == 0) {
            active.remove(vc);
            vc.delete().queue();
        }
    }
    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        VoiceChannel vc = event.getChannelLeft();

        if (active.contains(vc) && vc.getMembers().size() == 0) {
            active.remove(vc);
            vc.delete().queue();
        }
    }
    public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {
        HashMap<VoiceChannel, Guild> autochans = commands.cmdautochannel.getAutoChannels();
        if (autochans.containsKey(event.getChannel())) {
            commands.cmdautochannel.unsetChan(event.getChannel());
        }
    }

}