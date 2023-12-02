package com.uth.hn.views.reservas;

import com.uth.hn.controller.ReservasInteractor;
import com.uth.hn.controller.ReservasInteractorImpl;
import com.uth.hn.data.Reservas;
import com.uth.hn.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import com.vaadin.flow.component.combobox.ComboBox;

@PageTitle("Reservas")
@Route(value = "reservas/:reservasID?/:action?(edit)", layout = MainLayout.class)
@Uses(Icon.class)
public class ReservasView extends Div implements BeforeEnterObserver, ReservasViewModel {

    private final String RESERVAS_ID = "reservasID";
    private final String RESERVAS_EDIT_ROUTE_TEMPLATE = "reservas/%s/edit";

    private final Grid<Reservas> grid = new Grid<>(Reservas.class, false);

    private TextField idreserva;
    private TextField paquete;
    private TextField cliente;
    private DatePicker fecha;
    private TextField precio;
    private ComboBox<String> estado;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    private final Button delete = new Button("Eliminar", new Icon(VaadinIcon.TRASH));

    private Reservas reservas;
    private ReservasInteractor controladorReservas;
    private List<Reservas> elementos;


    public ReservasView( ) {
        addClassNames("reservas-view");
        
        controladorReservas = new ReservasInteractorImpl(this);
        this.elementos = new ArrayList<>();

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("idreserva").setAutoWidth(true).setHeader("Número de Reserva");
        //grid.addColumn("paquete").setAutoWidth(true).setHeader("Numero de Paquete");
        //grid.addColumn("cliente").setAutoWidth(true).setHeader("Numero de Cliente");
        grid.addColumn("fecha").setAutoWidth(true).setHeader("Fecha de Reserva");
        grid.addColumn("precio").setAutoWidth(true).setHeader("Precio Total");
        grid.addColumn("estado").setAutoWidth(true).setHeader("Estado");
        /*LitRenderer<Reservas> importantRenderer = LitRenderer.<Reservas>of(
                "<vaadin-icon icon='vaadin:${item.icon}' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: ${item.color};'></vaadin-icon>")
                .withProperty("icon", important -> important.isImportant() ? "check" : "minus").withProperty("color",
                        important -> important.isImportant()
                                ? "var(--lumo-primary-text-color)"
                                : "var(--lumo-disabled-text-color)");*/

        //grid.addColumn(importantRenderer).setHeader("Important").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(RESERVAS_EDIT_ROUTE_TEMPLATE, event.getValue().getIdreserva()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ReservasView.class);
            }
        });

        this.controladorReservas.consultarReservas();
        
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.reservas == null) {
                    this.reservas = new Reservas();
                }
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(ReservasView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        
        
    delete.addClickListener( e -> {
        	
        });
    
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> reservasIdreserva = event.getRouteParameters().get(RESERVAS_ID).map(Long::parseLong);
        if (reservasIdreserva.isPresent()) {
           /* Reservas reservasFromBackend = obtenerReservas(reservasIdreserva.get());
            if (reservasFromBackend.isPresent()) {
                populateForm(reservasFromBackend.get());
            } else {
                Notification.show(
                        String.format("La reserva con ID = %s no existe", reservasIdreserva.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(ReservasView.class);
            }*/
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        
        idreserva = new TextField("Número de Reserva");
        idreserva.setPrefixComponent(VaadinIcon.CALENDAR.create());
        
        ComboBox<Reservas> paquete = new ComboBox<>("Paquete");
        paquete.setItemLabelGenerator(Reservas::getPaquete);
        
        
        ComboBox<Reservas> cliente = new ComboBox<>("Cliente");
        cliente.setItemLabelGenerator(Reservas::getCliente);

        fecha = new DatePicker("Fecha de Reserva");

        NumberField precio = new NumberField();
        precio.setLabel("Precio Total");
        precio.setValue(0.0);
        Div dollarPrefix2 = new Div();
        dollarPrefix2.setText("$");
        precio.setPrefixComponent(dollarPrefix2);
        
        add(precio);
        
        estado = new ComboBox<>("Estado");
        estado.setItems("0", "1");
        estado.setAllowCustomValue(false);
        add( estado);
        
        formLayout.add( idreserva, paquete, cliente, fecha, precio, estado);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        
        buttonLayout.add(save, cancel, delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Reservas value) {
    	 this.reservas = value;

    }

	@Override
	public void mostrarReservasEnGrid(List<Reservas> items) {
		Collection<Reservas> itemsCollection = items;
		grid.setItems(itemsCollection);
		this.elementos = items;
		
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		Notification.show(mensaje);
		
	}
}
