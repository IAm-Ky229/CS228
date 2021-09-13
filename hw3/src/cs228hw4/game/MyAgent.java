package cs228hw4.game;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

//import java.util.Iterator;
//import java.util.List;
//import java.util.Stack;

import edu.iastate.cs228.game.Agent;
import edu.iastate.cs228.game.GalaxyState;
import edu.iastate.cs228.game.SystemState;

//import cs228hw4.graph.CS228DiGraph;
//import cs228hw4.graph.CS228Dijkstra;

/**
 * Class that provides moves to a game client.
 * 
 * @author Kylus Pettit-Ehler
 *
 */
public class MyAgent implements Agent {
	private Color mycolor;
	private Color theycolor;
	private ArrayList<String> preventLoop = new ArrayList<>();
	private int turnIndex = 0;
	private int lastShot = 0;
	// ArrayList<SystemState> pathToUnconquered = new ArrayList<>();

	@Override
	public String getFirstName() {
		return "Kylus";
	}

	@Override
	public String getLastName() {
		return "Pettit-Ehler";
	}

	@Override
	public String getStuID() {
		return "768237494";
	}

	@Override
	public String getUsername() {
		return "kylusp";
	}

	@Override
	public String getAgentName() {
		return "Shrek";
	}

	@Override
	public File getAgentImage() {
		File f = new File("Shrek.png");
		return f;
	}

	@Override
	public boolean inTournament() {
		return false;
	}

	@Override
	public AgentAction[] getCommandTurnTournament(GalaxyState g, int energyLevel) {
		// generator count increase
		// energy increase
		// wormhole cost change
		// all MOVEs
		// all REFUELs
		// all FORTIFYs
		// all SETSCANs
		// all CAPTURE/CONTINUECAPTUREs
		// all SHOOTs
		return null;
	}

	/**
	 * Determine what the agent should do given the current state of the system and
	 * their energy level
	 */
	@Override
	public AgentAction[] getCommandTurnGrading(GalaxyState g, int energyLevel) {
		// Gather all necessary data about the system
		AgentAction[] execute = new AgentAction[3];
		ArrayList<AgentAction> addActions = new ArrayList<>();
		SystemState decision = g.getCurrentSystemFor(mycolor);
		SystemState opponent = g.getCurrentSystemFor(theycolor);
		int captureCost = decision.getCostToCapture();
		Color[] present = decision.getAgentsPresent();
		int energyStored = decision.getEnergyStored();
		int generatorCount = decision.getGeneratorCount();
		int getMaxGenCount = decision.getMaximumGeneratorCount();
		int[] neighborCosts = decision.getTravelCost();
		Color owner = decision.getOwner();
		SystemState[] neighbors = decision.getNeighbors();
		// Prevent looping by keeping track of recently visited systems
		if (preventLoop.size() == 12) {
			preventLoop.remove(0);
		}
		preventLoop.add(decision.getName());

		// Add actions to the array with priority on refueling, capturing, fortifying,
		// shooting, moving, then finally refuelling again
		if (addActions.size() < 3) {
			addActions = DoRefuel(owner, addActions);
		}
		if (addActions.size() < 3) {
			addActions = CaptureSystem(energyStored, addActions, generatorCount, getMaxGenCount, owner, captureCost,
					energyLevel);
		}
		if (addActions.size() < 3) {
			addActions = ConsiderFortify(captureCost, energyLevel, addActions, owner);
		}
		if (addActions.size() < 3) {
			addActions = Sabotage(energyLevel, addActions, neighbors, neighborCosts, energyStored, present, decision,
					opponent);
		}
		if (addActions.size() < 3) {
			addActions = DetermineMove(energyLevel, neighborCosts, neighbors, addActions, getMaxGenCount, decision);
		}
		/*
		 * if (addActions.size() < 3) { addActions = AddSetScan(energyLevel,
		 * addActions); }
		 */
		if (addActions.size() < 3) {
			addActions = DoRefuel(owner, addActions);
		}
		// If still empty space in array add actions to do nothing
		if (addActions.size() < 3) {
			while (addActions.size() < 3) {
				addActions.add(new Agent.NoAction());
			}
		}
		// Add AgentActions to returned array
		for (int n = 0; n < addActions.size(); n++) {
			execute[n] = addActions.get(n);
		}
		return execute;
	}

