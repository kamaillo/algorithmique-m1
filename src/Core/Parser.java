package Core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Provides methods for retrieve and parse a Graph file with standard format
 * like example provided in this page:
 * http://www.info.univ-angers.fr/pub/hao/COL_Bench/r125.1.col
 * @author Clément GUILLERMIN
 * October 2012
 */
public class Parser {

	/**
	 * Private constructor to prevent miss-usages of this class (contains static members only).
	 */
	private Parser()
	{}
	
	/**
	 * Save result array into a text file.
	 * @param result
	 * Resulting array to save.
	 * @param fileDestination
	 * Full destination path file.
	 */
	public static void saveResult(ArrayList<Integer> result, int k, String fileDestination)
	{
		// Creates a new file
		File _file = new File(fileDestination);
		FileWriter _fileWriter;
		
		try
		{
			// Writes on file and erase if exists
			_fileWriter = new FileWriter(_file, false);
			BufferedWriter _bufferedWriter = new BufferedWriter (_fileWriter);
			PrintWriter _printWriter = new PrintWriter(_bufferedWriter); 
			
			// Construct our datum
			StringBuilder _stringBuilder = new StringBuilder(50);
			_stringBuilder.append(String.valueOf(k));
			_stringBuilder.append("\n");
			for (int i=0; i<result.size(); i++)
			{
				_stringBuilder.append(" ");
				_stringBuilder.append(String.valueOf(result.get(i)));
			}
			
			// Writes our datum.
			_printWriter.print(_stringBuilder.toString()); 
			
			// Close readers
			_printWriter.close();
			_bufferedWriter.close();
			_fileWriter.close();
			
			// null them for speed-up Garbage collection.
			_printWriter = null;
			_bufferedWriter = null;
			_fileWriter = null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse a text file that represents a Graph in standard mode.
	 * @param fileName
	 * Full file path for the text file.
	 * @return
	 * Returns corresponding Graph if file is correct and file parsing success, returns null otherwise.
	 */
	public static Graphe parse(String fileName)
	{
		// Declare stream and readers
		InputStream _stream;
		InputStreamReader _streamReader;
		BufferedReader _buffStreamReader;
		
		// Resulting Graph
		Graphe g = null;
		
		// Surround with try/catch in case of IO exceptions when trying to access file.
		try
		{
			_stream = new FileInputStream(fileName);	// Open file in input stream.
			_streamReader = new InputStreamReader(_stream);	// Create a simple stream reader.
			_buffStreamReader = new BufferedReader(_streamReader);	// Create a double-buffered reader from our simple reader to access stream.
			
			// File correctly opened
			if (_stream != null && _streamReader != null && _buffStreamReader != null)
			{
				g = parseGraphe(_buffStreamReader);
			}
			
			// Close opened stream and readers
			// not sure if closing _streamReader and _stream is necessary, but just in case...
			_buffStreamReader.close();
			_streamReader.close();
			_stream.close();
			
			// null them to speed-up Garbage collection
			_buffStreamReader = null;
			_streamReader = null;
			_stream = null;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return g;
	}
	
	/**
	 * Test if line is a commentary or not.
	 * @param line
	 * String line from text graph file to parse.
	 * @return Returns True if line is commentary line, returns False otherwise.
	 */
	protected static boolean isCommentaryLine(String line)
	{
		return line.startsWith("c");
	}
	
	/**
	 * Test if line is the graph summary one or not.
	 * @param line
	 * String line from text graph file to parse.
	 * @return Returns True if line is the summary line, returns False otherwise.
	 */
	protected static boolean isSummaryLine(String line)
	{
		return line.startsWith("p");
	}
	
	/**
	 * Test if line is an edge or not.
	 * @param line
	 * String line from text graph file to parse.
	 * @return Returns True if line is an edge line, returns False otherwise.
	 */
	protected static boolean isEdgeLine(String line)
	{
		return line.startsWith("e");
	}
	
	/**
	 * Parse a line and returns its elements.
	 * @param line
	 * A string representing one line of a standard Graph file;
	 * @return
	 * Returns a String table containing all line elements.
	 */
	protected static String[] parseLine(String line)
	{
		return line.split(" ");
	}
	
	/**
	 * Parse an edge-line and returns a table that contains 2 edges.
	 * @param edgeLine
	 * A string representing one edge-line of a standard Graph file;
	 * @return
	 * Returns a String table. First index contains first edge, second contains second edge.
	 */
	protected static int[] parseEdgeLine(String edgeLine)
	{
		String str[] = parseLine(edgeLine);
		if (str.length == 3)
		{
			int edges[] = new int[2];
			edges[0] = Integer.parseInt(str[1]);
			edges[1] = Integer.parseInt(str[2]);
			return edges;
		}
		return null;
	}
	
	/**
	 * Parse a summary line and returns a table that contains Edges number and Vertices number.
	 * @param summaryLine
	 * A string representing one summary line of a standard Graph file;
	 * @return
	 * Returns a String table. First index contains total Edges number, second contains total Vertices number.
	 */
	protected static int[] parseSummaryLine(String summaryLine)
	{
		String str[] = parseLine(summaryLine);
		if (str.length == 4)
		{
			int col[] = new int[2];
			col[0] = Integer.parseInt(str[2]);
			col[1] = Integer.parseInt(str[3]);
			return col;
		}
		return null;
	}
	
	/**
	 * Initialize a Graph using its summary line.
	 * @param summaryLine
	 * The summary line containing total number of edges and vertices.
	 * This line will serve as initializer.
	 */
	protected static void InitializeGraph(String summaryLine, Graphe g)
	{
		int[] col = parseSummaryLine(summaryLine);
		g.setNbSommets(col[0]);
	}
	
	/**
	 * Add edges to the Graph using an edge line.
	 * @param edgeLine
	 * A string representing the edge-line to add.
	 * @param g
	 * Target Graph of the add operation.
	 */
	protected static void AddEdge(String edgeLine, Graphe g)
	{
		if (g != null)
		{
			int[] edges = parseEdgeLine(edgeLine);
			
			if (edges != null)
			{
				Sommet _first = g.getListeAdjacence().get(edges[0]-1);
				Sommet _second = g.getListeAdjacence().get(edges[1]-1);
				
				_first.ajouterSommetAdjacent(_second);
				_second.ajouterSommetAdjacent(_first);
			}
		}
	}
	
	/**
	 * Parse the line and construct the Graph.
	 * @param line
	 * Line of Graph.
	 */
	protected static void editGraphWithLine(String line, Graphe g)
	{
		// Line represents an edge
		if (isEdgeLine(line))
		{
			AddEdge(line,g);
		}
		// Line represents the summary edge
		else if (isSummaryLine(line))
		{
			InitializeGraph(line,g);
		}
	}
	
	/**
	 * Parse an opened stream containing Graph file, using a BufferedReader.
	 * @param buff
	 * BufferedReader used to access to opened Graph file stream.
	 * @return
	 * Returns a valid Graph if file is valid and operation success, returns null otherwise.
	 * @throws IOException
	 * Exception throws by BufferedReader.
	 */
	protected static Graphe parseGraphe(BufferedReader buff) throws IOException
	{
		Graphe g = new Graphe();
		
		String line = null;	// Current line of file.
		
		// Get next line until we are at end of file.
		while ((line=buff.readLine())!=null)
		{
			editGraphWithLine(line, g);
		}
		
		return g;
	}
}
