package com.hesham0_0.quizgame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hesham0_0.quizgame.MainGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Quiz Game");
		int screenWidth = Lwjgl3ApplicationConfiguration.getDisplayMode().width;
		int screenHeight = Lwjgl3ApplicationConfiguration.getDisplayMode().height;
		config.setWindowedMode(screenWidth, screenHeight);
		config.setResizable(true);
		config.setMaximized(true);
		new Lwjgl3Application(new MainGame(), config);
	}
}
