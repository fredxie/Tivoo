package model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/*
 * Main class
 */
public class Main{
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ParseException{
    	TivooModel model = new TivooModel();
        TivooViewer viewer = new TivooViewer("Tivoo", model);
    }
}
