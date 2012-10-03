package Core;

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
	
	public static void parseArgs(String[] args)
	{
		int params = args.length;
		
		String inputPath = null;
		String outputPath = null;
		boolean displayResult = false;
		
		// Test each argument and parse it if correct
		for (int i = 0; i < params; i++)
		{
			// Help
		    if (args[i].equals("-?") || args[i].equals("-h"))
		    {
		    	System.out.println(HELP_STRING);
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
		    }
		}
		
		// An input file has been specified
		if (inputPath != null)
		{
			// Parse Graph file
			Graphe g = Parser.parse(inputPath);
			
			// Calculate Graph coloration
			int[] result = Algorithme.welshPowell(g);
			
			// Coloration produce a result
			if (result != null)
			{
				// Save result in output file
				if (outputPath != null)
				{
					Parser.saveResult(result, outputPath);
				}
				// Display result in standard output
				if (displayResult)
				{
					for(int i=0; i<result.length; i++)
					{
						System.out.print(" " + result[i]);
					}
					System.out.print(NEW_LINE);
				}
			}
		}
	}
}