	/**
	 * Determine if the agent should shoot at another system.
	 * 
	 * @param energyLevel   The agent's current energy level.
	 * @param addActions    The arraylist representing the agent's actions.
	 * @param neighbors     The immediate neighbors of the agent's current system.
	 * @param neighborCosts The costs to get to each neighbor of the current system.
	 * @param energyStored  The energy stored in the current system.
	 * @param present       The agents present in the current system.
	 * @param decision      The system the agent is currently in.
	 * @return The modified arraylist / any additional moves
	 */
	private ArrayList<AgentAction> Sabotage(int energyLevel, ArrayList<AgentAction> addActions, SystemState[] neighbors,
			int[] neighborCosts, int energyStored, Color[] present, SystemState decision, SystemState opponent) {
		// Don't shoot if energy level is less than 55
		if (energyLevel < 55) {
			return addActions;
		}
		SystemState shootAt = null;
		int shootAtEdge = -1;
		// Search for the opposing agent
		for (int i = 0; i < neighbors.length; i++) {
			if (neighbors[i].getAgentsPresent().length == 1) {
				shootAt = neighbors[i];
				shootAtEdge = neighborCosts[i];
			}
		}
		// Don't shoot if both agents are in the same system
		if (present.length == 2) {
			return addActions;
		}
		// Determine how many shots and power based on energylevel and state of
		// addActions
		if ((shootAtEdge > 0) && (shootAtEdge < 10) && lastShot > 4) {
			String[] toAdd = new String[1];
			toAdd[0] = shootAt.getName();
			int shotPower = (int) energyLevel * (1 / 10);
			if (addActions.size() == 2) {
				addActions.add(new Agent.Shoot(toAdd, shotPower));
			} else if (addActions.size() == 1) {
				addActions.add(new Agent.Shoot(toAdd, shotPower));
				addActions.add(new Agent.Shoot(toAdd, shotPower));
			} else {
				addActions.add(new Agent.Shoot(toAdd, shotPower));
				addActions.add(new Agent.Shoot(toAdd, shotPower));
				addActions.add(new Agent.Shoot(toAdd, shotPower));
			}
			lastShot = 0;
		} else {
			// Don't shoot too much
			lastShot++;
		}
		return addActions;
	}

	/*
	 * private ArrayList<AgentAction> AddSetScan(int energyLevel,
	 * ArrayList<AgentAction> addActions) { if ((energyLevel > 40) && (setScanTwo !=
	 * true)) { System.out.println("SETSCAN 2"); addActions.add(new
	 * Agent.SetScan(2)); setScanThree = false; setScanTwo = true; } else {
	 * addActions.add(new Agent.SetScan(0)); setScanTwo = false; }
	 * 
	 * } else if (energyLevel > 60 && (setScanThree != true)) {
	 * System.out.println("SETSCAN 3"); addActions.add(new Agent.SetScan(3));
	 * setScanTwo = false; setScanThree = true; }
	 * 
	 * return addActions; }
	 */

