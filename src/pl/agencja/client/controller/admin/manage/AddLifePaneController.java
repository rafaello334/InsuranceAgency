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
import pl.agencja.client.controller.admin.manage.policy.check.CheckLifePolicyPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.policy.LifePolicy;

public class AddLifePaneController implements Initializable
{
	@FXML
	private Button checkPolicyButton;

	@FXML
	private TextField ageTextField;

	@FXML
	private Button backAddLifeButton;

	@FXML
	private Button checkLifeButton;

	@FXML
	private Label errorAgeLabel;

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

	CheckLifePolicyPaneController checkLifePolicyPaneController;
	LifePolicy lifePolicy;
	boolean addLifePolicy;
	boolean ageCorrect;
	boolean valueCorrect;

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
		backAddLifeButton.setOnAction(new EventHandler<ActionEvent>()
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
							.getResource("/pl/agencja/client/view/admin/policy/manage/CheckLifePolicyPane.fxml"));
					loader.setController(new CheckLifePolicyPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					checkLifePolicyPaneController = (CheckLifePolicyPaneController) loader.getController();
					checkLifePolicyPaneController.setContentPane(contentPane);
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
				addLifePolicy = false;
				ageCorrect = false;
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

				if (ageTextField.getText().equals(""))
				{
					errorAgeLabel.setText("Podaj cel podró¿y!");
				} else if (!isNumeric(ageTextField.getText()))
				{
					errorAgeLabel.setText("Nieprawid³owa wartoœæ!");
				} else
				{
					ageCorrect = true;
					errorAgeLabel.setText("");
				}

				if (finishInsuranceDatePicker.getValue() == null)
				{
					errorFinishInsuranceLabel.setText("Wybierz date zakonczenia umowy!!");
				} else
				{
					errorFinishInsuranceLabel.setText("");
				}

				if (valueCorrect && ageCorrect)
					addLifePolicy = true;

				if (addLifePolicy && (!valueTextField.getText().isEmpty() && !ageTextField.getText().isEmpty()
						&& !finishInsuranceDatePicker.getValue().equals(null)))
				{
					lifePolicy = new LifePolicy();

					lifePolicy.setAge(Integer.parseInt(ageTextField.getText()));
					lifePolicy.setInsurancePrice(Double.parseDouble(valueTextField.getText()));
					lifePolicy.setStartPolicyDate(LocalDate.now());
					lifePolicy.setFinishPolicyDate(finishInsuranceDatePicker.getValue());

					HibernateUtil.entityManager.getTransaction().begin();
					HibernateUtil.entityManager.persist(lifePolicy);
					HibernateUtil.entityManager.getTransaction().commit();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Dodawanie polisy");
					alert.setHeaderText(null);
					alert.setContentText("Dodanie polisy na ¿ycie zakoñczy³o siê sukcesem.\n");
					alert.showAndWait();

					ageTextField.setText("");
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
