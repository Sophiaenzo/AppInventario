package util;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class MensagemUsuario {

    public static void mostrarMensagemParaUsuario(String Titulo, String Mensagem, String ButtonText, Context context){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(Titulo);
        dialog.setMessage(Mensagem);
        dialog.setPositiveButton(ButtonText, null);
        AlertDialog alertDialogA = dialog.create();
        alertDialogA.show();
    }

    public static void mensagemTemporariaParaUsuario(String Mensagem, Context context){
        Toast toast = Toast.makeText(context.getApplicationContext(),Mensagem, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
