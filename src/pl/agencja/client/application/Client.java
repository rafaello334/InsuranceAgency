package pl.agencja.client.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.agencja.client.controller.LoginPaneController;

public class Client extends Application
{
	public static final int PORT = 1001;
	public static final String HOST = "localhost";
	private LoginPaneController loginPaneController;

	@Override
	public void start(Stage primaryStage)
	{
		final String appName = "Agencja Ubezpieczeniowa v0.1";

		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/agencja/client/view/start/LoginPane.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setTitle(appName);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest((WindowEvent we) -> {
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Ostrze¿enie");
				a.setHeaderText("Czy na pewno chcesz zakoñczyæ program?");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK)
					{
						Platform.exit();
					} else
					{
						we.consume();
					}
				});
			});

			loginPaneController = (LoginPaneController) loader.getController();
			loginPaneController.setPrevStage(primaryStage);

			System.out.println(loginPaneController);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
