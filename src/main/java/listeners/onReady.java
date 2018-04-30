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
        System.out.println(" __  __               _                         _     _                 \n" +
                " |  \\/  |             | |                       | |   (_)                \n" +
                " | \\  / |   ___     __| |   ___   _ __    __ _  | |_   _    ___    _ __  \n" +
                " | |\\/| |  / _ \\   / _` |  / _ \\ | '__|  / _` | | __| | |  / _ \\  | '_ \\ \n" +
                " | |  | | | (_) | | (_| | |  __/ | |    | (_| | | |_  | | | (_) | | | | |\n" +
                " |_|  |_|  \\___/   \\__,_|  \\___| |_|     \\__,_|  \\__| |_|  \\___/  |_| |_|");
        EmbedBuilder start = new EmbedBuilder().setTitle(":battery: Bot ist gestartet").setColor(Color.cyan);
        event.getJDA().getTextChannelById("425717054935531520").sendMessage(start.build()).queue();
        event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
        gameHandler.start();
        commands.cmdautochannel.load(event.getJDA());
    }
}