	/**
	 * Determine the move that the agent should make given the following parameters.
	 * 
	 * @param energyLevel    The energy the Agent currently has.
	 * @param neighborCosts  The cost to travel to systems one vertex away from the
	 *                       system the agent is currently in.
	 * @param neighbors      The system neighbors of the system the agent is
	 *                       currently in.
	 * @param addActions     The collection containing the actions that the action
	 *                       will take.
	 * @param getMaxGenCount The max generator count of the current system.
	 * @param decision       The system the agent currently is located in.
	 * @return An arraylist with any additional moves added.
	 */
	private ArrayList<AgentAction> DetermineMove(int energyLevel, int[] neighborCosts, SystemState[] neighbors,
			ArrayList<AgentAction> addActions, int getMaxGenCount, SystemState decision) {
		int maxorminIndex = 0;
		// Do nothing if energy level less than 20
		if (energyLevel <= 20) {
			return addActions;
		}

		/*
		 * if (pathToUnconquered.isEmpty() && (decision.getOwner().getRGB() ==
		 * mycolor.getRGB())) { pathToUnconquered = unconqueredNeighbors(setScanTwo,
		 * setScanThree, neighbors, neighborCosts, decision); } if
		 * (!pathToUnconquered.isEmpty()) { addActions.add(new
		 * Agent.Move(pathToUnconquered.get(0).getName())); pathToUnconquered.remove(0);
		 * }
		 */

		// Prioritize unvisited systems
		for (int i = 0; i < neighbors.length; i++) {
			if (!(neighbors[i].getOwner().getRGB() == mycolor.getRGB())) {
				addActions.add(new Agent.Move(neighbors[i].getName()));
				return addActions;
			}
		}
		// Move randomly every once and a while
		if (randomMove(turnIndex)) {
			Random rand = new Random();
			int randomMove = rand.nextInt(neighbors.length);
			addActions.add(new Agent.Move(neighbors[randomMove].getName()));
			return addActions;
		}
		// Alternate what Agent wants to do each turn: if odd turn, look for shortest
		// edge neighbor
		if (turnIndex % 2 == 1) {
			int minimum = neighborCosts[0];
			int minIndex = 0;
			for (int o = 0; o < neighbors.length; o++) {
				if (neighborCosts[o] < minimum) {
					minimum = neighborCosts[o];
					minIndex = o;
				}
			}
			if (energyLevel > neighborCosts[minIndex]) {
				addActions.add(new Agent.Move(neighbors[minIndex].getName()));
			}
			return addActions;
		}
		// If even turn, look for systems with most energy
		int maxEnergyDifferential = neighbors[0].getEnergyStored() - neighborCosts[0];
		int EnergyDifferentialIndex = 0;
		for (int r = 0; r < neighbors.length; r++) {
			if (neighbors[r].getEnergyStored() - neighborCosts[r] > maxEnergyDifferential) {
				EnergyDifferentialIndex = r;
				maxEnergyDifferential = neighbors[r].getEnergyStored() - neighborCosts[r];
			}
		}
		maxorminIndex = EnergyDifferentialIndex;
		// As long as energy level exceeds edge cost to neighbor, add move to array
		if (energyLevel > neighborCosts[maxorminIndex]) {
			Agent.Move temp = new Agent.Move(neighbors[maxorminIndex].getName());
			addActions.add(temp);
		}
		return addActions;

	}

	/**
	 * A random move should be taken every 6 turns.
	 * 
	 * @param randomIndex2 The current index of random.
	 * @return Whether 6 turns have passed since the last random move.
	 */
	private boolean randomMove(int randomIndex2) {
		turnIndex++;
		return turnIndex % 5 == 4;
	}

	/*
	 * private ArrayList<SystemState> unconqueredNeighbors(boolean setScanTwo2,
	 * boolean setScanThree2, SystemState[] neighbors, int[] neighborCosts,
	 * SystemState decision) { ArrayList<SystemState> foundPath = new ArrayList<>();
	 * CS228DiGraph<SystemState> systemGraph = new CS228DiGraph<>(100);
	 * systemGraph.addVertex(decision); for (int p = 0; p < neighbors.length; p++) {
	 * systemGraph.addVertex(neighbors[p]); } for (int c = 0; c <
	 * neighborCosts.length; c++) { System.out.println(decision + " to " +
	 * neighbors[c] + " cost: " + neighborCosts[c]); systemGraph.addEdge(decision,
	 * neighbors[c], neighborCosts[c]); } System.out.println("Initial vertices: ");
	 * Iterator<SystemState> iterator = systemGraph.iterator(); while
	 * (iterator.hasNext()) { System.out.println(iterator.next()); } if (setScanTwo)
	 * { systemGraph = constructGraph(systemGraph, neighbors, neighborCosts, 2);
	 * Iterator<SystemState> iter = systemGraph.iterator(); SystemState destination
	 * = null; while (iter.hasNext()) { SystemState temp = iter.next(); if
	 * (!(temp.getOwner().getRGB() == mycolor.getRGB())) { destination = temp;
	 * break; } } System.out.println("Destination: " + destination); if (destination
	 * != null) { CS228Dijkstra<SystemState> calculate = new
	 * CS228Dijkstra<SystemState>(systemGraph); calculate.run(decision);
	 * List<SystemState> shortestPath = calculate.getShortestPath(destination);
	 * Iterator<SystemState> listIterator = shortestPath.iterator(); while
	 * (listIterator.hasNext()) { foundPath.add(listIterator.next()); } } return
	 * foundPath; } else if (setScanThree) { systemGraph =
	 * constructGraph(systemGraph, neighbors, neighborCosts, 3); return foundPath; }
	 * else { return foundPath; } }
	 */

