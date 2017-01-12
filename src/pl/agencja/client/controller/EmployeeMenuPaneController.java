package pl.agencja.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EmployeeMenuPaneController implements Initializable
{

	@FXML
	private ToggleButton manageClientsToggleButton;

	@FXML
	private ToggleButton changePasswordToggleButton;

	@FXML
	private Button logoutButton;

	@FXML
	private ToggleButton newPolicyToggleButton;

	@FXML
	private Pane contentPane;

	ToggleGroup toggleGroup;
	private Stage prevStage;

	EmployeeMenuPaneController adminMenuPaneController;
	LoginPaneController loginPaneController;
	ClientsPaneController clientsPaneController;
	PolicyPaneController policyPaneController;
	WelcomePaneController welcomePaneController;

	public void setPrevStage(Stage stage)
	{
		this.prevStage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		configureToggleGroup();
		configureButtons();
	}

	private void configureToggleGroup()
	{
		toggleGroup = new ToggleGroup();
		manageClientsToggleButton.setToggleGroup(toggleGroup);
		changePasswordToggleButton.setToggleGroup(toggleGroup);
		newPolicyToggleButton.setToggleGroup(toggleGroup);

	}

	private void changeSceneToWelcomePane()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/pl/agencja/client/view/menu/WelcomePane.fxml"));
			loader.setController(new WelcomePaneController());
			contentPane.getChildren().clear();
			contentPane.getChildren().add(loader.load());

			welcomePaneController = (WelcomePaneController) loader.getController();
			welcomePaneController.setContentPane(contentPane);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private void changeSceneToLogin(Stage prevStage)
	{
		try
		{
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Menu G³ówne");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/agencja/client/view/start/LoginPane.fxml"));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			prevStage.close();
			primaryStage.show();

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

		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private void configureButtons()
	{

		logoutButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Ostrze¿enie");
				a.setHeaderText("Czy na pewno wylogowaæ?");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK)
					{
						changeSceneToLogin(prevStage);
						System.out.println("Wylogowano");
					} else
					{
						event.consume();
					}
				});
			}
		});

		newPolicyToggleButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					if (newPolicyToggleButton.isSelected())
					{
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("/pl/agencja/client/view/menu/PolicyPane.fxml"));
						loader.setController(new PolicyPaneController());
						contentPane.getChildren().clear();
						contentPane.getChildren().add(loader.load());

						policyPaneController = (PolicyPaneController) loader.getController();
						policyPaneController.setContentPane(contentPane);
					} else
					{
						changeSceneToWelcomePane();
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		manageClientsToggleButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					if (manageClientsToggleButton.isSelected())
					{
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("/pl/agencja/client/view/menu/ClientsPane.fxml"));
						loader.setController(new ClientsPaneController());
						contentPane.getChildren().clear();
						contentPane.getChildren().add(loader.load());

						clientsPaneController = (ClientsPaneController) loader.getController();
						clientsPaneController.setContentPane(contentPane);
					} else
					{
						changeSceneToWelcomePane();
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		changePasswordToggleButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					if (changePasswordToggleButton.isSelected())
					{
						FXMLLoader loader = new FXMLLoader(
								getClass().getResource("/pl/agencja/client/view/menu/ChangePasswordPane.fxml"));
						loader.setController(new ChangePasswordPaneController());
						contentPane.getChildren().clear();
						contentPane.getChildren().add(loader.load());
					} else
					{
						changeSceneToWelcomePane();
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

}
