import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class Light {
    public static void main(String[] args) {
        log("Program starting");

        EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
        SensorMode sensorMode = colorSensor.getAmbientMode();
        int sampleSize = sensorMode.sampleSize();
        log("Sample size = " + sampleSize);
        float[] sampleValue = new float[sampleSize];

        for (int i = 1; i < 11; i++) {
            sensorMode.fetchSample(sampleValue, 0);
            log(i + ". Ambient light = " + sampleValue[0]);
            Delay.msDelay(1000);
        }
        colorSensor.close();

        log("Program ending");
    }

    private static void log(String msg) {
        System.out.println("log: " + msg);
    }
}
