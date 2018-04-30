package commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Oskar
 * on 24.03.2018
 * for Moderation
 * github.com/oskardevkappa/
 */


public class cmdNick implements Command {

    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }


    public void action(String[] args, MessageReceivedEvent event) {
        Message msg = event.getMessage();
        TextChannel tc = event.getTextChannel();

        if (msg.getMember().getPermissions().contains(Permission.NICKNAME_CHANGE))
        if (msg.getMentionedMembers().size() > 0){
            Member m = event.getMember();
            Member mm = msg.getMentionedMembers().get(0);


        }

    }


    public void executed(boolean sucess, MessageReceivedEvent event) {

    }


    public String help() {
        return null;
    }
}
