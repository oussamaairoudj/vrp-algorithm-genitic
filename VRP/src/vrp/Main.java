package vrp;
	
import java.io.IOException;
import java.util.stream.Stream;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Main extends Application {
	static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage=primaryStage;
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.setTitle("VRP");
			primaryStage.getIcons().add(new Image("/icon/icon.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 static void setborder(Pane pane) {

	        Color[] colors = Stream.of("darkorange", "tomato", "deeppink", "blueviolet", "steelblue", "cornflowerblue", "lightseagreen", "#6fba82", "chartreuse", "crimson")
	                .map(Color::web)
	                .toArray(Color[]::new);
	        int mills[] = {-200};
	        KeyFrame keyFrames[]  = Stream.iterate(0, i -> i+1)
	                .limit(100)
	                .map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, colors[i%colors.length]), new Stop(1, colors[(i+1)%colors.length])}))
	                .map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2))))
	                .map(b -> new KeyFrame(Duration.millis(mills[0]+=200), new KeyValue(pane.borderProperty(), b, Interpolator.EASE_IN)))
	                .toArray(KeyFrame[]::new);
	        
	        Timeline timeline = new Timeline(keyFrames);
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
	    }
	 
	 static void setborder(AnchorPane pane) {

	        Color[] colors = Stream.of("darkorange", "tomato", "deeppink", "blueviolet", "steelblue", "cornflowerblue", "lightseagreen", "#6fba82", "chartreuse", "crimson")
	                .map(Color::web)
	                .toArray(Color[]::new);
	        int mills[] = {-200};
	        KeyFrame keyFrames[]  = Stream.iterate(0, i -> i+1)
	                .limit(100)
	                .map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, colors[i%colors.length]), new Stop(1, colors[(i+1)%colors.length])}))
	                .map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2))))
	                .map(b -> new KeyFrame(Duration.millis(mills[0]+=200), new KeyValue(pane.borderProperty(), b, Interpolator.EASE_IN)))
	                .toArray(KeyFrame[]::new);
	        
	        Timeline timeline = new Timeline(keyFrames);
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
	    }
	
	public static void main(String[] args) throws IOException{
		launch(args);
	}
}
