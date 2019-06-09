package Controllers.Learning.Simulation.Helper;

import SimulationNetworkCore.*;
import NetworkCore.InputVector;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;


import java.util.ArrayList;


public class Helper {
    /**
     * Method to create HBox with essential parameters
     *
     * @param node child to add to the hBox
     * @return hBox
     */
    private static HBox createHBox(javafx.scene.Node node) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(50);
        hBox.setPrefWidth(720);
        if (node != null) {
            hBox.getChildren().add(node);
        }
        return hBox;
    }

    /**
     * Method to create input and output Nodes
     *
     * @param inputNodeArrayList  input Node List
     * @param outputNodeArrayList output Node List
     *                            {@see createInputNodes(inputNodeArrayList)}
     *                            {@see createOutputNodes(inputNodeArrayList)}
     */
    public static void createNodes(ArrayList<InputNode> inputNodeArrayList, ArrayList<OutputNode> outputNodeArrayList) {
        createInputNodes(inputNodeArrayList);
        createOutputNodes(outputNodeArrayList);
    }


    /**
     * Method to create output Nodes
     *
     * @param outputNodeArrayList output Node List
     */
    private static void createOutputNodes(ArrayList<OutputNode> outputNodeArrayList) {
        int layoutX = 280;
        int layoutY = 240;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                OutputNode outputNode = new OutputNode(1);
                outputNode.setXPos(y);
                outputNode.setYPos(x);
                outputNode.setCenterX(layoutX);
                outputNode.setCenterY(layoutY);
                outputNodeArrayList.add(outputNode);
                layoutX += 150;
            }
            layoutY += 120;
            layoutX = 280;
        }
    }

    /**
     * Method to create input Nodes
     *
     * @param inputNodeArrayList input Node List
     */
    private static void createInputNodes(ArrayList<InputNode> inputNodeArrayList) {
        int layoutX = 120;
        int layoutY = 350;
        for (int i = 0; i < 2; i++) {
            InputVector inputVector = new InputVector();
            InputNode inputNode = new InputNode(inputVector);
            inputNode.setCenterX(layoutX);
            inputNode.setCenterY(layoutY);
            inputNodeArrayList.add(inputNode);
            layoutY += 120;

        }
    }


    /**
     * Method to return a new generated Region
     *
     * @param height height of the region
     * @param width  width of the region
     * @return region
     */
    public static Region returnNewRegion(double height, double width) {
        Region region = new Region();
        region.setPrefHeight(height);
        region.setPrefWidth(width);
        return region;
    }

    /**
     * Method to create a new line and set its starting and ending points
     *
     * @param inputNode  input Node
     * @param outputNode output Node
     * @return
     */
    private static BoundLine createLine(InputNode inputNode, OutputNode outputNode) {
        return new BoundLine(BoundLine.getLineStartX(inputNode)
                , BoundLine.getLineStartY(inputNode)
                , BoundLine.getLineEndX(outputNode)
                , BoundLine.getLineEndY(outputNode));
    }


    /**
     * Method to create lines and add to arrayList
     *
     * @param inputNodeArrayList  inputNodeArrayList
     * @param outputNodeArrayList outputNodeArrayList
     * @param lineArrayList       line ArrayList
     */
    public static void generateLines(ArrayList<InputNode> inputNodeArrayList, ArrayList<OutputNode> outputNodeArrayList, ArrayList<BoundLine> lineArrayList) {
        for (int i = 1; i < inputNodeArrayList.size() + 1; i++) {
            InputNode inputNode = inputNodeArrayList.get(i - 1);
            for (int j = 1; j < outputNodeArrayList.size() + 1; j++) {
                OutputNode outputNode = outputNodeArrayList.get(j - 1);
                BoundLine line = createLine(inputNode, outputNode);
                line.setFrom(inputNode);
                line.setTo(outputNode);
                line.setNum(j - 1);
                outputNode.addLine(line, i + j);
                lineArrayList.add(line);
            }
        }
    }

    /**
     * @param inputIndex
     * @param weightIndex
     * @return
     */
    public static LatexFormula createLatexFormula(int inputIndex, int weightIndex) {
        return new LatexFormula(inputIndex, weightIndex);
    }


    /**
     * Method to add input and output nodes to a pane
     *
     * @param inputNodeArrayList  Input Nodes
     * @param outputNodeArrayList Output Nodes
     * @param nodePane            Pane to be added to
     *                            {@see addInputNodes()}
     *                            {@see addOutputNodes()}
     */
    public static void addNodesToPane(AnchorPane nodePane, ArrayList<InputNode> inputNodeArrayList, ArrayList<OutputNode> outputNodeArrayList) {
        addInputNodes(nodePane, inputNodeArrayList);
        addOutputNodes(nodePane, outputNodeArrayList);
    }


    /**
     * Method to add generated input nodes to the networkPane
     *
     * @param networkPane        network Pane
     * @param inputNodeArrayList input Node List
     */
    private static void addInputNodes(AnchorPane networkPane, ArrayList<InputNode> inputNodeArrayList) {
        for (InputNode inputNode : inputNodeArrayList) {
            networkPane.getChildren().add(inputNode);
        }
    }


    /**
     * Method to add generated output nodes to the networkPane
     *
     * @param networkPane         network Pane
     * @param outputNodeArrayList output Node List
     */
    private static void addOutputNodes(AnchorPane networkPane, ArrayList<OutputNode> outputNodeArrayList) {
        for (OutputNode node : outputNodeArrayList) {
            networkPane.getChildren().add(node);
        }
    }


    /**
     * @param formulaPane         formulaPane for displaying formula
     * @param outputNodeArrayList outputNodeArrayList
     * @param webViewArrayList    list containing webView
     *                            {@see initFormulaPane(formulaPane, outputNodeArrayList)}
     *                            {@see addWebViews(formulaPane, outputNodeArrayList, webViewArrayList)}
     */
    public static void initialize(VBox formulaPane, ArrayList<OutputNode> outputNodeArrayList, ArrayList<WebView> webViewArrayList) {
        initFormulaPane(formulaPane, outputNodeArrayList);
        addWebViews(formulaPane, outputNodeArrayList, webViewArrayList);
    }


    /**
     * Method to add webViews to the initialized formula Pane
     *
     * @param formulaPane         formula Pane
     * @param outputNodeArrayList outNodes list
     * @param webViewArrayList    webView list
     */
    private static void addWebViews(VBox formulaPane, ArrayList<OutputNode> outputNodeArrayList, ArrayList<WebView> webViewArrayList) {
        for (int i = 1; i < outputNodeArrayList.size() + 1; i++) {
            OutputNode outputNode = outputNodeArrayList.get(i - 1);
            NodeWeightsLabel label = new NodeWeightsLabel(outputNode.getInputNodes(), outputNode);
            label.setWebView(webViewArrayList.get(i - 1));
            label.setLabelText();

            javafx.scene.Node node = formulaPane.getChildren().get(i);
            HBox hBox = (HBox) node;
            hBox.getChildren().add(label.getWebView());
        }
    }


    /**
     * Method to initialize formula pane with HBoxes
     *
     * @param formulaPane         formulaPane to show each nodes input and weight
     * @param outputNodeArrayList outputNodeArrayList
     */
    private static void initFormulaPane(VBox formulaPane, ArrayList<OutputNode> outputNodeArrayList) {
        formulaPane.getChildren().add(createHBox(returnNewRegion(60, 720)));

        for (int i = 0; i < outputNodeArrayList.size(); i++) {
            HBox hBox = createHBox(null);
            hBox.setStyle("-fx-border-color: #373839;-fx-border-radius: 10; -fx-border-insets: 6 0 6 0; ");
            formulaPane.getChildren().add(hBox);
        }
        formulaPane.getChildren().add(createHBox(returnNewRegion(60, 720)));
    }


    /**
     * Method to initialize ArrayLists
     *
     * @param outputNodeArrayList outputNodeArrayList representing circles/output neurons
     * @param webViewArrayList    webViewArrayList
     * @param imageViewArrayList  imageViewArrayList
     *                            {@see initWebViewArrayList() }
     */
    public static void initArrayLists(ArrayList<OutputNode> outputNodeArrayList, ArrayList<WebView> webViewArrayList, ArrayList<ImageView> imageViewArrayList) {
        initWebViewArrayList(outputNodeArrayList, webViewArrayList);
    }


    /**
     * Method to init webView and add to List
     *
     * @param outputNodeArrayList output Node List
     * @param webViewArrayList    web View List
     */
    private static void initWebViewArrayList(ArrayList<OutputNode> outputNodeArrayList, ArrayList<WebView> webViewArrayList) {
        for (int i = 0; i < outputNodeArrayList.size(); i++) {
            WebView webView = new WebView();
            webView.setPrefHeight(50);
            webView.setPrefWidth(175);
            webViewArrayList.add(webView);
        }
    }


    /**
     * Method to add and display each node's ID
     *
     * @param inputNodeArrayList  input Node List
     * @param outputNodeArrayList output Node List
     * @param networkPane         network Pane
     */
    public static void setNodeIDLabel(ArrayList<InputNode> inputNodeArrayList, ArrayList<OutputNode> outputNodeArrayList, AnchorPane networkPane) {
        setLabels(inputNodeArrayList, outputNodeArrayList, 's', networkPane);
        setLabels(inputNodeArrayList, outputNodeArrayList, 'e', networkPane);
    }


    /**
     * Method to generate text to add to each node for identification
     *
     * @param node        Node
     * @param type        type of node
     * @param networkPane networkPane
     */
    private static void generateText(Node node, char type, AnchorPane networkPane) {
        Text text = new Text();
        if (type == 's') text.setText("Input " + node.getID());
        else text.setText("Output\n Node" + node.getID());

        double radius = node.getRadius();
        text.setLayoutX(node.getCenterX() - (radius / 2));
        text.setLayoutY(node.getCenterY() - (radius / 3));
        networkPane.getChildren().add(text);
    }


    /**
     * Method to set ID to each node
     *
     * @param inputNodeArrayList  input Node List
     * @param outputNodeArrayList output Node List
     * @param type                type of node
     * @param networkPane         pane to add the label to
     */
    private static void setLabels(ArrayList<InputNode> inputNodeArrayList, ArrayList<OutputNode> outputNodeArrayList, char type, AnchorPane networkPane) {
        if (type == 's') {
            for (InputNode node : inputNodeArrayList) {
                generateText(node, type, networkPane);
            }
        } else {
            for (OutputNode node : outputNodeArrayList) {
                generateText(node, type, networkPane);
            }
        }
    }


    /**
     * Method to set each input Output nodes ID for identification
     *
     * @param inputNodeArrayList  input Nodes
     * @param outputNodeArrayList output Nodes
     */
    public static void setNodeID(ArrayList<InputNode> inputNodeArrayList, ArrayList<OutputNode> outputNodeArrayList) {
        for (int i = 0; i < 18; i++) {
            if (i == 0 || i == 1) {
                inputNodeArrayList.get(i).setID(i + 1);
            } else {
                for (InputNode inputNode : inputNodeArrayList) outputNodeArrayList.get(i - 2).addInputNode(inputNode);
                outputNodeArrayList.get(i - 2).setID(i - 1);
            }
        }
    }


    /**
     * Method to add input Values to each inputNode
     *
     * @param inputNodeArrayList inputNode
     */
    public static void setInputVectors(ArrayList<InputNode> inputNodeArrayList) {
        InputVector vector = new InputVector();
        vector.addElement(0.5);

        InputVector vector2 = new InputVector();
        vector2.addElement(1.0);

        inputNodeArrayList.get(0).setInputVector(vector);
        inputNodeArrayList.get(1).setInputVector(vector2);
    }
}
