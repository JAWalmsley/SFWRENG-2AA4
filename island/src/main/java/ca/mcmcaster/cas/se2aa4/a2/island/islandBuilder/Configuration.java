package ca.mcmcaster.cas.se2aa4.a2.island.islandBuilder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Configuration {
    String[] args;
    CommandLine cli;

    public Configuration(String[] args) {
        this.args = args;
    }

    public void parse() throws ParseException{
        DefaultParser cliParser = new DefaultParser();
        Options options = new Options();

        options.addOption("h", "help", false, "Display help")
                .addOption("s", "shape", true, "Island Shape, default circle")
                .addOption("e", "elevation", true, "Elevation Land Type, default volcano")
                .addOption("l","lakes", true, "Maximum number of Lakes, default 2")
                .addOption("h", "heatmap", true, "Elevation Heatmap \"e\", " +
                        "Moisture Heatmap \"m\" or normal island \"i\", default i")
                .addOption("a", "absorption", true, "Soil absorption profile (linear, expoential), default linear")
                .addOption("q", "aquifers", true, "Maximum number of aquifers, default 3")
                .addOption("r", "rivers", true, "Maximum number of rivers, default 5")
                .addOption("d", "seed", true, "Generation Seed, default system time");

        this.cli = cliParser.parse(options, this.args);

        if (this.cli.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Island [OPTIONS]", options);
            return;
        }
    }

    /**
     * Get an option from the commandline
     * @param opt the option to get
     * @param def the default if no option is present
     * @return the value of the option
     */
    public String getOption(String opt, String def)
    {
        if(cli.hasOption(opt)) {
            return cli.getOptionValue(opt);
        }
        return def;
    }
}
