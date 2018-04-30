package commands;

import anderes.Dev;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class cmdchannels implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder info = new EmbedBuilder().setColor(Color.green).setTitle("ℹ Auskunft");
        EmbedBuilder help = new EmbedBuilder().setColor(Color.RED).setTitle("Hilfe");
        if (Dev.isDev(event.getAuthor())){
            if (args.length <1){
                help.addField("reports","schaut ab es einen Report channel gibt",false);
                event.getTextChannel().sendMessage(help.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            switch (args[0]){
                case "reports":
                    if (event.getGuild().getTextChannelsByName("reports",false).size()>0 ){
                        info.setDescription("Report Channel existiert");
                        event.getTextChannel().sendMessage(info.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                    }
            }
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
