package pl.agencja.client.controller.admin.manage.policy.check;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.admin.manage.AddLifePaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.policy.LifePolicy;

public class CheckLifePolicyPaneController implements Initializable
{
	public static final String AGE_COLUMN = "Wiek";
	public static final String INSURANCE_PRICE_COLUMN = "Kwota ubezpieczenia";
	public static final String START_POLICY_DATE = "Data rozpoczêcia polisy";
	public static final String FINISH_POLICY_DATE = "Data rozpoczêcia polisy";

	@FXML
	private Button backCheckPolicyButton;

	@FXML
	private TableView<LifePolicy> lifePolicyTableView;

	@FXML
	private Pane contentPane;

	ArrayList<LifePolicy> lifeList;
	AddLifePaneController addLifePaneController;

	public void setContentPane(Pane contentPane)
	{
		this.contentPane = contentPane;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		configureCollection();
		configureButtons();
		configureTableView();

	}

	private void configureButtons()
	{

		backCheckPolicyButton.setOnAction(new EventHandler<ActionEvent>()
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

	}

	private void configureTableView()
	{
		TableColumn<LifePolicy, Double> ageColumn = new TableColumn<>(AGE_COLUMN);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<LifePolicy, Double> insurancePriceColumn = new TableColumn<>(INSURANCE_PRICE_COLUMN);
		insurancePriceColumn.setCellValueFactory(new PropertyValueFactory<>("insurancePrice"));

		TableColumn<LifePolicy, LocalDate> startPolicyColumn = new TableColumn<>(START_POLICY_DATE);
		startPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("startPolicyDate"));

		TableColumn<LifePolicy, LocalDate> finishPolicyColumn = new TableColumn<>(FINISH_POLICY_DATE);
		finishPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("finishPolicyDate"));

		lifePolicyTableView.getColumns().add(ageColumn);
		lifePolicyTableView.getColumns().add(insurancePriceColumn);
		lifePolicyTableView.getColumns().add(startPolicyColumn);
		lifePolicyTableView.getColumns().add(finishPolicyColumn);

	}

	@SuppressWarnings("unchecked")
	private void configureCollection()
	{
		HibernateUtil.entityManager.getTransaction().begin();
		lifeList = (ArrayList<LifePolicy>) HibernateUtil.entityManager.createQuery("from LifePolicy").getResultList();
		HibernateUtil.entityManager.getTransaction().commit();
		lifePolicyTableView.setItems(FXCollections.observableArrayList(lifeList));
	}
}
