package com.jetbrains;


import com.vaadin.ui.*;


public class chatUI extends VerticalLayout {
    public chatUI()
    {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        final TextField field = new TextField();
        Button button = new Button("Click Meeee");


        this.addComponent(field);
        this.addComponent(button);
    }

}
