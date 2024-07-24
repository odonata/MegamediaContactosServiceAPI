package cl.megamedia.contactos.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstadoDelClimaResponse {

    @JsonProperty("imagen")
    private byte[] imagen;

    @JsonProperty("datos")
    private ClimaDatos datos;

    public EstadoDelClimaResponse() {
    }

    public EstadoDelClimaResponse(byte[] imagen, ClimaDatos datos) {
        this.imagen = imagen;
        this.datos = datos;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public ClimaDatos getDatos() {
        return datos;
    }

    public void setDatos(ClimaDatos datos) {
        this.datos = datos;
    }

    public static class ClimaDatos {

        @JsonProperty("temperatura")
        private Double temperatura;

        @JsonProperty("temperatura_maxima")
        private Double temperaturaMaxima;

        @JsonProperty("temperatura_minima")
        private Double temperaturaMinima;

        @JsonProperty("humedad")
        private Double humedad;

        @JsonProperty("presion")
        private Double presion;

        @JsonProperty("visibilidad")
        private Double visibilidad;

        @JsonProperty("estado_clima")
        private String estadoClima;

        @JsonProperty("velocidad_viento")
        private Double velocidadViento;

        public ClimaDatos() {
        }

        public ClimaDatos(Double temperatura, Double temperaturaMaxima, Double temperaturaMinima, Double humedad, Double presion, Double visibilidad, String estadoClima, Double velocidadViento) {
            this.temperatura = temperatura;
            this.temperaturaMaxima = temperaturaMaxima;
            this.temperaturaMinima = temperaturaMinima;
            this.humedad = humedad;
            this.presion = presion;
            this.visibilidad = visibilidad;
            this.estadoClima = estadoClima;
            this.velocidadViento = velocidadViento;
        }

        public Double getTemperatura() {
            return temperatura;
        }

        public void setTemperatura(Double temperatura) {
            this.temperatura = temperatura;
        }

        public Double getTemperaturaMaxima() {
            return temperaturaMaxima;
        }

        public void setTemperaturaMaxima(Double temperaturaMaxima) {
            this.temperaturaMaxima = temperaturaMaxima;
        }

        public Double getTemperaturaMinima() {
            return temperaturaMinima;
        }

        public void setTemperaturaMinima(Double temperaturaMinima) {
            this.temperaturaMinima = temperaturaMinima;
        }

        public Double getHumedad() {
            return humedad;
        }

        public void setHumedad(Double humedad) {
            this.humedad = humedad;
        }

        public Double getPresion() {
            return presion;
        }

        public void setPresion(Double presion) {
            this.presion = presion;
        }

        public Double getVisibilidad() {
            return visibilidad;
        }

        public void setVisibilidad(Double visibilidad) {
            this.visibilidad = visibilidad;
        }

        public String getEstadoClima() {
            return estadoClima;
        }

        public void setEstadoClima(String estadoClima) {
            this.estadoClima = estadoClima;
        }

        public Double getVelocidadViento() {
            return velocidadViento;
        }

        public void setVelocidadViento(Double velocidadViento) {
            this.velocidadViento = velocidadViento;
        }
    }
}