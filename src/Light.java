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

public class Light {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static void main(String[] args) throws Exception {
        log("Program starting");

        try (EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1)) {
            SensorMode sensorMode = colorSensor.getAmbientMode();
            int sampleSize = sensorMode.sampleSize();
            log("Sample size = " + sampleSize);
            float[] sampleValue = new float[sampleSize];

            try (FileWriter csvFileWriter = new FileWriter("samples.csv")) {
                for (int i = 1; i < 11; i++) {
                    sensorMode.fetchSample(sampleValue, 0);
                    log(i + ". Ambient light = " + sampleValue[0]);
                    writeCsv(csvFileWriter, sampleValue[0]);
                    Delay.msDelay(1000);
                }
            }
        }
        log("Program ending");
    }

    private static void writeCsv(Writer writer, float value) throws IOException {
        String dateTime = DATE_FORMAT.format(new Date());
        writer.append(dateTime).append(",").append(Float.toString(value)).append("\n");
    }

    private static void log(String msg) {
        System.out.println("log: " + msg);
    }
}
