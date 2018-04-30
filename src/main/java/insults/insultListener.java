package insults;


import com.sun.jna.StringArray;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import anderes.report;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/*
*
 * Created by Oskar
 * on 19.04.2018
 * for Moderation
 * github.com/oskardevkappa/

*/



public class insultListener extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {


        String msg = event.getMessage().getContentDisplay().toLowerCase();
        try {

            File f = new File("insultList.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while( (line = br.readLine()) != null ) {
                String[] args =  msg.split(" ");
                for (int i = 0; i < args.length; i++){
                    if(args[i].equals(line)){

                    report rep = new report();
                    rep.create(event, 1);
                    }
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }


}

