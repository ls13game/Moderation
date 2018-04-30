package listeners;

import UTIL.STATIC;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;

import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static core.Main.jda;


/**
 * Created by Oskar
 * on 19.03.2018
 * github.com/oskardevkappa/
 */

public class gameHandler {


    private static Timer t;
    static int count = 0;


    static public void startTimer() {
        t = new Timer();
        t.schedule(new TimerTask() {

            public void run() {

                if (count == 6) count = 1;
                switch (count) {
                    case 1:
                        jda.getPresence().setGame(Game.playing("with " + AllMembers(jda) + " Users!"));
                        break;
                    case 2:
                        jda.getPresence().setGame(Game.playing("on " + jda.getGuilds().size() + " Guilds!"));
                        break;

                    case 3:
                        jda.getPresence().setGame(Game.watching("out for Translators."));
                        break;
                    case 4:
                        jda.getPresence().setGame(Game.playing(STATIC.PREFIX + "help"));
                        break;
                    case 5:
                        jda.getPresence().setGame(Game.watching("to his new Logo"));
                }count ++;
            }
        }, Date.from(Instant.now()), 100000);
    }



    static public void stop(){
        t.purge();
        System.out.println("Timer gestoppt");
    }
    static public void start(){
        startTimer();
        System.out.println("Timer gestartet");
    }

    static private int AllMembers(JDA jda){
        int members = jda.getUsers().size();
        int membersx = 0;

        for (Guild gu:jda.getGuilds() ) {
            membersx += gu.getMembers().size();
            //members = membersx - jda.getGuildById("264445053596991498").getMembers().size();
        }
        return members;
    }

    public static void CustomGame(String input){
        stop();
        jda.getPresence().setGame(Game.playing(input));

    }


}

