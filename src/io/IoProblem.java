package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.Drone;
import model.Order;
import model.Problem;
import model.Warehouse;

public class IoProblem {

	public static Problem load(File problemFile) throws IOException {

		System.out.println("... Reading : " + problemFile.toString());
		BufferedReader br = new BufferedReader(new FileReader(problemFile));

		Problem prob = new Problem(getFileName(problemFile));
		// TODO parse la première ligne avec les variables

		// TODO parse toutes les lignes suivantes

		IoProblem.loadInfos(prob, br);
		IoProblem.loadWarehouses(prob, br);
		IoProblem.loadOrders(prob, br);

		br.close();

		return prob;
	}

	private static String getFileName(File problemFile) {
		String path = problemFile.getAbsolutePath();
		String name = path.substring(path.lastIndexOf('/') + 1);
		String nameWithoutExtension = name.substring(0, name.indexOf('.'));

		return nameWithoutExtension;
	}

	/*
	 */
	private static void loadInfos(Problem prob, BufferedReader br) throws IOException {
		String line1 = br.readLine();
		//
		String[] dataLine1 = line1.split(" ");
		prob.rowsNb = Integer.parseInt(dataLine1[0]);
		prob.colsNb = Integer.parseInt(dataLine1[1]);
		prob.dronesNb = Integer.parseInt(dataLine1[2]);
		prob.turnsNb = Integer.parseInt(dataLine1[3]);
		prob.maxPayload = Integer.parseInt(dataLine1[4]);

		//
		String line2 = br.readLine();
		String[] dataLine2 = line2.split(" ");

		//
		String line3 = br.readLine();
		String[] dataLine3 = line3.split(" ");

		for (int idx = 0; idx < dataLine3.length; idx++) {
			prob.orderWeights.add(Integer.parseInt(dataLine3[idx]));
		}

		return;
	}

	private static void loadWarehouses(Problem prob, BufferedReader br) throws IOException {
		// nbOfWarehouses
		String line4 = br.readLine();
		String[] dataLine4 = line4.split(" ");
		int warehousesNb = Integer.parseInt(line4);

		// warehouse info
		int i = 0;

		while (i < warehousesNb) {
			String[] position = br.readLine().split(" ");
			String[] infoProduct = br.readLine().split(" ");

			Warehouse wh = new Warehouse(i, Integer.parseInt(position[0]), Integer.parseInt(position[1]));

			wh.products = new int[infoProduct.length];
			
			for (int idx = 0; idx < infoProduct.length; idx++) {
				wh.products[idx] = Integer.parseInt(infoProduct[idx]);
			}
			
			prob.warehouses.add(wh);

			i++;
		}

		return;
	}

	private static void loadOrders(Problem prob, BufferedReader br) throws IOException {
		// nb of orders
		String line4 = br.readLine();
		int ordersNb = Integer.parseInt(line4);

		// orders info
		int i = 0;

		while (i < ordersNb) {
			String[] position = br.readLine().split(" ");
			int nbOfProduct = Integer.parseInt(br.readLine());

			String[] listOfProduct = br.readLine().split(" ");

			Order o = new Order(i, Integer.parseInt(position[0]), Integer.parseInt(position[1]));
			
			Integer max = null;
			for (int idx = 0; idx < listOfProduct.length; idx++) {
				if (max == null || max <= Integer.parseInt(listOfProduct[idx])) {
					max =  Integer.parseInt(listOfProduct[idx]) + 1;
				}
			}
			
			o.products = new int[max];

			for (int idx = 0; idx < listOfProduct.length; idx++) {
				o.products[Integer.parseInt(listOfProduct[idx])]++;
			}

			prob.orders.add(o);
			i++;
		}

		return;
	}
}
