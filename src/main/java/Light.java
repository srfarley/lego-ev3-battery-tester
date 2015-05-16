import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Light {
    private static final DateFormat CSV_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final int SAMPLE_INTERVAL_MS = 1000;
    private static final float MIN_LIGHT_LEVEL = 0.03f;
    private static final String CSV_FILE_NAME = "samples.csv";

    public static void main(String[] args) throws Exception {
        final Logger logger = logger();
        try {
            Sound.beepSequenceUp();
            try (EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1)) {
                SensorMode sensorMode = colorSensor.getAmbientMode();
                int sampleSize = sensorMode.sampleSize();
                logger.info("Sample size = " + sampleSize);
//                if (sampleSize != 2) {
//                    throw new Exception("Expended sample size of 2, but was " + sampleSize);
//                }
                float[] sampleValue = new float[sampleSize];

                try (FileWriter csvFileWriter = new FileWriter(CSV_FILE_NAME)) {
                    float lightLevel = Float.MAX_VALUE;
                    while(lightLevel > MIN_LIGHT_LEVEL) {
                        sensorMode.fetchSample(sampleValue, 0);
                        lightLevel = sampleValue[0];
                        logger.info("ambient light = " + lightLevel);
                        writeCsv(csvFileWriter, lightLevel);
                        Delay.msDelay(SAMPLE_INTERVAL_MS);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "exception thrown from main", e);
        } finally {
            Sound.beepSequence();
        }
    }

    private static void writeCsv(Writer writer, float value) throws IOException {
        String dateTime = CSV_DATE_FORMAT.format(new Date());
        writer.append(dateTime).append(",").append(Float.toString(value)).append("\n");
    }

    private static Logger logger() throws IOException {
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(Light.class.getResourceAsStream("logging.properties"));
        Logger logger = Logger.getLogger(Light.class.getName());
        if (logger == null) throw new NullPointerException("logger");
        return logger;
    }
}
