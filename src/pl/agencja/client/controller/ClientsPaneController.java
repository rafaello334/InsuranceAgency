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
import pl.agencja.client.controller.admin.manage.AddClientPaneController;
import pl.agencja.client.controller.admin.manage.CheckClientPaneController;
import pl.agencja.client.controller.admin.manage.ModifyClientPaneController;

public class ClientsPaneController implements Initializable
{
	@FXML
	private Button checkClientButton;

	@FXML
	private Button addClientButton;

	@FXML
	private Button modifyClientButton;

	@FXML
	private Pane contentPane;

	AddClientPaneController addClientPaneController;
	CheckClientPaneController checkClientPaneController;
	ModifyClientPaneController modifyClientPaneController;

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
		addClientButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/admin/clients/manage/AddClientPane.fxml"));
					loader.setController(new AddClientPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addClientPaneController = (AddClientPaneController) loader.getController();
					addClientPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		modifyClientButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/pl/agencja/client/view/admin/clients/manage/ModifyClientPane.fxml"));
					loader.setController(new ModifyClientPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					modifyClientPaneController = (ModifyClientPaneController) loader.getController();
					modifyClientPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		checkClientButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/pl/agencja/client/view/admin/clients/manage/CheckClientPane.fxml"));
					loader.setController(new CheckClientPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					checkClientPaneController = (CheckClientPaneController) loader.getController();
					checkClientPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
