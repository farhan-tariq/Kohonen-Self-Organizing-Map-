package SimulationNetworkCore;

import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.List;

public class NodeWeightsLabel {
    private WebView webView;
    private WebEngine webEngine;
    private List<InputNode> fromNodeList;
    private OutputNode toNode;
    private StringBuilder builder = new StringBuilder();

    public NodeWeightsLabel(List<InputNode> nodeList, OutputNode toNode) {
        this.fromNodeList = nodeList;
        this.toNode = toNode;
    }


    public void setLabelText() {
        String to = String.valueOf(toNode.getID());
        builder.append("<html><body> Node ");
        builder.append(to);
        builder.append(" (");

        for (int i = 0; i < fromNodeList.size(); i++) {
            InputNode fromNode = fromNodeList.get(i);
            String from = String.valueOf(fromNode.getID());
            builder.append(" w<sub>");
            builder.append(to);
            builder.append(",");
            builder.append(from);
            builder.append("</sub>");
            if (i % 2 == 0)
                builder.append(" | ");
        }
        builder.append(" ) </body></html>");
        webEngine.loadContent(builder.toString());
        changeListener(webEngine);
    }

    private void changeListener(WebEngine engine) {
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                Document document = engine.getDocument();
                Element styleNode = document.createElement("style");
                Text styleContent = document.createTextNode("body {" +
                        "color:white;" +
                        "background-color:#454545;" +
                        "font-size:14" +
                        "}");
                styleNode.appendChild(styleContent);
                document.getDocumentElement().getElementsByTagName("body").item(0).appendChild(styleNode);
            }
        });
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
        this.webEngine = this.webView.getEngine();
    }

    public WebView getWebView() {
        webView.setPrefSize(200, 50);
        return webView;
    }
}
