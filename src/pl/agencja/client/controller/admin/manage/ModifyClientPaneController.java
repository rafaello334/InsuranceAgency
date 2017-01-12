package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.ClientsPaneController;

public class ModifyClientPaneController implements Initializable
{
	@FXML
	private Button backModifyClientButton;

	@FXML
	private Pane contentPane;

	ClientsPaneController clientsPaneController;

	public void setContentPane(Pane contentPane)
	{
		this.contentPane = contentPane;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		configureButtons();
	}

	private void configureButtons()
	{
		backModifyClientButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/menu/ClientsPane.fxml"));
					loader.setController(new ClientsPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					clientsPaneController = (ClientsPaneController) loader.getController();
					clientsPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

}
