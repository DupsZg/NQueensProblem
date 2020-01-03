package main;

import java.util.Random;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import fitness.MinimizeAttacksFitnessFunction;


public class Main {
	
	public static final int POPULATION_SIZE = 100;
	public static final int NUMBER_OF_EVOLUTIONS = 148;
	public static final int BOARD_SIZE=4;

	public static void main(String[] args) {
		
		Configuration conf = new DefaultConfiguration();
		int[] board=new int[BOARD_SIZE];
		
		board=fillTheBoard(board);
		
		FitnessFunction fitnessFunction = new MinimizeAttacksFitnessFunction(board);

		try {
			//1.Konfiguracija u izvjestaju
			conf.setFitnessFunction(fitnessFunction);
			conf.addGeneticOperator(new CrossoverOperator(conf));
			
			//2.Konfiguracija u izvještaju
			conf.setPreservFittestIndividual(true);
			
			//3.Konfiguracija u izvještaju
			conf.setKeepPopulationSizeConstant(false);

			int chromosomeSize = board.length;

			Gene[] sampleGenes = new Gene[chromosomeSize];

			for (int i = 0; i < chromosomeSize; i++) {
				sampleGenes[i] = new IntegerGene(conf, 0, BOARD_SIZE-1);
			}

			Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);

			conf.setSampleChromosome(sampleChromosome);

			conf.setPopulationSize(POPULATION_SIZE);

			Genotype population = Genotype.randomInitialGenotype(conf);

			long startTime = System.currentTimeMillis();

			for (int i = 0; i < NUMBER_OF_EVOLUTIONS; i++) {

				IChromosome bestSolutionSoFar = population.getFittestChromosome();

				System.out.println("\nNajbolje rješenje nakon " + i + ". evolucije:");
				printSolution(bestSolutionSoFar, board);
				
				population.evolve();

				System.out.println();
			}

			long endTime = System.currentTimeMillis();

			System.out.println("Ukupno vrijeme trajanja : "
					+ (((double) endTime - startTime) / 1000) + " sekundi");

		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}

	}
	 private static void printSolution(IChromosome bestSolutionSoFar, int[] board) {
		 int stupac=0,redak=0;
		 String newLine = System.getProperty("line.separator");
		 for(stupac=0;stupac<bestSolutionSoFar.size();stupac++){
			 for(redak=0;redak<BOARD_SIZE;redak++){
				 if(((Integer) bestSolutionSoFar.getGene(stupac).getAllele()).intValue()==redak){
					 System.out.print("Q ");
				 }
				 else{
					 System.out.print("X ");
				 }
			 }
			 System.out.print(newLine);
			}
		
	}
	public static int[] fillTheBoard(int[] board){
		 int i=0;
		 Random rand = new Random();
		 for(i=0;i<BOARD_SIZE-1;i++){
			 board[i]=rand.nextInt(BOARD_SIZE-1);
		 }
		 return board;
	 }

	}

