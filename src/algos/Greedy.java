package algos;

import java.io.BufferedReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Drone;
import model.Instruction;
import model.Order;
import model.Problem;
import model.Solution;
import model.Warehouse;

public class Greedy {

	private Solution solution;

	public Greedy(Solution sol) {
		this.solution = sol;
	}

	public void naiveAlgo(Solution grSol) {
		// sort orders
		sortOrders(grSol);
		//
		takeCareOfOrders(grSol, solution.currentTurnNb);

		// calculate score
		grSol.computeScore();
	}

	private static int getDistance(int x1, int y1, int x2, int y2) {
		return new Double(Math.ceil(Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)))).intValue();
	}

	public void sortOrders(Solution grSol) {
		Collections.sort(grSol.problem.orders, new Comparator<Order>() {
			public int compare(Order o1, Order o2) {
				int sum1 = 0;

				for (int idx = 0; idx < o1.products.length; idx++)
					sum1 += o1.products[idx];

				int sum2 = 0;

				for (int idx = 0; idx < o2.products.length; idx++)
					sum2 += o2.products[idx];

				return (sum1 - sum2);
			}
		});
	}

	// closest warehouse containing at least 1 product
	public Warehouse findBestWarehouse(Order order, int productId, int productQty, Solution grSol) {
		Warehouse bestWh = null;
		Integer minDistance = null;

		for (Warehouse wh : grSol.problem.warehouses) {
			// System.out.println("... wh product " + wh.products[productId]);
			if (wh.products[productId] != 0) {
				int distance = getDistance(wh.row, wh.col, order.row, order.col);

				if (minDistance == null || distance < minDistance) {
					bestWh = wh;
				}
			}
		}

		return bestWh;
	}

	// closest drone with at least
	public Drone findBestDrone(Warehouse wh, int productId, int productQty, Solution grSol) {
		Drone bestDrone = null;
		Integer minDistance = null;

		for (Drone dr : grSol.drones) {
			if (dr.turnsNb == grSol.currentTurnNb
					&& (new Double(Math.floor(dr.availableSpace / grSol.problem.orderWeights.get(productId)))
							.intValue()) > 0) {
				int distance = getDistance(dr.row, dr.col, wh.row, wh.col);

				if (minDistance == null || distance < minDistance) {
					bestDrone = dr;
				}
			}
		}

		return bestDrone;
	}

	public void takeCareOfOrders(Solution grSol, int currentTurn) {
		while (grSol.currentTurnNb < grSol.problem.turnsNb) {

			for (Order order : grSol.problem.orders) {
				boolean isDelivered = true;

				if (order.deliveredTurn == null) {
					for (int idx = 0; idx < order.products.length; idx++) {
						if (order.products[idx] != 0) {
							// System.out.println("... looking for warehouse &
							// drone");
							// System.out.println("... looking for product : " +
							// idx);
							Warehouse wh = findBestWarehouse(order, idx, order.products[idx], grSol);

							Drone dr = null;

							if (wh != null) {
								// System.out.println("... looking for drone");
								dr = findBestDrone(wh, idx, order.products[idx], grSol);
							}

							if (dr != null) {
								int actionCost = launchAction(wh, dr, order, idx, order.products[idx], grSol);
							}

							isDelivered = false;
						}
					}
				}

				// not exactly true, the estimated score is an approximation
				if (order.deliveredTurn == null && isDelivered) {
					System.out.println("... order " + order.id + " is delivered ");
					System.out.println("... turn : " + grSol.currentTurnNb);
					order.deliveredTurn = order.currentTurn;
				}
			}

			makesUnoccupiedDroneSleep(grSol);
			// System.out.println("... current turn : " + grSol.currentTurnNb);
			grSol.currentTurnNb++;
		}

	}

	public int launchAction(Warehouse wh, Drone dr, Order order, int productId, int productQty, Solution grSol) {
		int quantityMaxTaken = Math.min(wh.products[productId], productQty);
		int quantityTaken = Math.min(quantityMaxTaken,
				new Double(Math.floor(dr.availableSpace / grSol.problem.orderWeights.get(productId))).intValue());

		int distanceWh = getDistance(dr.row, dr.col, wh.row, wh.col) + 1;

		int distanceOrder = getDistance(order.row, order.col, wh.row, wh.col) + 1;

		if ((dr.turnsNb + distanceWh + distanceOrder) < grSol.problem.turnsNb && quantityTaken > 0) {
			int weight = grSol.problem.orderWeights.get(productId) * quantityTaken;

			grSol.instructions.add(new Instruction(grSol.currentTurnNb, dr.load(wh, productId, quantityTaken, weight)));

			grSol.instructions.add(new Instruction((grSol.currentTurnNb + distanceWh),
					dr.deliver(order, productId, quantityTaken, weight)));

			wh.products[productId] -= quantityTaken;
			order.products[productId] -= quantityTaken;
			order.currentTurn = order.currentTurn != null ? Math.max(order.currentTurn, (grSol.currentTurnNb + distanceWh + distanceOrder)
					) : (grSol.currentTurnNb + distanceWh + distanceOrder);
		}

		return (grSol.currentTurnNb + distanceWh + distanceOrder);
	}

	public void makesUnoccupiedDroneSleep(Solution grSol) {
		for (Drone dr : grSol.drones) {
			if (dr.turnsNb == grSol.currentTurnNb) {
				grSol.instructions.add(new Instruction(dr.turnsNb, dr.wait_()));
			}
		}
	}

	public Solution solve() {
		Solution grSol = new Solution(this.solution);

		grSol.createDrones();
		naiveAlgo(grSol);

		return grSol;
	}

}
