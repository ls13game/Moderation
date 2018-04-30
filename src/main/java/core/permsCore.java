package core;

import UTIL.STATIC;
import anderes.Dev;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class permsCore {

        public static int check(MessageReceivedEvent event) {

            for ( Role r : event.getGuild().getMember(event.getAuthor()).getRoles() ) {
                if (Arrays.stream(STATIC.FULLPERMS).parallel().anyMatch(r.getName()::contains))
                    return 2;
                else if (Arrays.stream(STATIC.PERMS).parallel().anyMatch(r.getName()::contains))
                    return 1;
                else if (Dev.isDev(event.getAuthor()))
                    return 1;
                else
                    event.getTextChannel().sendMessage(":warning:  Sorry, " + event.getAuthor().getAsMention() + ", you don't have the permissions to use this command!").queue(msg -> {msg.delete().queueAfter(20, TimeUnit.SECONDS);});
            }
            return 0;
        }

}
