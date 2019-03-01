package dal;

import java.util.*;
import java.security.SecureRandom;

public class PassGen {

    private SecureRandom randomize;
    private ArrayList uppercharacters;
    private ArrayList lowercharacters;
    private ArrayList specialcharacters;
    private ArrayList numerials;

    public PassGen() {

        randomize = new SecureRandom();
        uppercharacters = new ArrayList<>();
        lowercharacters = new ArrayList<>();
        specialcharacters = new ArrayList<>();
        numerials = new ArrayList<>();

        for (int i = 65; i <= 90; i++) {
            //Adding uppercase letters
            uppercharacters.add((char) i);
            //Adding lowercase letters
            lowercharacters.add((char) (i + 32));
        }

        //Adding numerials
        for (int i = 48; i <= 57; i++) {
            numerials.add((char) i);
        }
        //Follwing code adds selected special characters
        specialcharacters.add((char) 33);
        specialcharacters.add((char) 43);
        specialcharacters.add((char) 45);
        specialcharacters.add((char) 46);
        specialcharacters.add((char) 61);
        specialcharacters.add((char) 63);
        specialcharacters.add((char) 95);

        //Shuffling the arraylists in a randomized order.
        Collections.shuffle(uppercharacters);
        Collections.shuffle(lowercharacters);
        Collections.shuffle(specialcharacters);
        Collections.shuffle(numerials);
    }

    //Methods that puts the characters in each arraylist in a randomized order
    public char randomUpperChar() {
        return (char) uppercharacters.get(randomize.nextInt(uppercharacters.size()));}

    public char randomLowerChar() {
        return (char) lowercharacters.get(randomize.nextInt(lowercharacters.size()));}

    public char randomSpecial() {
        return (char) specialcharacters.get(randomize.nextInt(specialcharacters.size()));}

    public char randomNum() {
        return (char) numerials.get(randomize.nextInt(numerials.size()));}

    public String passBuild() {

        //Receives our arraylists that create the restrictions for the password.
        PassGen pg = new PassGen();

        //StringBuilders created to receive the full string from an arraylist
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        //For-loop that adds 2 characters for each restriction to a single string
        for (int i = 0; i < 2; i++) {
            sb.append(pg.randomUpperChar()+" ");
            sb.append(pg.randomLowerChar()+" ");
            sb.append(pg.randomNum()+ " ");
            sb.append(pg.randomSpecial()+ " ");
        }

        //The following code splits the newly created string up in each
        //character and adds it to a arraylist which shuffle them
        //in a randomized order.
        String num = sb.toString();
        String str[] = num.split(" ");
        List<String> al = new ArrayList<>();
        al = Arrays.asList(str);
        Collections.shuffle(al);

        //Builds the string from the new arraylist.
        for (String s : al)
        {
            sb2.append(s);
        }

        //Returning the full randomized string
        return sb2.toString();
    }
}