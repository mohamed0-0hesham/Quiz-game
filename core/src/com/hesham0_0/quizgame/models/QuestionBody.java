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

public class QuestionBody extends Actor {
    private final Texture buttonDrawable;
    private Label label;
    private float width;
    private float height;
    private float virtualWidth;

    public QuestionBody(float x, float y, float width, float height, String labelText) {
        this.buttonDrawable= new Texture("main_question.png");
        this.virtualWidth=width;
        this.width = width;
        this.height = height;
        this.setBounds(x - (width / 2f), y - (height / 2f), width, height);
        setLabel(labelText);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(buttonDrawable, getX(), getY(), getWidth(), getHeight());
        label.draw(batch, parentAlpha);
    }

    public void setLabel(String labelText) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (virtualWidth / 40);
        parameter.minFilter = Texture.TextureFilter.Linear;
        BitmapFont customFont = generator.generateFont(parameter);
        generator.dispose();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor= Color.BLACK;
        labelStyle.font = customFont;
        this.label = new Label(labelText, labelStyle);
        this.label.setAlignment(Align.center);
        this.label.setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
