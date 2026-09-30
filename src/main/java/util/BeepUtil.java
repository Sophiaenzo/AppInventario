package util;
import android.media.AudioManager;
import android.media.ToneGenerator;

public class BeepUtil {
    private static ToneGenerator toneGenerator;

    // Inicializa o ToneGenerator
    public static void initToneGenerator() {
        toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
    }

    // Gera um bip de sucesso
    public static void bipSucesso() {
        if (toneGenerator == null) {
            initToneGenerator();
        }
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }

    // Gera um bip de erro
    public static void bipErro() {
        if (toneGenerator == null) {
            initToneGenerator();
        }
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP, 200);
    }
}
