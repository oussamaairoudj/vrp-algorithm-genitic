package vrp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class Open{
	
	Scanner scanner;
	int optimomloc [][]= {{0, 23, 26, 28, 18, 19, 15, 16, 14, 12, 11, 10, 0, 43, 41, 40, 59, 60, 58, 56, 53, 54, 55, 68, 0, 91, 89, 88, 85, 95, 97, 100, 99, 2, 0, 90, 87, 86, 84, 94, 92, 93, 96, 98, 3, 5, 0, 75, 1, 4, 6, 9, 8, 7, 0, 20, 21, 22, 25, 27, 30, 13, 17, 0, 47, 49, 46, 44, 57, 50, 52, 33, 32, 0, 24, 29, 31, 35, 37, 38, 39, 36, 34, 51, 48, 45, 42, 0, 67, 69, 66, 64, 61, 72, 74, 62, 63, 65, 0, 83, 82, 81, 80, 79, 77, 73, 70, 71, 76, 78, 0}
	,{0, 21, 20, 22, 13, 15, 12, 14, 16, 19, 17, 18, 26, 23, 25, 9, 11, 10, 8, 93, 90, 77, 85, 71, 70, 73, 80, 79, 76, 81, 78, 82, 83, 84, 86, 88, 91, 0, 5, 75, 2, 1, 99, 100, 97, 92, 94, 95, 98, 7, 3, 4, 89, 87, 96, 61, 72, 64, 49, 55, 40, 57, 54, 53, 56, 58, 60, 59, 46, 45, 42, 47, 43, 41, 69, 68, 65, 44, 51, 50, 52, 0, 24, 30, 28, 34, 36, 37, 39, 38, 35, 31, 6, 32, 33, 29, 27, 48, 74, 62, 66, 67, 63, 0}
	,{0, 26, 12, 24, 68, 3, 77, 76, 1, 30, 70, 31, 69, 27, 0, 82, 46, 47, 36, 49, 64, 20, 66, 65, 71, 50, 28, 0, 52, 7, 88, 62, 11, 63, 90, 32, 10, 19, 48, 8, 45, 17, 84, 60, 6, 0, 89, 96, 99, 61, 16, 38, 14, 42, 43, 15, 57, 87, 13, 58, 0, 79, 33, 81, 51, 9, 35, 34, 78, 29, 80, 54, 55, 4, 72, 40, 0, 97, 92, 37, 100, 44, 86, 91, 85, 59, 94, 0, 95, 98, 93, 5, 83, 18, 0, 53, 2, 73, 74, 41, 22, 75, 23, 67, 25, 39, 56, 21, 0}
	,{0,13, 58, 40, 87, 97, 37, 86, 38, 44, 14, 43, 15, 57, 41, 22, 75, 56, 4, 21, 73, 72, 74, 23, 67, 39, 25, 55, 54, 29, 24, 68, 80, 77, 3, 1, 70, 30, 31, 88, 10, 90, 32, 20, 66, 65, 35, 71, 9, 51, 81, 78, 34, 79, 33, 50, 76, 12, 26, 28, 53, 0, 27, 69, 52, 7, 48, 82, 18, 83, 60, 84, 89, 6, 99, 93, 85, 16, 91, 100, 42, 2, 95, 94, 96, 59, 92, 98, 61, 5, 17, 45, 8, 46, 47, 36, 19, 49, 64, 63, 11, 62,0}
	,{0,55, 79, 78, 47, 17, 16, 15, 12, 82, 69, 0, 90, 98, 99, 57, 52, 74, 9, 73, 8, 46, 45, 5, 3, 1, 0, 42, 44, 43, 40, 37, 36, 35, 39, 38, 72, 81, 0, 95, 62, 50, 34, 31, 33, 28, 26, 27, 29, 30, 32, 63, 85, 0, 80, 96, 71, 93, 94, 67, 92, 91, 66, 0, 68, 70, 100, 88, 53, 10, 13, 75, 97, 59, 0, 2, 4, 6, 7, 60, 14, 11, 0, 51, 20, 22, 24, 49, 19, 25, 77, 58, 83, 0, 65, 64, 56, 84, 41, 54, 61, 0, 76, 89, 23, 21, 48, 18, 86, 87,0}
	,{0, 90, 98, 53, 10, 11, 9, 13, 15, 16, 17, 47, 14, 12, 78, 73, 79, 6, 4, 46, 8, 7, 60, 88, 55, 69, 82, 99, 57, 83, 66, 65, 52, 86, 74, 87, 59, 97, 75, 58, 77, 25, 23, 21, 48, 18, 19, 49, 20, 64, 56, 85, 62, 95, 94, 93, 96, 92, 91, 80, 0, 68, 61, 41, 42, 44, 39, 38, 37, 36, 35, 40, 43, 1, 3, 5, 45, 2, 100, 70, 81, 54, 72, 71, 67, 34, 33, 32, 30, 28, 26, 27, 29, 31, 50, 63, 89, 76, 51, 84, 22, 24, 0}};
	
	@SuppressWarnings("deprecation")
	public Open(Controller controller) {
		try {
		    FileChooser chooser =new FileChooser();
		    chooser.setInitialDirectory(new File("./instances-vrp"));
		    chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("VRP", "*.txt"));
		    File file=chooser.showOpenDialog(null);
		    controller.file.setText("File:"+file.getName());
			scanner=new Scanner(file);
			if(controller.algorithm!=null) {controller.clock.setVisible(false);controller.algorithm.stop();Controller.pause=false;
			controller.algorithm=null;controller.temp.stop();controller.temp=null;}
			controller.prograss.setVisible(false);
			controller.content.getChildren().clear();
			controller.population.setText("");
			controller.resdis.setText("");
			controller.chemin.setText("path");
			controller.resvih.setText("");
			controller.generation.setText("");
			controller.content.setTranslateX(180);
			controller.content.setTranslateY(150);
			controller.content.setScaleX(4.5);
			controller.content.setScaleY(4.5);
			Controller.clients=new ArrayList<Client>();
			Controller.vehicules=new ArrayList<Vehicule>();
			int cpt=0;
			while (scanner.hasNextLine()) {
				String valeur[]=scanner.nextLine().split("\\s+");
				if(cpt==4) {
					controller.vehicle.setText("Vehicle:"+valeur[1]);
					controller.capacity.setText("Capacity:"+valeur[2]);
					for (int i = 0; i < Integer.parseInt(valeur[1]); i++) {
						Controller.vehicules.add(new Vehicule(Integer.parseInt(valeur[2])));
					}
				}else {
					if(cpt>8) {
						if(cpt==9) {
							Controller.depot=new Depot(Integer.parseInt(valeur[1]), Integer.parseInt(valeur[2]), Integer.parseInt(valeur[3]));
							controller.content.getChildren().add(Controller.depot );
							}else {
							Controller.clients.add(new Client(Integer.parseInt(valeur[1]), Integer.parseInt(valeur[2]), Integer.parseInt(valeur[3]), Integer.parseInt(valeur[4])));	
							controller.content.getChildren().add(Controller.clients.get(cpt-10));
						}
					}
				}
			cpt++;	
			}
			controller.client.setText("Customer:"+(cpt-10));
			switch (file.getName()) {
			case "c101.txt":case "c102.txt":case "c103.txt":case "c104.txt":case "c105.txt":case "c106.txt":case "c107.txt":case "c108.txt":case "c109.txt" :
				optimisation(0);
				break;
			case "c201.txt":case "c202.txt":case "c203.txt":case "c204.txt":case "c205.txt":case "c206.txt":case "c207.txt":case "c208.txt":
				optimisation(1);
				break;
			case "r101.txt":case "r102.txt":case "r103.txt":case "r104.txt":case "r105.txt":case "r106.txt":case "r107.txt":case "r108.txt":case "r109.txt":case "r110.txt":case "r111.txt":case "r112.txt":
				optimisation(2);
				break;
			case "r201.txt":case "r202.txt":case "r203.txt":case "r204.txt":case "r205.txt":case "r206.txt":case "r207.txt":case "r208.txt":case "r209.txt":case "r210.txt":case "r211.txt":
				optimisation(3);
				break;		
			case "rc101.txt":case "rc102.txt":case "rc103.txt":case "rc104.txt":case "rc105.txt":case "rc106.txt":case "rc107.txt":case "rc108.txt":
				optimisation(4);
				break;	
			case "rc201.txt":case "rc202.txt":case "rc203.txt":case "rc204.txt":case "rc205.txt":case "rc206.txt":case "rc207.txt":case "rc208.txt":
				optimisation(5);
				break;	
			default:
				break;
			}
		} catch (FileNotFoundException e) {
			
		}	
	}
  void optimisation(int j) {
	  ArrayList<Circle> circles=new ArrayList<Circle>();
		for (int i = 0; i < optimomloc[j].length; i++) {
			if(optimomloc[j][i]==0)circles.add(Controller.depot);
			else circles.add(Controller.clients.get(optimomloc[j][i]-1));
		}Algorithm.chromosomet=new Chromosome(circles); 
  }
}
