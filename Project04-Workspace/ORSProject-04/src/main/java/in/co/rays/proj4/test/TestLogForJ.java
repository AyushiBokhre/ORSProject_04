package in.co.rays.proj4.test;

import org.apache.log4j.Logger;

public class TestLogForJ {

	public static void main(String[] args) {

		Logger log = Logger.getLogger(TestLogForJ.class);

		log.debug("debug message..");
		log.info("info message..");
		log.warn("warn message..");
		log.error("error message..");
		log.fatal("fatal message..");

	}

}