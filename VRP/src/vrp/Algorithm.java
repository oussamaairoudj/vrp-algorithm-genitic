package vrp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Algorithm implements Runnable{
    ArrayList<Client>clients=new ArrayList<Client>();
	ArrayList<Vehicule>vehicules=new ArrayList<Vehicule>();
	ArrayList<Chromosome>generations;
	Depot depot;
	Controller controller;
	int population;
	static Random random=new Random();
	ArrayList<Line> lines=new ArrayList<Line>(); 
	Chromosome chromosome;
	static Chromosome chromosomet;
	int cpt;
	String time;
	
	public Algorithm(Controller controller,ArrayList<Client>clients,ArrayList<Vehicule>vehicules,Depot depot,int population) {
		this.controller=controller;
		this.clients=clients;
		this.vehicules=vehicules;
		this.depot=depot;
		this.population=population;
		controller.population.setText("Population:"+population);
		this.generations=new ArrayList<Chromosome>(initialPopulation(clients, vehicules, population));
	    this.cpt=0;
	}
	
	ArrayList<Chromosome>initialPopulation(ArrayList<Client>clients,ArrayList<Vehicule>vehicules,int population ){
		ArrayList<Chromosome>initialPopulation=new ArrayList<Chromosome>();
		for (int i = 0; i < population; i++) {
		    initialPopulation.add(new Chromosome(clients,vehicules, depot));
		}
		return initialPopulation;
	}
	
	@SuppressWarnings("deprecation")
	void geniticAlgorithme(int generation) {
			for (int i = 0; i < generation; i++) {
		    	generations.sort(Comparator.comparingDouble(Chromosome::fitness));
		    	int j=0;
				while (j<population-1) {
					int k=j+1;
					while (k<population) {
						if(generations.get(j).genes.equals(generations.get(k).genes)) {
							generations.set(k,mutation(generations.get(k).genes, 1));
						}else {
							k++;
						}
					}j++;
				}
				if(generations.get(0).equals(chromosome))cpt++;
				else{time=controller.clock.getText();cpt=0;}
				paint(generations.get(0),i);
				for (int k = 0; k < population; k++) {
					crossover(generations.get(selection(generations, population)),generations.get(selection(generations, population)));
				}
				if(i==200)generations.add(mutation(valid(chromosomet.genes), 1));
				if(cpt==400) {controller.clock.setText(time);;controller.prograss.setVisible(false);controller.temp.stop();break;}
			}
	}
	
	Chromosome mutation(ArrayList<Circle>genes,double d) {
		float mutate=random.nextFloat();
		ArrayList<Circle> chromosomemut=new ArrayList<Circle>(genes);
		if(mutate<d) {
			Collections.swap(chromosomemut,random.nextInt(chromosomemut.size()-1)+1,random.nextInt(chromosomemut.size()-1)+1);
		   return new Chromosome(valid(chromosomemut));
		}else {return new Chromosome(chromosomemut);}
	}
	
	void crossover(Chromosome chromosome1,Chromosome chromosome2) {
		int x=random.nextInt(Math.min(chromosome1.genes.size(),chromosome2.genes.size()));
		ArrayList<Circle>genchromosome1=new ArrayList<Circle>(chromosome1.genes);
		ArrayList<Circle>genchromosome2=new ArrayList<Circle>(chromosome2.genes);
		ArrayList<Circle>genechild1=new ArrayList<Circle>(genchromosome1.subList(0, x));
		ArrayList<Circle>genechild2=new ArrayList<Circle>(genchromosome2.subList(0, x));
		genchromosome1.removeAll(genechild2);
		genchromosome2.removeAll(genechild1);
		genechild1.addAll(genchromosome2);
		genechild2.addAll(genchromosome1);
		this.generations.add(mutation(valid(genechild1),0.3));
		this.generations.add(mutation(valid(genechild2),0.3));
	}
	
	ArrayList<Circle>valid(ArrayList<Circle>genes){
		ArrayList<Circle>gen=new ArrayList<Circle>(genes);
		int j=1;
		while (j<gen.size()) {
				if(gen.get(j).toString().startsWith("D")) {
					gen.remove(j);
				}else {
					j++;
				}
		}
		int capacite=this.vehicules.get(0).capacite;
		Client client=null;
		for (int i = 1; i < gen.size(); i++) {
			client=(Client)gen.get(i);
			if(capacite-client.demand>=0) {
				capacite-=client.demand;
			}else {
				gen.add(i,depot);
				capacite=this.vehicules.get(0).capacite;
			}
		}
		gen.add(depot);
		return gen;
	}
	
	
	int selection(ArrayList<Chromosome>chromosomes,int population) {
		double tfitness=0;
		for (int i = 0; i < population; i++) {
			tfitness+=chromosomes.get(i).fitness;
		}
		int index=random.nextInt((int)tfitness);
		double ifitness=0;
		for (int i = 0; i <population; i++) {
			ifitness+=chromosomes.get(i).fitness;
			if(ifitness>=index) {return i;}
		}
		return -1;
	}
	
	void paint(Chromosome chromosome, int k) {
    	Platform.runLater(()->{
    		Paint color=null;
    		controller.generation.setText("Generation:"+k);	
    		controller.resvih.setText("Vihicule:"+chromosome.nombreVihicules);
        	controller.resdis.setText("Distance:"+chromosome.distances);
        	String chemin="0";
        	for (int i = 1; i <chromosome.genes.size(); i++) {
				chemin+=","+chromosome.genes.get(i).toString().split(",")[1];
			}
        	controller.chemin.setText("path:"+chemin);
        	controller.content.getChildren().removeAll(lines);
        	lines.clear();
		for (int i = 0; i < chromosome.genes.size()-1; i++) {
			if(chromosome.genes.get(i).equals(depot)) {
				color=Color.rgb(random.nextInt(200), random.nextInt(200), random.nextInt(200)); 
			}
			Line line=new Line(chromosome.genes.get(i).getCenterX(),chromosome.genes.get(i).getCenterY(),chromosome.genes.get(i+1).getCenterX(),chromosome.genes.get(i+1).getCenterY());
			line.setStroke(color);
		    controller.content.getChildren().add(line);
		    line.toBack();
		    lines.add(line);
		}this.chromosome=chromosome;
    	});
	}

	@Override
	public void run() {
		this.geniticAlgorithme(Integer.MAX_VALUE);
	}

}
