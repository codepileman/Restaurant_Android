package com.hfad.bitsandpizzas;

public class Pizza {
    private String name;
    private int imageResourceId;
    private String description;
    private double price;

    public static final Pizza[] pizzas = {
            new Pizza("Diavolo", R.drawable.diavolo,"Marinated Rib-Eye, Garlic, Mozzarella, Scallions, Serrano Chiles, Kimchi, Kewpie Mayo & Shichimi",12.99),
            new Pizza("Funghi", R.drawable.funghi,"Squid Ink Dough, Clams, Mussles, Octopus, Scallops, Shrimp, Calamari, Tomatoes, Garlic, & Parmigiano",15.99)
    };

    private Pizza(String name, int imageResourceId, String description,double price) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getDescription() { return description;}

    public double getPrice() {return price;}
}