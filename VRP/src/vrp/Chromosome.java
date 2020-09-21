package vrp;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.shape.Circle;

public class Chromosome {
	ArrayList<Circle>genes;
	ArrayList<Client>clients;
	ArrayList<Vehicule>vehicules;
	Depot depot;
	int nombreVihicules;
	int distances;
	double fitness;
	static Random random=new Random();
	
	public Chromosome(ArrayList<Circle>genes){
		this.genes=new ArrayList<Circle>(genes);
		this.distance();
		this.fitness=this.fitness();
	}
	
	public Chromosome(ArrayList<Client>clients,ArrayList<Vehicule>vehicules,Depot depot) {
		this.clients=new ArrayList<Client>(clients);
		this.vehicules=new ArrayList<Vehicule>(vehicules);
		this.depot=depot;
		this.distances=0;
		this.genes=generateChromosome();
		this.distance();
		this.fitness=fitness();
	}
	
	ArrayList<Circle>generateChromosome(){
		ArrayList<Circle>genes=new ArrayList<Circle>();
		int capacite=this.vehicules.get(0).capacite;
		genes.add(depot);
		while (this.clients.size()!=0) {
			int index=random.nextInt(this.clients.size());
			if(capacite-this.clients.get(index).demand>=0) {
				genes.add(this.clients.get(index));
				capacite=capacite-this.clients.get(index).demand;
				this.clients.remove(index);
			}else {
				genes.add(depot);
				capacite=this.vehicules.get(0).capacite;
			}
		}
		genes.add(depot);
		return genes;
	}
	
	void distance() {
		for (int i = 0; i <this.genes.size()-1; i++) {
			this.distances+=distancesEcl(Double.parseDouble(genes.get(i+1).toString().split(",")[2]),Double.parseDouble(genes.get(i).toString().split(",")[2]),Double.parseDouble(genes.get(i+1).toString().split(",")[3]),Double.parseDouble(genes.get(i).toString().split(",")[3]));
			if(this.genes.get(i).toString().startsWith("D")) {this.nombreVihicules++;}
		}
	}
	
	double fitness() {
		return 0.33*this.distances+0.67*this.nombreVihicules;
	}
	
	double distancesEcl(double x2,double x1,double y2,double y1) {
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
}
