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
import pl.agencja.client.controller.admin.manage.policy.check.CheckOcPolicyPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.policy.CarOcPolicy;

public class AddOcPaneController implements Initializable
{
	@FXML
	private Button backAddOcButton;

	@FXML
	private Button checkPolicyButton;

	@FXML
	private Button checkOcButton;

	@FXML
	private TextField productionYearTextField;

	@FXML
	private TextField valueTextField;

	@FXML
	private Label errorProductionYearLabel;

	@FXML
	private TextField markTextField;

	@FXML
	private DatePicker finishInsuranceDatePicker;

	@FXML
	private Label errorValueLabel;

	@FXML
	private Label errorFinishInsuranceLabel;

	@FXML
	private Button acceptButton;

	@FXML
	private Label nameLabel;

	@FXML
	private Label errorMarkLabel;

	@FXML
	private Pane contentPane;

	PolicyPaneController policyPaneController;
	CheckOcPolicyPaneController checkOcPolicyPaneController;

	public void setContentPane(Pane contentPane)
	{
		this.contentPane = contentPane;
	}

	CarOcPolicy carOcPolicy;
	boolean addOcPolicy;
	boolean markCorrect;
	boolean productionYearCorrect;
	boolean valueCorrect;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		configureButtons();
	}

	private void configureButtons()
	{
		backAddOcButton.setOnAction(new EventHandler<ActionEvent>()
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
							.getResource("/pl/agencja/client/view/admin/policy/manage/CheckOcPolicyPane.fxml"));
					loader.setController(new CheckOcPolicyPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					checkOcPolicyPaneController = (CheckOcPolicyPaneController) loader.getController();
					checkOcPolicyPaneController.setContentPane(contentPane);
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
				addOcPolicy = false;
				markCorrect = false;
				productionYearCorrect = false;
				valueCorrect = false;

				if (isNumeric(productionYearTextField.getText()))
				{
					productionYearCorrect = true;
					errorProductionYearLabel.setText("");
				} else if (productionYearTextField.getText().equals(""))
				{
					errorProductionYearLabel.setText("Podaj rok produkcji!");
				} else
				{
					errorProductionYearLabel.setText("Nieprawid³owa wartoœæ");
				}

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

				if (markTextField.getText().equals(""))
				{
					errorMarkLabel.setText("Podaj marke!");
				} else if (isNumeric(markTextField.getText()))
				{
					errorMarkLabel.setText("Nieprawid³owa wartoœæ!");
				} else
				{
					markCorrect = true;
					errorMarkLabel.setText("");
				}

				if (finishInsuranceDatePicker.getValue() == null)
				{
					errorFinishInsuranceLabel.setText("Wybierz date zakonczenia umowy!!");
				} else
				{
					errorFinishInsuranceLabel.setText("");
				}

				if (productionYearCorrect && valueCorrect && markCorrect)
					addOcPolicy = true;

				if (addOcPolicy && (!valueTextField.getText().isEmpty() && !markTextField.getText().isEmpty()
						&& !productionYearTextField.getText().isEmpty()
						&& !finishInsuranceDatePicker.getValue().equals(null)))
				{
					carOcPolicy = new CarOcPolicy();

					carOcPolicy.setCarMark(markTextField.getText());
					carOcPolicy.setCarValue(Integer.parseInt(valueTextField.getText()));
					carOcPolicy.setProductionYear(Integer.parseInt(productionYearTextField.getText()));
					carOcPolicy.setStartPolicyDate(LocalDate.now());
					carOcPolicy.setFinishPolicyDate(finishInsuranceDatePicker.getValue());

					HibernateUtil.entityManager.getTransaction().begin();
					HibernateUtil.entityManager.persist(carOcPolicy);
					HibernateUtil.entityManager.getTransaction().commit();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Dodawanie polisy");
					alert.setHeaderText(null);
					alert.setContentText("Dodanie polisy OC zakoñczy³o siê sukcesem.\n");
					alert.showAndWait();

					markTextField.setText("");
					valueTextField.setText("");
					productionYearTextField.setText("");
					finishInsuranceDatePicker.setValue(null);

				} else
				{

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³¹d dodawania");
					alert.setHeaderText(null);
					alert.setContentText("Nie mo¿na dodaæ polisy OC. \nPopraw dane w formularzu!");
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
