package gamemodes;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import gui.GameView;
import game_objects.GameObject;

import java.util.Random;
import java.util.LinkedList;

public class Bonus_Malus extends GameObject implements Gamemode{
    
    private Circle circle;

    private double size;
    private boolean est_apparu;
    private LinkedList<gamemodes.Gamemode> list;
    private LinkedList<gamemodes.Gamemode> active_gamemodes;
    private int decompte = 3000;
    private boolean finito = false;


    public Bonus_Malus(Pane root, LinkedList<gamemodes.Gamemode> gamemode_list){
	super();
	list = gamemode_list;
	active_gamemodes = new LinkedList<gamemodes.Gamemode>();
        
	circle = new Circle();
	root.getChildren().add(circle);
	
	size = 7;
    }



    public void reset() {
	set_x(-size);
	set_y(-size);
	finito = false;
	est_apparu = false;
	decompte = 3000;
	active_gamemodes.clear();
    }
    
    public void on_key_pressed(KeyCode key) {
    }
    
    public void on_key_released(KeyCode key) {
    }

    private static int un_ou_moins_un(){
	Random r = new Random();
	int n = r.nextInt(2);
	if (n==0) { return -1; }
	return 1;
    }

    public void add_gamemode(){
	Random r = new Random();
	if(list.size()==0){return;}
	int n = r.nextInt(list.size());
	System.out.println(n);
	active_gamemodes.add(list.get(n));
    }

    public boolean update(model.Court c, double deltaT) {
	//if(finito){return;}
	//if(list.size() == active_gamemodes.size()){finito = true; x = -size;y = -size;return;}
	super.update(c,0);
	for (gamemodes.Gamemode gamemode : active_gamemodes) {
	    gamemode.update(c, deltaT);
	}
	
	Random r = new Random();
	decompte --;
	if(!est_apparu && decompte<0){
	    est_apparu = true;
	    super.set_vel((50 + (r.nextInt(30)*un_ou_moins_un()))*un_ou_moins_un(),-50 + r.nextInt(30));
	    super.set_x(c.get_width() / 2);
	    super.set_y(0);	    
	}
	if(est_apparu){
	    super.change_x(super.get_dx() * deltaT);
	    super.change_y(super.get_dy() * deltaT);
	}
	if (super.get_middle_y() < 0) {
	    super.set_y(-super.get_middle_y());
	    super.set_vel(super.get_dx(),Math.abs(super.get_dy()));
	} else if (super.get_middle_y() > c.get_height()) {
	    super.set_y(c.get_height() - (super.get_middle_y() - c.get_height()));
	    super.set_vel(super.get_dx(),-Math.abs(super.get_dy()));
	}
	if(super.get_middle_x()<0){
	    super.set_x(-super.get_middle_x());
	    super.set_vel(Math.abs(super.get_dx()),super.get_dy());
	}
	else if(super.get_middle_x() > c.get_width()){
	    super.set_x(c.get_width() - (super.get_middle_x() - c.get_width()));
	    super.set_vel(-Math.abs(super.get_dx()),super.get_dy());
	}
	if (super.collides(c.get_player_a(),0) || super.collides(c.get_player_b(),0)) {
	    add_gamemode();
	    super.set_x(-size);
	    super.set_y(-size);
	    est_apparu = false;
	    decompte = 1000+r.nextInt(5000);
	} 
	return false;
    }
    
    
    public void render(gui.GameView view, model.Court court) {
	circle.setRadius(size);
	circle.setFill(Color.BLACK);
	circle.setCenterX(super.get_middle_x() * view.get_scale());
	circle.setCenterY(super.get_middle_y() * view.get_scale());
    }


}
