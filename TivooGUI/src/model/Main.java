package model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;


public class Main{
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ParseException{
    	Model model = new Model();
        TivooViewer viewer = new TivooViewer("Tivoo", model);
    }
}
