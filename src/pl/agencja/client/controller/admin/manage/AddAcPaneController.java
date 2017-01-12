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
import pl.agencja.client.controller.PolicyPaneController;

public class AddAcPaneController implements Initializable
{
	@FXML
	private Button backAddAcButton;

	@FXML
	private Pane contentPane;

	PolicyPaneController policyPaneController;

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
		backAddAcButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/menu/PolicyPane.fxml"));
					loader.setController(new PolicyPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					policyPaneController = (PolicyPaneController) loader.getController();
					policyPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

}
