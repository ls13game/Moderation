package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.Time;

public class onReady extends ListenerAdapter {
    public void onReady(ReadyEvent event){
        EmbedBuilder start = new EmbedBuilder().setTitle(":battery: Bot ist gestartet").setColor(Color.cyan);
        event.getJDA().getTextChannelById("425717054935531520").sendMessage(start.build()).queue();
        event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
        gameHandler.start();

    }
}
