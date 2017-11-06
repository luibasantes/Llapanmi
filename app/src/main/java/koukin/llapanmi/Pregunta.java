package koukin.llapanmi;

/**
 * Created by pc on 6/11/2017.
 */

public class Pregunta {
    String pregunta,respuesta,opcionB,opcionC,opcionD,explicacion;

    public Pregunta(String pregunta, String respuesta, String opcionB, String opcionC, String opcionD, String explicacion) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.explicacion = explicacion;
    }

    public Pregunta(){}

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public void setOpcionB(String opcionB) {
        this.opcionB = opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public void setOpcionC(String opcionC) {
        this.opcionC = opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    public void setOpcionD(String opcionD) {
        this.opcionD = opcionD;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
}
