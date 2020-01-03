package fitness;




import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import java.lang.Math;
import main.Main;



public class MinimizeAttacksFitnessFunction extends FitnessFunction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[] board=new int[Main.BOARD_SIZE];
	
	
	 public MinimizeAttacksFitnessFunction(int[] board) {
		this.board=board;
	}

	 

	@Override
	protected double evaluate(IChromosome a_potentialSolution) {
		int stupacPrvi=0,stupacDrugi=0,redakPrvi=0,redakDrugi=0;
		double fitness=1000000;
		
		for(stupacPrvi=0;stupacPrvi<a_potentialSolution.size();stupacPrvi++){
			redakPrvi=((Integer) a_potentialSolution.getGene(stupacPrvi).getAllele()).intValue();
			for(stupacDrugi=0;stupacDrugi<a_potentialSolution.size();stupacDrugi++){
				redakDrugi=((Integer) a_potentialSolution.getGene(stupacDrugi).getAllele()).intValue();
				if(redakPrvi==redakDrugi && stupacPrvi!=stupacDrugi){
					fitness=fitness-1000;
				}
				if(Math.abs(stupacPrvi-stupacDrugi)==Math.abs(redakPrvi-redakDrugi) && stupacPrvi!=stupacDrugi){
					fitness=fitness-1000;
				}
			}
			
		}
		return fitness;
	}

}
