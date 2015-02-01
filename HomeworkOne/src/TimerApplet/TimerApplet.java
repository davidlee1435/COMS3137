package TimerApplet;

import graph.Axis;
import graph.DataSet;
import graph.Graph2D;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;  
import java.applet.*;
import graph.*;

public class TimerApplet extends Applet{
	Graph2D graph;
	DataSet data1;
	DataSet data2;
	DataSet data3;
	Axis    xaxis;
	Axis    yaxis_left;
	Axis    yaxis_right;
	double d1[],d2[];
	int np = 100000;
	int n = 0;


	public void init() {
		final int MAXPOINTS=12;
		double d1[] = new double[MAXPOINTS*2];
		double d2[] = new double[MAXPOINTS*2];
		double d3[] = new double[MAXPOINTS*2];
		double x,y;

		graph = new Graph2D();
		graph.drawzero = false;
		graph.drawgrid = false;
		graph.borderTop = 50;
		graph.borderRight=100;
		setLayout( new BorderLayout() );
		add("Center", graph);
		
		Algorithm a = new Algorithm();
		
		TimeInterval t= new TimeInterval();
		t.startTiming();
		for(int i=0; i<MAXPOINTS; i++){
			x=i;
			y=i;
			d1[i*2]=x; d1[i*2+1]=y;
		}
		t.endTiming();
		System.out.println("time to generate " + MAXPOINTS + " values of Y=X is " + t.getElapsedTime());

		t = new TimeInterval();
		int i = 0;
		
		t.startTiming();
		a.algorithmOne(5);
		t.endTiming();
		System.out.println("Time to generate algorithm two is: " + t.getElapsedTime());
		
		data1 = graph.loadDataSet(d1,MAXPOINTS);
		data1.linestyle = 1;
		data1.linecolor   =  new Color(0,255,0);
		data1.marker    = 1;
		data1.markerscale = 1;
		data1.markercolor = new Color(0,0,255);
		data1.legend(200,100,"Algorithm 1");
		data1.legendColor(Color.black);

		data2 = graph.loadDataSet(d2,MAXPOINTS);
		data2.linestyle = 1;
		data2.linecolor   =  new Color(255,0,0);
		data2.marker    = 1;
		data2.markerscale = 1;
		data2.markercolor = new Color(0,0,255);
		data2.legend(200,120,"Algorithm 2");
		data2.legendColor(Color.black);

		data3 = graph.loadDataSet(d3,MAXPOINTS);
		data3.linestyle = 1;
		data3.linecolor   =  new Color(0,0,255);
		data3.marker    = 1;
		data3.markerscale = 1;
		data3.markercolor = new Color(0,0,255);
		data3.legend(200,120,"Algorithm 3");
		data3.legendColor(Color.black);
		
		xaxis = graph.createAxis(Axis.BOTTOM);
		xaxis.attachDataSet(data1);
		xaxis.attachDataSet(data2);
		xaxis.setTitleText("N (array length)");
		xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
		xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
		/*
		 **      Attach the first data set to the Left Axis
		 */
		yaxis_left = graph.createAxis(Axis.LEFT);
		yaxis_left.attachDataSet(data1);
		yaxis_left.attachDataSet(data2);
		yaxis_left.setTitleText("Time (s)");
		yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
		yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
		yaxis_left.setTitleColor( new Color(0,0,255) );
	}
}
