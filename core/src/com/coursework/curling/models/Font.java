package com.coursework.curling.models;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Collections;

public class Font {

    private int number;

    public Font(int number) {
        this.number = number;
    }

    private ArrayList<Texture> images = null;

    public ArrayList<Texture> images() {

        ArrayList<Texture> digits = new ArrayList<Texture>();
        int temp = number;
        while(temp > 0) {
            int digit = temp % 10;
            temp /= 10;
            Texture texture = new Texture(String.format("%d_blue.png", digit));
            digits.add(texture);
        }

        Collections.reverse(digits);
        images = digits;
        return digits;

    }

    public void dispose(){
        for (Texture image: images){
            image.dispose();
        }
    }
}
