package Controllers.Learning.Simulation.Helper;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.BlendMode;
import org.jfree.fx.FXGraphics2D;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LatexFormula extends Canvas {
    private TeXIcon teXIcon;
    private FXGraphics2D graphics2D;
    private DecimalFormat decimalFormat = new DecimalFormat("#.#####");


    /**
     * Constructor to generate the Formula
     */
    public LatexFormula(int nodeIndex, int inputNodeIndex) {
        graphics2D = new FXGraphics2D(getGraphicsContext2D());
        TeXFormula formula = new TeXFormula("Distance = \\sqrt{\\sum(x_{" + inputNodeIndex + "} - w_{" + nodeIndex + "," + inputNodeIndex + "})^2}   ");
        formula.setColor(Color.WHITE);
        teXIcon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        setWidth(180);
        setHeight(35);
        draw();
    }

    /***
     * Constructor to generate the formula using input and output values
     * */
    public LatexFormula(double inputValue, double weightValue) {
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        graphics2D = new FXGraphics2D(getGraphicsContext2D());
        TeXFormula formula = new TeXFormula("\\quad\\Longrightarrow  \\quad\\sqrt{\\sum({" + inputValue + "} - {" + decimalFormat.format(weightValue) + "})^2}");
        formula.setColor(Color.WHITE);
        teXIcon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        setWidth(200);
        setHeight(35);
        draw();
    }

    /**
     * Constructor to generate weight value label
     */
    public LatexFormula(double value) {
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        graphics2D = new FXGraphics2D(getGraphicsContext2D());
        TeXFormula formula = new TeXFormula("  =  {" + decimalFormat.format(value) + "}");
        formula.setColor(Color.WHITE);
        teXIcon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        setWidth(100);
        setHeight(35);
        draw();
    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();
        int texIconWidth = teXIcon.getIconWidth();
        int texIconHeight = teXIcon.getIconHeight();
        getGraphicsContext2D().clearRect(0, 0, width, height);

        BufferedImage image = new BufferedImage(texIconWidth, texIconHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gg = image.createGraphics();
        gg.setColor(Color.decode("#454545"));
        gg.fillRect(0, 0, teXIcon.getIconWidth(), teXIcon.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        teXIcon.paintIcon(jl, gg, 0, 0);
        graphics2D.drawImage(image, 0, 0, null);
    }
}
