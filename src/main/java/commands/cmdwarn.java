/*
package commands;

import MySQLTest.MySQLAccess;
import MySQLTest.MySQLConnection;
import UTIL.STATIC;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;

public class cmdwarn implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        String args0 = args[0];
        if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)){
            String user_id = event.getMessage().getMentionedUsers().get(0).getId();
            String reporter_id = event.getAuthor().getId();
            String reason = event.getMessage().getContentDisplay().replace(STATIC.PREFIX, "").replace("warn", "").replace(args0, "");
           MySQLConnection.getInstance().createStatement("insert into guilds (id, owner_name, owner_discriminator, owner_id, name, users, prefix) values ('123', 'oskar', '1234', '5678', 'oskars guild', '10', '-')").executeBatch();
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
*/
