import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.security.SecureRandom;

public class PassGen {

    private SecureRandom randomize;
    private ArrayList characters;

    public PassGen(){
        randomize = new SecureRandom();
        characters = new ArrayList<>();

        for(int i =65; i <=90; i++){
            //Adding uppercase letters
            characters.add((char) i);
            //Adding lowercase letters
            characters.add((char) (i + 32));
        }
        for(int i=48; i<=57; i++){
            characters.add((char)i);
        }
        characters.add((char)33);
        characters.add((char)43);
        characters.add((char)45);
        characters.add((char)46);
        characters.add((char)61);
        characters.add((char)63);
        characters.add((char)95);


        Collections.shuffle(characters);
    }

    public char randomChar() {
        return (char) characters.get(randomize.nextInt(characters.size()));
    }









}