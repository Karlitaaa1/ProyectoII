/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;
/**
 *
 * @author kbren
 */
public class Tarea {
    private String IdCliente;
    private TipoOperacion tipoOperacion;
    private Object parametros;
    private String estado;
    private Resultado resultado;

    // Clase interna Resultado
    public static class Resultado {
        private boolean exito;
        private String mensaje;
        private Object datos;

        public Resultado(boolean exito, String mensaje, Object datos) {
            this.exito = exito;
            this.mensaje = mensaje;
            this.datos = datos;
        }

        public boolean isExito() { return exito; }
        public void setExito(boolean exito) { this.exito = exito; }

        public String getMensaje() { return mensaje; }
        public void setMensaje(String mensaje) { this.mensaje = mensaje; }

        public Object getDatos() { return datos; }
        public void setDatos(Object datos) { this.datos = datos; }
    }

    // Getters y setters para Tarea
    public String getIdCliente() { return IdCliente; }
    public void setIdCliente(String IdCliente) { this.IdCliente = IdCliente; }

    public TipoOperacion getTipoOperacion() { return tipoOperacion; }
    public void setTipoOperacion(TipoOperacion tipoOperacion) { this.tipoOperacion = tipoOperacion; }

    public Object getParametros() { return parametros; }
    public void setParametros(Object parametros) { this.parametros = parametros; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Resultado getResultado() { return resultado; }
    public void setResultado(Resultado resultado) { this.resultado = resultado; }
}


