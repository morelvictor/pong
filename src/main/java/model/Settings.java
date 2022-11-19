package model;

import java.util.LinkedList;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import gamemodes.*;

public class Settings {
	public KeyCode left_up;
	public KeyCode left_down;
	public KeyCode right_up;
	public KeyCode right_down;
	public KeyCode stop;
	public KeyCode turbo;
	public Color forground_color;
	public Color background_color;
	public LinkedList<Gamemode> gamemodes;
<<<<<<< HEAD
	public LinkedList<Gamemode> possible_gamemodes;
=======
>>>>>>> master

	public Settings(Pane root) {
		left_up = KeyCode.A;
		left_down = KeyCode.Q;
		right_up = KeyCode.UP;
		right_down = KeyCode.DOWN;
		stop = KeyCode.ESCAPE;
		turbo = KeyCode.SPACE;

		forground_color = Color.BLACK;
		background_color = Color.WHITE;

		LinkedList<Gamemode> bonus_malus_gamemodes = new java.util.LinkedList<gamemodes.Gamemode>();
		bonus_malus_gamemodes.add(new gamemodes.RacketLength(2));
		bonus_malus_gamemodes.add(new gamemodes.RackWTF());
<<<<<<< HEAD

		possible_gamemodes = new LinkedList<Gamemode>();
		possible_gamemodes.add(new gamemodes.RacketLength(2));
		possible_gamemodes.add(new gamemodes.RackWTF());
		possible_gamemodes.add(new gamemodes.Scoreboard_versus(root));
		possible_gamemodes.add(new gamemodes.Acceleration());
		possible_gamemodes.add(new gamemodes.Ia(1, true));
		possible_gamemodes.add(new gamemodes.Ia(1, false));
		possible_gamemodes.add(new gamemodes.Bonus_Malus(root, bonus_malus_gamemodes));

		gamemodes = new LinkedList<>();
=======
		bonus_malus_gamemodes.add(new gamemodes.Vent(root));
		
		gamemodes = new LinkedList<>();
		gamemodes.add(new gamemodes.Bonus_Malus(root, bonus_malus_gamemodes));
		gamemodes.add(new gamemodes.Scoreboard_versus(root));
		gamemodes.add(new CasseBrique(root));
>>>>>>> master
	}
}
