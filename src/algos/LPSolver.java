package algos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Problem;
import model.Solution;

public class LPSolver {
	
	private static final String GLPK_DIRECTORY = "/usr/local/bin/";
	
	private Solution solution;
	private Problem pb;
	private String lpFilename;
	private String saveFilename;
	

	public LPSolver(Solution sol) {
		this.solution = new Solution(sol.problem);
		this.pb = sol.problem;
		this.lpFilename = "solver/input.lp";
		this.saveFilename = "solver/output.save";
	}

	

	public Solution solve() {
		this.createFile();
		this.exec(10 * 60);
		this.readSolution();
		
		return this.solution;
	}

	private void createFile() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.lpFilename));
			
			this.createMin (bw);
			this.createConstraints (bw);
			this.createVariables (bw);
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createMin(BufferedWriter bw) throws IOException {
		bw.write("Minimize\n");
		String line = "  obj: ";
		
		bw.write(line);
		bw.newLine();
	}

	private void createConstraints(BufferedWriter bw) throws IOException {
		bw.write("Subject To\n");
		
	}

	private void createVariables(BufferedWriter bw) throws IOException {
		bw.write("Binary\n");
		
	}
	
	public void exec (int timeout) {
		Runtime rt = Runtime.getRuntime();
		try {
			long startTime = System.currentTimeMillis();
			// --save filename pour solutions intermédiaires
			Process pr = rt.exec(GLPK_DIRECTORY + "glpsol --lp --exact --first -w " + this.saveFilename + " " + this.lpFilename);
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				
				if (System.currentTimeMillis() - startTime > 1000*timeout) {
					pr.destroy();
					break;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readSolution () {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.saveFilename));
			
			String line = br.readLine();
			String[] header = line.split(" ");
			int nbConstraints = new Integer(header[0]);
			int nbVariables = new Integer(header[1]);
			
			br.readLine();
			for (int idx=0 ; idx<nbConstraints ; idx++)
				br.readLine();
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
