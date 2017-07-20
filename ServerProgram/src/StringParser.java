/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *status variable declaration -
 * 0-screenName:Msg
 * 1-screenName:/help
 * 2-screenName:/exit
 * 3-screenName:/pm toUser Msg
 * 5 - none of the above format
 * 4 - Server Configuration
 * @author Rishabh Rishu
 */
import java.util.*;
public class StringParser {
    /*variable declaration*/
    private String incomingString;
    private String screenName;
    private String msgToSend;
    private int status;
    private String toUser;
    /*end variable declaration*/
    
    public void setIncomingString(String s){
        incomingString = s;
    }
    public String getMsgToSend(){
        return msgToSend;
    }
    public String getScreenName(){
        return screenName.substring(0,screenName.length()-1);
    }
    public int getStatus(){
        return status;
    }
    public String getToUser(){
        return toUser;
    }
    public void tokenize(){
        String restOfMsg = "";
        StringTokenizer st = new StringTokenizer(incomingString);
        screenName = st.nextToken(":");
        screenName = screenName;
        restOfMsg = incomingString.substring(screenName.length()+1);
        while (restOfMsg.startsWith(" ")) /*to remove accidental whitespaces added by user*/
                restOfMsg = restOfMsg.substring(1);
        if(restOfMsg.startsWith("/")){
            if(restOfMsg.startsWith("/help")){
                status = 1;
                msgToSend="Type '/exit' to quit from the program\nType '/pm screenName message' to send a personal message to a particular user\n";
            }
            else if(restOfMsg.startsWith("/exit")){
                status = 2;
                msgToSend="Good bye "+screenName;
            }
            else if(restOfMsg.startsWith("/pm")){
                status = 3;
                restOfMsg = restOfMsg.substring(4);
                StringTokenizer s = new StringTokenizer(restOfMsg," ");
                toUser = s.nextToken();
                msgToSend = restOfMsg.substring(toUser.length()+1);
            }
            else if (restOfMsg.startsWith("/ServerCheck")){
                status = 4;
                msgToSend = screenName + " has joined the chat room";
            }
            else {
                status = 5;
                msgToSend = "Please follow proper format";
            }
        }
        else{
            status = 0;
            msgToSend = restOfMsg;
        }
    }   
}