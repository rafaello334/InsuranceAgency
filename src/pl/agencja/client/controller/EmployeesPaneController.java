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
import pl.agencja.client.controller.admin.manage.AddEmployeePaneController;
import pl.agencja.client.controller.admin.manage.CheckEmployeePaneController;
import pl.agencja.client.controller.admin.manage.DeleteEmployeePaneController;
import pl.agencja.client.controller.admin.manage.ModifyEmployeePaneController;

public class EmployeesPaneController implements Initializable
{

	@FXML
	private Button addEmployeeButton;

	@FXML
	private Button modifyEmployeeButton;

	@FXML
	private Button deleteEmployeeButton;

	@FXML
	private Button checkEmployeeButton;

	@FXML
	private Pane contentPane;

	AddEmployeePaneController addEmployeePaneController;
	CheckEmployeePaneController checkEmployeePaneController;
	DeleteEmployeePaneController deleteEmployeePaneController;
	ModifyEmployeePaneController modifyEmployeePaneController;

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
		addEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/pl/agencja/client/view/admin/employee/manage/AddEmployeePane.fxml"));
					loader.setController(new AddEmployeePaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addEmployeePaneController = (AddEmployeePaneController) loader.getController();
					addEmployeePaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		deleteEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/pl/agencja/client/view/admin/employee/manage/DeleteEmployeePane.fxml"));
					loader.setController(new DeleteEmployeePaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					deleteEmployeePaneController = (DeleteEmployeePaneController) loader.getController();
					deleteEmployeePaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		modifyEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/pl/agencja/client/view/admin/employee/manage/ModifyEmployeePane.fxml"));
					loader.setController(new ModifyEmployeePaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					modifyEmployeePaneController = (ModifyEmployeePaneController) loader.getController();
					modifyEmployeePaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		checkEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/pl/agencja/client/view/admin/employee/manage/CheckEmployeePane.fxml"));
					loader.setController(new CheckEmployeePaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					checkEmployeePaneController = (CheckEmployeePaneController) loader.getController();
					checkEmployeePaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
