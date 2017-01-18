package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.PolicyPaneController;
import pl.agencja.client.controller.admin.manage.policy.check.CheckTravelPolicyPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.policy.TravelPolicy;

public class AddTravelPaneController implements Initializable
{
	@FXML
	private Button checkPolicyButton;

	@FXML
	private Button backAddTravelButton;

	@FXML
	private TextField destinationTextField;

	@FXML
	private Label errorDestinationLabel;

	@FXML
	private Button backAddTravelcButton;

	@FXML
	private TextField valueTextField;

	@FXML
	private DatePicker finishInsuranceDatePicker;

	@FXML
	private Label errorFinishInsuranceLabel;

	@FXML
	private Button acceptButton;

	@FXML
	private Label nameLabel;

	@FXML
	private Label errorValueLabel;

	@FXML
	private Pane contentPane;

	PolicyPaneController policyPaneController;
	CheckTravelPolicyPaneController checkTravelPolicyPaneController;

	TravelPolicy travelPolicy;
	boolean addTravelPolicy;
	boolean destinationCorrect;
	boolean valueCorrect;

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
		backAddTravelButton.setOnAction(new EventHandler<ActionEvent>()
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

		checkPolicyButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/pl/agencja/client/view/admin/policy/manage/CheckTravelPolicyPane.fxml"));
					loader.setController(new CheckTravelPolicyPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					checkTravelPolicyPaneController = (CheckTravelPolicyPaneController) loader.getController();
					checkTravelPolicyPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		acceptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				addTravelPolicy = false;
				destinationCorrect = false;
				valueCorrect = false;

				if (valueTextField.getText().equals(""))
				{

					errorValueLabel.setText("Podaj wartosc!");
				} else if (!isNumeric(valueTextField.getText()))
				{
					errorValueLabel.setText("Nieprawid³owa wartoœæ!");
				} else
				{
					valueCorrect = true;
					errorValueLabel.setText("");
				}

				if (destinationTextField.getText().equals(""))
				{
					errorDestinationLabel.setText("Podaj cel podró¿y!");
				} else if (isNumeric(destinationTextField.getText()))
				{
					errorDestinationLabel.setText("Nieprawid³owa wartoœæ!");
				} else
				{
					destinationCorrect = true;
					errorDestinationLabel.setText("");
				}

				if (finishInsuranceDatePicker.getValue() == null)
				{
					errorFinishInsuranceLabel.setText("Wybierz date zakonczenia umowy!!");
				} else
				{
					errorFinishInsuranceLabel.setText("");
				}

				if (valueCorrect && destinationCorrect)
					addTravelPolicy = true;

				if (addTravelPolicy && (!valueTextField.getText().isEmpty() && !destinationTextField.getText().isEmpty()
						&& !finishInsuranceDatePicker.getValue().equals(null)))
				{
					travelPolicy = new TravelPolicy();

					travelPolicy.setDestination(destinationTextField.getText());
					travelPolicy.setPrice(Double.parseDouble(valueTextField.getText()));
					travelPolicy.setStartPolicyDate(LocalDate.now());
					travelPolicy.setFinishPolicyDate(finishInsuranceDatePicker.getValue());

					HibernateUtil.entityManager.getTransaction().begin();
					HibernateUtil.entityManager.persist(travelPolicy);
					HibernateUtil.entityManager.getTransaction().commit();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Dodawanie polisy");
					alert.setHeaderText(null);
					alert.setContentText("Dodanie polisy OC zakoñczy³o siê sukcesem.\n");
					alert.showAndWait();

					destinationTextField.setText("");
					valueTextField.setText("");
					finishInsuranceDatePicker.setValue(null);

				} else
				{

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³¹d dodawania");
					alert.setHeaderText(null);
					alert.setContentText("Nie mo¿na dodaæ polisy podró¿y. \nPopraw dane w formularzu!");
					alert.showAndWait();

				}
			}
		});
	}

	public static boolean isNumeric(String str)
	{
		try
		{
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}
}
