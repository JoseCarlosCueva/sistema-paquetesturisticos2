package com.uth.hn.views.paquetesturisticos;

import com.uth.hn.controller.PaquetesTuristicosInteractor;
import com.uth.hn.controller.PaquetesTuristicosInteractorImpl;
import com.uth.hn.data.PaquetesTuristicos;
import com.uth.hn.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.component.textfield.IntegerField;
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

@PageTitle("PaquetesTuristicos")
@Route(value = "paquetesTuristicos/:samplePersonID?/:action?(edit)", layout = MainLayout.class)
@Uses(Icon.class)
public class PaquetesTuristicosView extends Div implements BeforeEnterObserver, PaquetesTuristicosViewModel  {

    private final String SAMPLEPERSON_ID = "samplePersonID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "paquetesTuristicos/%s/edit";

    private final Grid<PaquetesTuristicos> grid = new Grid<>(PaquetesTuristicos.class, false);

    private TextField nombre;
    private TextField destino;
    private TextField precio;
    private TextField descripcion;
    private IntegerField duracion;
    private IntegerField cupo;
    
    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    private final Button delete = new Button("Eliminar", new Icon(VaadinIcon.TRASH));

    private PaquetesTuristicos paquetesTuristicos;
    private PaquetesTuristicosInteractor controlador;
    private List<PaquetesTuristicos> elementos;

    public PaquetesTuristicosView( ) {
        addClassNames("paquetes-turisticos-view");
        
        controlador = new PaquetesTuristicosInteractorImpl(this);
        this.elementos = new ArrayList<>();

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        //grid.addColumn("idPaquete").setAutoWidth(true).setHeader("Numero de Paquete");
        grid.addColumn("nombre").setAutoWidth(true).setHeader("Nombre del Paquete");
        grid.addColumn("destino").setAutoWidth(true).setHeader("Destino");
        grid.addColumn("precio").setAutoWidth(true).setHeader("Precio");
        grid.addColumn("descripcion").setAutoWidth(true).setHeader("Descripcion");
        grid.addColumn("duracion").setAutoWidth(true).setHeader("Duracion de noches");
        /*LitRenderer<PaquetesTuristicos> importantRenderer = LitRenderer.<PaquetesTuristicos>of(
                "<vaadin-icon icon='vaadin:${item.icon}' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: ${item.color};'></vaadin-icon>")
                .withProperty("icon", important -> important.isImportant() ? "check" : "minus").withProperty("color",
                        important -> important.isImportant()
                                ? "var(--lumo-primary-text-color)"
                                : "var(--lumo-disabled-text-color)");*/

        //grid.addColumn(importantRenderer).setHeader("cupoPersonas").setAutoWidth(true).setHeader("Cupo de Personas");
        grid.addColumn("cupo").setAutoWidth(true).setHeader("Cupo de Personas");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getIdPaquete()));
            } else {
                clearForm();
                UI.getCurrent().navigate(PaquetesTuristicosView.class);
            }
        });
        
        controlador.consultarPaquetesturisticos();

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.paquetesTuristicos == null) {
                    this.paquetesTuristicos = new PaquetesTuristicos();
                }
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(PaquetesTuristicosView.class);
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
        Optional<Long> samplePersonId = event.getRouteParameters().get(SAMPLEPERSON_ID).map(Long::parseLong);
        if (samplePersonId.isPresent()) {
           /* Optional<SamplePerson> samplePersonFromBackend = samplePersonService.get(samplePersonId.get());
            if (samplePersonFromBackend.isPresent()) {
                populateForm(samplePersonFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested samplePerson was not found, ID = %s", samplePersonId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(PaquetesTuristicosView.class);
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
        
        nombre = new TextField("Nombre del Paquete");
        nombre.setPrefixComponent(VaadinIcon.FOLDER_ADD.create());
        
        destino = new TextField("Destino");
        destino.setPrefixComponent(VaadinIcon.AIRPLANE.create());

        NumberField precio = new NumberField();
        precio.setLabel("Precio");
        precio.setValue(0.0);
        Div dollarPrefix = new Div();
        dollarPrefix.setText("$");
        precio.setPrefixComponent(dollarPrefix);
        
        add(precio);
        
        descripcion = new TextField("Descripcion");

        duracion = new IntegerField();
        duracion.setLabel("Duracion por noches");
        duracion.setMin(0);
        duracion.setMax(10);
        duracion.setValue(0);  // Asigna un valor Integer
        duracion.setStepButtonsVisible(true);
        add(duracion);


        cupo = new IntegerField();
        cupo.setLabel("Cupo maximo de Personas");
        cupo.setMin(0);
        cupo.setMax(10);
        cupo.setValue(0);  // Asigna un valor Integer
        cupo.setStepButtonsVisible(true);
        add(cupo);
        
        formLayout.add(nombre, destino, precio, descripcion, duracion, cupo);

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

    private void populateForm(PaquetesTuristicos value) {
        this.paquetesTuristicos = value;

    }

	@Override
	public void mostrarPaquetesTuristicosEnGrid(List<PaquetesTuristicos> items) {
		Collection<PaquetesTuristicos> itemsCollection = items;
		grid.setItems(itemsCollection);
		this.elementos = items;
		
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		Notification.show(mensaje);
		
	}

	
}
