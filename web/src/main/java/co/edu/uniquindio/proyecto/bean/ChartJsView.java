package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@ViewScoped
public class ChartJsView implements Serializable {

    @Getter@Setter
    private DonutChartModel donutModel;

    @Getter @Setter
    private BarChartModel barModel;

    @Getter @Setter
    private BarChartModel barModel2;

    @Getter @Setter
    private PieChartModel pieModel;


    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostConstruct
    public void inicializar(){
        createDonutModel();
        createBarModel();
        createBarModel2();
        createPieModel();
    }

    private void createPieModel() {
        List<Object[]> listaProductos = ciudadServicio.listarProductosCiudades();
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        listaProductos.forEach(p -> values.add((Number) p[1]));
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgba(255, 0, 0, 0.7)");
        bgColors.add("rgba(255, 96, 0, 0.7)");
        bgColors.add("rgba(255, 247, 0, 0.7)");
        bgColors.add("rgba(113, 255, 0, 0.7)");
        bgColors.add("rgba(0, 255, 238, 0.7)");
        dataSet.setBackgroundColor(bgColors);



        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        listaProductos.forEach(p->labels.add((String) p[0]));
        data.setLabels(labels);

        pieModel.setData(data);
    }

    public void createBarModel2() {
        List<Object[]> listaProductos = productoServicio.listarProductoVendidos();
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Producto más vendido");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(int i=0; i<5;i++){
            Object[] objeto = listaProductos.get(i);
            values.add((Number) objeto[1]);
            labels.add((String) objeto[0]);
        }
        barDataSet.setData(values);
        data.setLabels(labels);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 0, 0, 0.7)");
        bgColor.add("rgba(255, 197, 82, 0.7)");
        bgColor.add("rgba(255, 251, 0, 0.7)");
        bgColor.add("rgba(109, 255, 0, 0.7)");
        bgColor.add("rgba(0, 255, 245, 0.7)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        barModel2.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Ranking de los 5 productos más vendidos");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel2.setOptions(options);
    }


    public void createDonutModel() {
        List<Object[]> listaProductos = productoServicio.productosPorCategoria();
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for(int i = 0;i<listaProductos.size(); i++){
            Object [] objeto = listaProductos.get(i);
            values.add((Number) objeto[1]);
            labels.add((String) objeto[0]);
        }

        dataSet.setData(values);
        data.setLabels(labels);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(18, 210, 181)");
        bgColors.add("rgb(210,18, 192)");
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);
        donutModel.setData(data);
    }


    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        List<Object[]> listaProductosUsuario = usuarioServicio.listarProductosUsuario();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Productos por usuario");

        List<String> labels = new ArrayList<>();
        List<Number> values = new ArrayList<>();

        for(int i = 0;i<listaProductosUsuario.size(); i++){
            Object [] objeto = listaProductosUsuario.get(i);
            values.add((Number) objeto[1]);
            labels.add((String) objeto[0]);
        }

        barDataSet.setData(values);
        data.setLabels(labels);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);


        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Grafico de barras");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }
}