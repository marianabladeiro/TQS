package ui;

import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DemoMain {

    /**
     * demonstrates a client for ramdom euromillions bets
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
    	Logger logger = Logger.getLogger(DemoMain.class.getName());

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();
        logger.info("Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        logger.info("You played:");
        logger.info(thisWeek.format());

        logger.info("Draw results:");
        logger.info(draw.getDrawResults().format());

        logger.info("Your score:");
        for (Dip dip : draw.findMatches(thisWeek)) {
            logger.info(dip.format());

        }
    }
}
