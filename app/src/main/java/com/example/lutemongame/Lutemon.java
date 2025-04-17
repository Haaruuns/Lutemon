package com.example.lutemongame;

import android.widget.ImageView;

public class Lutemon {
    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience = 0;
    protected int health;
    protected int maxHealth;
    protected int id;
    private static int idCounter = 0;

    protected int wins;
    protected int losses;
    protected ImageView picture;

    public Lutemon(String name, String color, int attack, int defense, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.id = idCounter++;
        this.experience = 0;
        this.wins = 0;
        this.losses = 0;
    }





}
