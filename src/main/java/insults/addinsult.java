package insults;

import anderes.Dev;
import commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class addinsult implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        String content = String.join(" ", args);
        if(Dev.isDev(event.getAuthor())){
            {
                FileWriter fw = new FileWriter("insultList.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.newLine();
                bw.write(content);
                bw.close();
            }
            event.getTextChannel().sendMessage("Success").queue();
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
