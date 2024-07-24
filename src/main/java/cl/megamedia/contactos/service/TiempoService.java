package cl.megamedia.contactos.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.megamedia.contactos.to.EstadoDelClimaResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * Clase de servicio para lñlamar a la apirest del 
 * tiempo en : https://api.openweathermap.org/data/2.5/weather
 * 
 * @author Gonzalo Silva
 *
 */
@Service
public class TiempoService {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public TiempoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Generar la imagen en bytes del grafico del estado del tiempo
     * @param ciudad
     * @return
     */
    public EstadoDelClimaResponse generarGrafico(String ciudad) {
        String jsonData = obtenerEstadoDelTiempo(ciudad);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> data = mapper.readValue(jsonData, Map.class);

            Map<String, Object> main = (Map<String, Object>) data.get("main");
            Map<String, Object> weather = ((List<Map<String, Object>>) data.get("weather")).get(0);
            if (main == null || weather == null) {
                throw new RuntimeException("Datos principales o clima no encontrados en la respuesta JSON.");
            }

            Double temperatura = convertToDouble(main.get("temp"));
            Double tempMax = convertToDouble(main.get("temp_max"));
            Double tempMin = convertToDouble(main.get("temp_min"));
            Double humedad = convertToDouble(main.get("humidity"));
            Double presion = convertToDouble(main.get("pressure"));
            Double visibilidad = convertToDouble(data.get("visibility"));
            String descripcionClima = (String) weather.get("description");
            Double velocidadViento = convertToDouble(((Map<String, Object>) data.get("wind")).get("speed"));

            CategoryDataset dataset = crearDataset(temperatura, tempMax, tempMin);
            JFreeChart chart = ChartFactory.createBarChart(
                "Estado del Tiempo en " + ciudad,
                "Categoría",
                "Valor",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
            );

            // Configuración de colores
            BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
            renderer.setSeriesPaint(0, Color.RED);    // Color para Temperatura
            renderer.setSeriesPaint(1, Color.GREEN);  // Color para Temperatura Máxima
            renderer.setSeriesPaint(2, Color.BLUE);   // Color para Temperatura Mínima

            // Configurar etiquetas encima de las barras
            renderer.setDefaultItemLabelsVisible(true);
            renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            renderer.setDefaultItemLabelPaint(Color.BLACK);

            // Configuración de etiquetas visibles
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator(
                "{2}", NumberFormat.getInstance()
            ));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ChartUtils.writeChartAsPNG(baos, chart, 600, 400);  // Escribe el gráfico en PNG
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al generar la imagen del gráfico.", e);
            }

            // Crear y devolver el objeto de respuesta con los datos y la imagen
            EstadoDelClimaResponse.ClimaDatos climaDatos = new EstadoDelClimaResponse.ClimaDatos(
                temperatura, tempMax, tempMin, humedad, presion, visibilidad, descripcionClima, velocidadViento
            );

            return new EstadoDelClimaResponse(baos.toByteArray(), climaDatos);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al procesar los datos JSON.", e);
        }
    }

    private Double convertToDouble(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("No se pudo convertir el valor a Double: " + value);
            }
        }
        throw new IllegalArgumentException("Expected a Number or String but got: " + value.getClass().getName());
    }

    private CategoryDataset crearDataset(Double temperatura, Double tempMax, Double tempMin) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(temperatura, "Temperatura", "Actual");
        dataset.addValue(tempMax, "Temperatura Máxima", "Máxima");
        dataset.addValue(tempMin, "Temperatura Mínima", "Mínima");
        return dataset;
    }

    public String obtenerEstadoDelTiempo(String ciudad) {
        String url = String.format("%s?q=%s&appid=%s&units=metric&lang=es", weatherApiUrl, ciudad, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}