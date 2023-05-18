import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observer{

    private static Logger instance = null; // lazy singleton instantiation. We don't create it yet
    public static int logNum = 1; // suffix for the output file
    static FileWriter file; // only one file opened ever
    static BufferedWriter bw;
    // file pointer variable, opens in constructor, called in update, and closes in Close()

    private Logger(){
        try {
            file = new FileWriter("./logs/Logger-" + logNum + ".txt"); // initial file writer
            bw = new BufferedWriter(file);
        }
        catch (IOException ioe){
            System.out.println("Error with file " + ioe.getMessage());
        }
    }

    public static Logger GetInstance(){ // singleton pattern

        if(instance == null){ // lazy instantiation, only now when we know we need it outside of the class do we instantiate it
            instance = new Logger();
        }
        // close previous file before getting the next one?
        try {
            bw.close();
            file = new FileWriter("./logs/Logger-" + logNum + ".txt");
            bw = new BufferedWriter(file);
        }
        catch (IOException ioe){
            System.out.println("Error with file " + ioe.getMessage());
        }
        logNum++; // for opening a new log file for the day

        return instance;
    }

    public void Update(String s, Integer i1, Integer i2){

        // print the line itself, then add to the log file
        if(s != ""){
            System.out.println(s);
            try{
                bw.append(s);
                bw.append("\n");
            }
            catch(IOException ioe){
                System.out.println("Error with file " + ioe.getMessage());
            }
        }
    }

}
