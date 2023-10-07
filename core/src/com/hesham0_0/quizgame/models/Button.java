package com.hesham0_0.quizgame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StringBuilder;

public class Button extends Actor {
    private final Texture buttonDrawable;
    private Label label;
    private final float width;
    private float virtualWidth;
    private final float height;

    public Button(float x, float y, float virtualWidth, String labelText) {
        this.buttonDrawable = new Texture("button.png");
        this.virtualWidth = virtualWidth;
        this.width = virtualWidth * 4 / 5;
        this.height = width * buttonDrawable.getHeight() / buttonDrawable.getWidth();
        this.setBounds(x - (this.width / 1.45f), y - (this.height / 2f), this.width, this.height);
        setLabel(labelText);
    }

    public Button(float x, float y, float virtualWidth, Texture texture) {
        this.buttonDrawable = texture;
        this.virtualWidth = virtualWidth;
        this.width = virtualWidth * 1 / 5;
        this.height = width * buttonDrawable.getHeight() / buttonDrawable.getWidth();
        this.setBounds(x - (this.width / 1.45f), y - (this.height / 2f), this.width, this.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(buttonDrawable, getX(), getY(), getWidth(), getHeight());
        if (label != null) {
            label.draw(batch, parentAlpha);
        }
    }

    public boolean isClicked(float x, float y) {
        return getX() + getWidth()/2 <= x && x <= getX() + getWidth() && getY() <= y && y <= getY() + getHeight();
    }

    public void setLabel(String labelText) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (virtualWidth / 40);
        parameter.minFilter = Texture.TextureFilter.Linear;
        BitmapFont customFont = generator.generateFont(parameter);
        generator.dispose();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = Color.BLACK;
        labelStyle.font = customFont;
        this.label = new Label(labelText, labelStyle);
        this.label.setAlignment(Align.center);
        this.label.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public String getText() {
        return label.getText().toString();
    }
}
