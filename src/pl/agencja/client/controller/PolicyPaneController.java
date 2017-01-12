package pl.agencja.client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.admin.manage.AddAcPaneController;
import pl.agencja.client.controller.admin.manage.AddLifePaneController;
import pl.agencja.client.controller.admin.manage.AddOcPaneController;
import pl.agencja.client.controller.admin.manage.AddTravelPaneController;

public class PolicyPaneController implements Initializable
{

	@FXML
	private Button ocPolicyButton;

	@FXML
	private Button travelPolicyButton;

	@FXML
	private Button acPolicyButton;

	@FXML
	private Button lifePolicyButton;

	@FXML
	private Pane contentPane;

	AddAcPaneController addAcPaneController;
	AddOcPaneController addOcPaneController;
	AddLifePaneController addLifePaneController;
	AddTravelPaneController addTravelPaneController;

	public void setContentPane(Pane contentPane)
	{
		this.contentPane = contentPane;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		configureButtons();
	}

	private void configureButtons()
	{
		acPolicyButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/admin/policy/manage/AddAcPane.fxml"));
					loader.setController(new AddAcPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addAcPaneController = (AddAcPaneController) loader.getController();
					addAcPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		lifePolicyButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/admin/policy/manage/AddLifePane.fxml"));
					loader.setController(new AddLifePaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addLifePaneController = (AddLifePaneController) loader.getController();
					addLifePaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		travelPolicyButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/admin/policy/manage/AddTravelPane.fxml"));
					loader.setController(new AddTravelPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addTravelPaneController = (AddTravelPaneController) loader.getController();
					addTravelPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		ocPolicyButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/admin/policy/manage/AddOcPane.fxml"));
					loader.setController(new AddOcPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addOcPaneController = (AddOcPaneController) loader.getController();
					addOcPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
