/*
package listeners;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class reactionListener extends ListenerAdapter {
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        String message = event.getMessageId();
        if (event.getUser().equals(event.getJDA().getSelfUser())) {
            return;
        }
        if (event.getTextChannel().getMessageById(message).complete().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (event.getTextChannel().getName().equals("reports")) {
                event.getTextChannel().getMessageById(message).complete().editMessage("Dieser Report wurde bereits erfolgreich bearbeitet von " + event.getMember().getAsMention()).complete();
                event.getTextChannel().getMessageById(message).complete().clearReactions().complete();
                return;
            }
            if (event.getTextChannel().getName().equals("support")) {
                event.getTextChannel().getMessageById(message).complete().editMessage("Diese Supportanfrage wurde bearbeitet").queue();
                event.getTextChannel().getMessageById(message).complete().clearReactions().complete();
                return;
            }
        }else {
            return;
        }
    }
}
*/
