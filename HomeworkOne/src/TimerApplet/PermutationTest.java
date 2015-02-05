package TimerApplet;

import graph.Axis; 
import graph.DataSet;
import graph.Graph2D;

import java.awt.*;  
import java.applet.*;
import java.util.Arrays;

import graph.*;

/**
 * @author David Lee<br>
 * UNI: jl4397 <br>
 * 
 * An applet that times three different permutation algorithms<br>
 * Source Code: http://www.cs.columbia.edu/~allen/S14/src/graphexample/<br>
 */
public class PermutationTest extends Applet{
	private Graph2D graph;
	private DataSet data1, data2, data3;
	private TimeInterval timeInterval;
	private Axis xaxis;
	private Axis yaxis_left;
	private double d1[],d2[],d3[];
	private static final int MAXPOINTS = 20;
	private static final Algorithm algorithm = new Algorithm();

	/**
	 * Initializes the applet
	 */
	public void init() {
		d1 = new double[MAXPOINTS*2];
		d2 = new double[MAXPOINTS*2];
		d3 = new double[MAXPOINTS*2];
		
		initializeGraph();
		
		assignAlgorithmOne();
		assignAlgorithmTwo();
		assignAlgorithmThree();

		initializeDatasetOne();
		initializeDatasetTwo();
		initializeDatasetThree();
		
		initializeXAxis();
		initializeYAxis();
	}
	
	/**
	 * Sets up the graph
	 */
	private void initializeGraph(){
		graph = new Graph2D();
		graph.drawzero = false;
		graph.drawgrid = false;
		graph.borderTop = 50;
		graph.borderRight=100;
		setLayout( new BorderLayout() );
		add("Center", graph);
	}
	
	/**
	 * Assigns d1 values from algorithm one
	 */
	private void assignAlgorithmOne(){
		timeInterval= new TimeInterval();
		
		int n = 100;
		double totalTime = 0;
		int i = 0;
		while(n<=7000000 && totalTime<30){
			totalTime = timeAlgorithm(timeInterval, n, 1);
			d1[i*2]=n;
			d1[i*2+1]=totalTime;
			if(totalTime<30)
				i++;
			n*=2;
		}
		//cuts off the end of the array that is empty in order to prevent them from being plotted on the graph
		d1=Arrays.copyOfRange(d1, 0, i*2);
	}
	
	/**
	 * Assigns d2 values from algorithm two
	 */
	private void assignAlgorithmTwo(){
		timeInterval= new TimeInterval();
		
		int n = 100;
		double totalTime = 0;
		int i = 0;
		while(n<=7000000 && totalTime<30){
			totalTime = timeAlgorithm(timeInterval, n, 2);
			d2[i*2]=n;
			d2[i*2+1]=totalTime;
			if(totalTime<30)
				i++;
			n*=2;
		}
		//cuts off the end of the array that is empty in order to prevent them from being plotted on the graph
		d2=Arrays.copyOfRange(d2, 0, i*2);
	}
	
	/**
	 * Assigns d3 values algorithm three
	 */
	private void assignAlgorithmThree(){
		timeInterval= new TimeInterval();
		
		int n = 100;
		double totalTime = 0;
		int i = 0;
		while(n<=7000000 && totalTime<30){
			totalTime = timeAlgorithm(timeInterval, n, 3);
			d3[i*2]=n;
			d3[i*2+1]=totalTime;
			if(totalTime<30)
				i++;
			n*=2;
		}
		//cuts off the end of the array that is empty in order to prevent them from being plotted on the graph
		d3=Arrays.copyOfRange(d3, 0, i*2);
	}
	
	/**
	 * Plots dataset one on the graph and formats the information
	 */
	private void initializeDatasetOne(){
		data1 = graph.loadDataSet(d1, d1.length/2);
		data1.linestyle = 1;
		data1.linecolor = new Color(0,255,0);
		data1.marker = 1;
		data1.markerscale = 1;
		data1.markercolor = new Color(0,0,255);
		data1.legend(200,100,"Algorithm 1");
		data1.legendColor(Color.black);
	}
	
	/**
	 * Plots dataset two on the graph and formats the information
	 */
	private void initializeDatasetTwo(){
		data2 = graph.loadDataSet(d2, d2.length/2);
		data2.linestyle = 1;
		data2.linecolor = new Color(255,0,0);
		data2.marker = 1;
		data2.markerscale = 1;
		data2.markercolor = new Color(0,0,255);
		data2.legend(200,120,"Algorithm 2");
		data2.legendColor(Color.black);
	}
	
	/**
	 * Plots dataset three on the graph and formats the information
	 */
	private void initializeDatasetThree(){
		data3 = graph.loadDataSet(d3, d3.length/2);
		data3.linestyle = 1;
		data3.linecolor = new Color(0,0,255);
		data3.marker = 1;
		data3.markerscale = 1;
		data3.markercolor = new Color(0, 0, 255);
		data3.legend(200, 140, "Algorithm 3");
		data3.legendColor(Color.black);
	}
	
	/**
	 * Initializes information on the x axis
	 */
	private void initializeXAxis(){
		xaxis = graph.createAxis(Axis.BOTTOM);
		xaxis.attachDataSet(data1);
		xaxis.attachDataSet(data2);
		xaxis.attachDataSet(data3);
		xaxis.setTitleText("N (array length)");
		xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
		xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
	}
	
	/**
	 * Initializes information on the y axis
	 */
	private void initializeYAxis(){
		yaxis_left = graph.createAxis(Axis.LEFT);
		yaxis_left.attachDataSet(data1);
		yaxis_left.attachDataSet(data2);
		yaxis_left.attachDataSet(data3);
		yaxis_left.setTitleText("Time (s)");
		yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
		yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
		yaxis_left.setTitleColor( new Color(0,0,255) );
	}
	
	/**
	 * Times a certain algorithm
	 * @param t the TimeInterval object that is used to time the algorithm
	 * @param size the algorithm's input size
	 * @param algorithmNumber 1, 2, or 3, determines which algorithm will be used
	 * @return the time in seconds to run the algorithm
	 */
	private double timeAlgorithm(TimeInterval t, int size, int algorithmNumber){
		t.startTiming();
		if(algorithmNumber == 1)
			algorithm.algorithmOne(size);
		else if(algorithmNumber==2)
			algorithm.algorithmTwo(size);
		else
			algorithm.algorithmThree(size);
		t.endTiming();
		return t.getElapsedTime();
	}
}
