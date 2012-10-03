package Core;

import java.util.ArrayList;

public class Main {
	
	/**
	 * System defined new-line character.
	 */
	public static String NEW_LINE = System.getProperty("line.separator");
	
	/**
	 * Usage string for command summary.
	 */
	public static String USAGE_STRING = "coloration [-i input_path | -? | -h ] [-o output_path] [-v]";
	
	/**
	 * Help string for command details.
	 */
	public static String HELP_STRING = "Usage:" + NEW_LINE +
			"   " + USAGE_STRING + NEW_LINE +
			"Options:" + NEW_LINE +
			"   [-o output_path] result file: write detailed result in output file. File will be created if not exists." + NEW_LINE +
			"   [-v] verbose: show coloration details in standard output." + NEW_LINE +
			"   [-?][-h] help." + NEW_LINE +
			"Example:" + NEW_LINE +
			"   coloration -i /home/inputGraph.col -o /home/outputGraph.txt";
	
	/**
	 * Main function.
	 * @param args
	 */
	public static void main(String[] args) {
		
		int params = args.length;
		
		switch (params)
		{
		case 0:
			System.out.println("No input file. See -? or -h for help.");
			break;
		default:
			parseArgs(args);
			break;
		}
	}
	
	/**
	 * Parse main arguments
	 * @param args
	 * Command-line arguments
	 */
	public static void parseArgs(String[] args)
	{
		int params = args.length;
		
		String inputPath = null;
		String outputPath = null;
		boolean displayResult = false;
		boolean error = false;
		
		// Test each argument and parse it if correct
		for (int i = 0; i < params; i++)
		{
			// Help
		    if (args[i].equals("-?") || args[i].equals("-h"))
		    {
		    	System.out.println(HELP_STRING);
		    	error = true;
		    	break;
		    }
		    // Input
		    else if (args[i].equals("-i") && (i+1<params))
		    {
		    	inputPath = args[i+1];
		    	i++;
		    }
		    // Output
		    else if (args[i].equals("-o") && (i+1<params))
		    {
		    	outputPath = args[i+1];
		    	i++;
		    }
		    // Display
		    else if (args[i].equals("-v"))
		    {
		    	displayResult = true;
		    }
		    // Invalid parameter
		    else
		    {
		    	System.out.println("ERROR: invalid parameter \"" + args[i] + "\"" + NEW_LINE +
		    			"Usage: " + USAGE_STRING + NEW_LINE +
		    			"See help for details");
		    	error = true;
		    	break;
		    }
		}
		
		// An input file has been specified and there is no interruption
		if (inputPath != null && !error)
		{
			// Parse Graph file
			Graphe g = Parser.parse(inputPath);
			
			// Calculate Graph coloration
			ArrayList<Integer> result = new ArrayList<Integer>();
			int k = Algorithme.welshPowell(g,result);
			
			// Coloration produce a result
			if (result != null)
			{
				// Save result in output file
				if (outputPath != null)
				{
					Parser.saveResult(result, k, outputPath);
				}
				// Display result in standard output
				if (displayResult)
				{
					System.out.println("K = " + k + NEW_LINE);
					for(int i=0; i<result.size(); i++)
					{
						System.out.print(" " + result.get(i));
					}
					System.out.print(NEW_LINE);
				}
			}
		}
	}
}
