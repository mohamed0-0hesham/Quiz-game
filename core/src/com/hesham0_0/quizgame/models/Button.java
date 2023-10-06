package com.hesham0_0.quizgame.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class Button extends com.badlogic.gdx.scenes.scene2d.ui.Button {
    private TextureRegionDrawable buttonDrawable;
    private Label label;
    private int width;
    private int height;

    public Button(float x, float y, int width, int height, String labelText) {
        this.width = width;
        this.height = height;
        this.buttonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("button.png")));
        this.setBounds(x - (width / 1.45f), y - (height / 2f), width, height);
        setLabel(labelText);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        buttonDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        label.draw(batch, parentAlpha);
    }

    public boolean isClicked(float x, float y) {
        return getX() <= x && x <= getX() + getWidth() && getY() <= y && y <= getY() + getHeight();
    }

    public void setLabel(String labelText) {
        // Create a Label for the button's text
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(); // Use your desired font
        this.label = new Label(labelText, labelStyle);
        this.label.setAlignment(Align.center);
        this.label.setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
