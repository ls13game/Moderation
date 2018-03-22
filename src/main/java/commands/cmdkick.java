package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class cmdkick implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String member = event.getMessage().getMentionedMembers().get(0).getUser().getId();
        EmbedBuilder error = new EmbedBuilder();
        EmbedBuilder success = new EmbedBuilder();
        EmbedBuilder kick = new EmbedBuilder();
        if(event.getMember().hasPermission(Permission.KICK_MEMBERS)){
            if (args.length <1){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte Erwähne einen User");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            if (event.getMessage().getMentionedMembers().size() <0 ){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Bitte Erwähne einen User");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            if(!event.getGuild().getSelfMember().canInteract(event.getMessage().getMentionedMembers().get(0))){
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Ich habe keine Rechte dazu");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
                return;
            }
            if (event.getGuild().getOwner().getUser().getId().equals(member)) {
                error.setColor(Color.RED);
                error.setTitle("Error");
                error.setDescription("Du kannst nicht den serverowner kicken");
                event.getTextChannel().sendMessage(error.build()).queue(msg -> {
                    msg.delete().queueAfter(20, TimeUnit.SECONDS);
                });
                return;
            }
            kick.setColor(Color.GREEN);
            String content = String.join(" ", args);
            kick.setTitle("Du wurdest gekickt");
            kick.setDescription("Du wurdest vom server ` "+event.getGuild().getName()+" ` gekickt um zu joinen klicke [Hier]("+event.getTextChannel().createInvite().setMaxUses(1).complete().getURL()+")");
            event.getJDA().getUserById(member).openPrivateChannel().complete().sendMessage(kick.build()).queue();
            event.getGuild().getController().kick(member).queue();
            success.setColor(Color.green);
            success.setTitle("Erfolgreich");
            success.setDescription("Member "+event.getGuild().getMemberById(member).getAsMention()+" wurde erfolgreich gekickt");
            event.getTextChannel().sendMessage(success.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});


        } else {
            error.setColor(Color.RED);
            error.setTitle("Error");
            error.setDescription("Keine Berrechtigung");
            event.getTextChannel().sendMessage(error.build()).queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
            return;
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
