import org.apache.commons.cli.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class CLIApplication {

    public static final String INPUT_FILE_OPTION = "input-file-path";
    public static final String OUTPUT_FILE_OPTION = "output-file-path";

    public static void main(String[] args) throws ParseException, IOException, ParserConfigurationException, TransformerException {

        Options options = createOptions();

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);

        if (commandLine.hasOption(INPUT_FILE_OPTION) && commandLine.hasOption(OUTPUT_FILE_OPTION)) {
            Parser parser = new Parser();
            String inputPath = commandLine.getOptionValue(INPUT_FILE_OPTION);
            String outputPath = commandLine.getOptionValue(OUTPUT_FILE_OPTION);

            parser.parse(inputPath);
            parser.writeToFile(outputPath);
        } else {
            new HelpFormatter().printHelp("parser", options);
        }
    }


    private static Options createOptions() {
        Options options = new Options();

        Option inputFilePath = Option.builder()
                .hasArg()
                .longOpt(INPUT_FILE_OPTION)
                .desc("Path to the file to be parsed")
                .required()
                .build();

        Option outputFilePath = Option.builder()
                .hasArg()
                .longOpt(OUTPUT_FILE_OPTION)
                .desc("Path to the new XML file")
                .required()
                .build();

        options.addOption(inputFilePath);
        options.addOption(outputFilePath);

        return options;
    }
}
