package vrp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller implements Initializable {
	double x,y;
	@FXML Text clock,chemin;
	@FXML Group content;
	@FXML Button file,vehicle,capacity,client,population,resvih,resdis,generation;
	@FXML ProgressIndicator prograss;
	@FXML Pane graph;
	@FXML AnchorPane title,in,out,tool,solution;
	Thread algorithm;
    Thread temp;
	
	static ArrayList<Client>clients;
	static ArrayList<Vehicule>vehicules;
	static Depot depot;
	static boolean pause;
	
	@FXML
	 void exit(MouseEvent e) {
		 System.exit(0);
	 }
	
	@SuppressWarnings("deprecation")
	@FXML
	void pause(MouseEvent e) {
		if(algorithm!=null) {
			if(!pause) {
				prograss.setVisible(false);
				algorithm.suspend();;
				temp.suspend();
				pause=true;
			}
		}
	}
	
	@FXML
	void open(MouseEvent e) {
		try {new Open(this);} catch (Exception e2) {}
	}
	
	@FXML
	void pressed(MouseEvent e) {
		x=e.getSceneX();
		y=e.getSceneY();
	}
	
	@FXML
	void dragged(MouseEvent e) {
		Main.stage.setX(Main.stage.getX()-(x-e.getSceneX()));
		Main.stage.setY(Main.stage.getY()-(y-e.getSceneY()));
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	void vrp(MouseEvent e) {
		prograss.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
		if(!pause && algorithm==null) {
			prograss.setVisible(true);
		algorithm=new Thread(new Algorithm(this, clients, vehicules, depot,100));
		algorithm.start();
		temp=new Thread(new Runnable() {
			@Override
			public void run() {
				clock.setVisible(true);
				int t=0,s=0,m=0;
				while (true) {
					try {
						Thread.sleep(10);
						t++;
						if(t==100) {s++;t=0;}
						if(s==60) {m++;s=0;}
						paint(m, s, t);
					} catch (Exception e2) {}
				}
			}
			void paint(int m,int s,int t) {Platform.runLater(()->{clock.setText(m+":"+s+":"+t);});}
		});temp.start(); 
		}else {
			prograss.setVisible(true);
		    algorithm.resume();
		    temp.resume();
		    pause=false;
		}
	}
	
	@SuppressWarnings("deprecation")
	@FXML
	void clear(MouseEvent e) {
		if(algorithm!=null) {algorithm.stop();temp.stop();algorithm=null;
		temp=null;};
		content.getChildren().clear();
		file.setText("");
		vehicle.setText("");
		capacity.setText("");
		client.setText("");
		population.setText("");
		resdis.setText("");
		resvih.setText("");
		generation.setText("");
		pause=false;
		chemin.setText("path");
		prograss.setVisible(false);
		clock.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Main.setborder(graph);
		Main.setborder(title);
		Main.setborder(in);
		Main.setborder(tool);
		Main.setborder(out);
		Main.setborder(solution);
	}

}