	/*
	 * private CS228DiGraph<SystemState> constructGraph(CS228DiGraph<SystemState>
	 * systemGraph, SystemState[] neighbors, int[] neighborCosts, int i) { if (i ==
	 * 2) { SystemState traverse; SystemState[] traverseNeighbors; for (int e = 0; e
	 * < neighbors.length; e++) { traverse = neighbors[e]; traverseNeighbors =
	 * neighbors[e].getNeighbors(); System.out.println(); int[] traverseCosts =
	 * traverse.getTravelCost(); System.out.println(traverseNeighbors.length); for
	 * (int x = 0; x < traverseNeighbors.length; x++) {
	 * 
	 * System.out.println("Got here"); systemGraph.addVertex(traverseNeighbors[x]);
	 * 
	 * } System.out.println("After adding vertices: "); Iterator<SystemState>
	 * iterator = systemGraph.iterator(); while (iterator.hasNext()) {
	 * System.out.println(iterator.next()); } for (int y = 0; y <
	 * traverse.getNeighbors().length; y++) { System.out.println(traverse + " to " +
	 * traverseNeighbors[y] + " cost: " + traverseCosts[y]);
	 * systemGraph.addEdge(traverse, traverseNeighbors[y], traverseCosts[y]);
	 * System.out .println("Assigned edge cost: " +
	 * systemGraph.getEdgeCost(traverse, traverseNeighbors[y])); } } return
	 * systemGraph; } return systemGraph; }
	 */

	/**
	 * Considers if the current system should be fortified.
	 * 
	 * @param captureCost Capture cost of the system.
	 * @param energyLevel The agent's current energy level.
	 * @param addActions  The arraylist containing the agent's actions.
	 * @param owner       The owner of the system where the agent is located.
	 * @return Any additional moves / modified arraylist
	 */
	private ArrayList<AgentAction> ConsiderFortify(int captureCost, int energyLevel, ArrayList<AgentAction> addActions,
			Color owner) {
		// Only consider if the agent owns the system
		if (owner.getRGB() == mycolor.getRGB()) {
			if (captureCost < 5 && (energyLevel > 6)) {
				Agent.Fortify temp = new Agent.Fortify(4);
				addActions.add(temp);
			}
		}
		return addActions;
	}

	/**
	 * Attempt to refuel.
	 * 
	 * @param owner      The owner of the current system.
	 * @param addActions The collection of moves to be executed.
	 * @return Any additional moves / modified arraylist
	 */
	private ArrayList<AgentAction> DoRefuel(Color owner, ArrayList<AgentAction> addActions) {
		// Only add action if the agent owns the system.
		if (owner.getRGB() == mycolor.getRGB()) {
			addActions.add(new Agent.Refuel());
		}
		return addActions;
	}

	/**
	 * Determine if the agent should try to capture the current system.
	 * 
	 * @param energyStored   The energy stored in the system.
	 * @param addActions     The arraylist of actions the agent will take.
	 * @param generatorCount The number of generators in the system.
	 * @param getMaxGenCount The maximum number of generators the system can
	 *                       support.
	 * @param owner          The owner of the current system.
	 * @param captureCost    The capture cost of the current system.
	 * @param energyLevel    The agent's current energy level.
	 * @return The modified arraylist / any additional agent actions
	 */
	private ArrayList<AgentAction> CaptureSystem(int energyStored, ArrayList<AgentAction> addActions,
			int generatorCount, int getMaxGenCount, Color owner, int captureCost, int energyLevel) {
		// Only consider if the agent doesn't own the system
		if (!(owner.getRGB() == mycolor.getRGB())) {
			// Capture commands added change based on the cost to capture
			if (captureCost < (energyLevel - 5)) {
				if (captureCost < 4) {
					addActions.add(new Agent.Capture(0));
					for (int j = 0; j < captureCost - 1; j++) {
						addActions.add(new Agent.ContinueCapture());
					}
				} else if (captureCost < 6) {
					addActions.add(new Agent.Capture(3));
					for (int k = 0; k < captureCost - 4; k++) {
						addActions.add(new Agent.ContinueCapture());
					}
				} else {
					addActions.add(new Agent.Capture(captureCost - 3));
					addActions.add(new Agent.ContinueCapture());
					addActions.add(new Agent.ContinueCapture());
				}
			}
		}
		return addActions;
	}

	/**
	 * Set the color of the agent that is operated by this java file.
	 */
	@Override
	public void setColor(Color c) {
		mycolor = c;
	}

	/**
	 * Set the color of the opposing agent.
	 */
	@Override
	public void setOpponentColor(Color c) {
		theycolor = c;
	}

}
