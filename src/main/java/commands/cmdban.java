package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmdban implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        EmbedBuilder success = new EmbedBuilder();
        if (event.getMember().hasPermission(Permission.BAN_MEMBERS)){
            if (args.length <1){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte wähle einen User aus");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
            }
            if(!event.getGuild().getSelfMember().canInteract(event.getMessage().getMentionedMembers().get(0))){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Ich habe keine Rechte dazu");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            String member = event.getMessage().getMentionedMembers().get(0).getUser().getId();
            event.getGuild().getController().kick(member).queue();
            success.setColor(Color.GREEN);
            success.setDescription("Erfolgreich "+ event.getGuild().getMemberById(member).getAsMention()+" gebannt");
            event.getTextChannel().sendMessage(success.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
        }else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS); });
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
