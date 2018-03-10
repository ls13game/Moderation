package listeners;

import UTIL.STATIC;
import core.commandHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.IOException;
import java.text.ParseException;

public class commandListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getContentDisplay().startsWith(STATIC.PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
            try {
                commandHandler.handleCommand(commandHandler.parse.parser(event.getMessage().getContentDisplay(), event));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}
