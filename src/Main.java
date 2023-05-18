import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String args[]){

        FNCD f1 = new FNCD("North");
        FNCD f2 = new FNCD("South");

        Tracker t = Tracker.GetInstance(); // only one tracker for the whole simulation. Tracker and logger will subscribe to the events of both FNCDs. Eager instantiation since only one, and we know that.
        f1.AddObs(t);
        f2.AddObs(t);

        final int DAYS = 31;
        int currentDay = 1;
        String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}; // for checking which day it is based on DAYS (since closed on Sunday and more buyers on weekend)

        // loop through the 30 days
        while (currentDay <= DAYS) {

            String day = days[currentDay % 7]; // string for day of the week
            Logger l = Logger.GetInstance(); // new logger every day because will create a log file for each day. This should be lazy instantiation because based on each day as it happens

            f1.AddObs(l); // add the logger for the day to concrete subjects lists
            f2.AddObs(l);

            f1.Open();
            f2.Open();
            f1.Wash();
            f2.Wash();
            f1.Repair();
            f2.Repair();
            if(day == "Sunday" || day == "Wednesday"){ // do it after opening / washing / repair so we have a better chance of having cars to use
                f1.Race();
                f2.Race();
            }
            if(currentDay != 31){
                f1.Sell();
                f2.Sell();
            }
            else{
                // begin command pattern here, has 8 choices. This while loop will act as the broker... where we just put in one command at a time, create that concrete CommandAction
                Actions.UserBuy(f1, f2); // implement command in here?
            }
            f1.End();
            f2.End();

            t.Display(currentDay); // running total of amount made by staff and FNCD

            f1.RemoveObs(l); // remove logger from list
            f2.RemoveObs(l);

            currentDay++;
        }

    }


}
