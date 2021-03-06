/**
 * 2017 (c) piteren
 */
package trainer.toCase;

import trainer.NTaiSolver;
import trainer.NTCaseFeedback;
import diffUtils.UArr;

/**
 * trained actor interface
 * object that implements this interface should extend client_actor and should have AIsolver
 * should override client_actor_METHOD that makes decision for case with solver_supported_METHOD:
 *  1.knows case state and has all information to prepare solver input data
 *  2.interprets solver output data (it may know case_ACTUAL_POSSIBLE_decision_list, so it should use that info to "trim" solver out)
 *  3.prepares feedback for solver
 */
public interface NTActor {

    // returns actor AIsolver
    NTaiSolver getMySolver();

    // prepares solver input data array
    double[] prepSolverIN();

    // runs actor solver with given input, returns solver output
    default double[] runMySolver(double[] solverIN){
        return getMySolver().runFWD(solverIN);
    }

    // interprets solver output array, returns decision index
    default int intpSolverOUT(double[] solverOUT){
        int aX = UArr.maxVix(solverOUT);
        return aX;
    }

    //prepares complete feedback for solver (for taken decision index)
    NTCaseFeedback prepFeedbackToSolver(int decIX);

    // !! void feedMySolver(double[] solverOUT, int decIX);
}