package presentation;

import persistence.Operation;
import presentation.util.JsfUtil;
import presentation.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import service.ManageOperation;

@Named("operationController")
@SessionScoped
public class OperationController implements Serializable {

    private Operation current;
    private DataModel items = null;
    @EJB
    private ManageOperation manageOperation;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public OperationController() {
    }

    public Operation getSelected() {
        if (current == null) {
            current = new Operation();
            current.setOperationPK(new persistence.OperationPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return manageOperation.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(manageOperation.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Operation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Operation();
        current.setOperationPK(new persistence.OperationPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getOperationPK().setGammeDeFabrication(current.getArticle().getReference());
            manageOperation.create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("OperationCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Operation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getOperationPK().setGammeDeFabrication(current.getArticle().getReference());
            manageOperation.edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("OperationUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Operation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            manageOperation.remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("OperationDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = manageOperation.count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = manageOperation.findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(manageOperation.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(manageOperation.findAll(), true);
    }

    public Operation getOperation(persistence.OperationPK id) {
        return manageOperation.find(id);
    }

    @FacesConverter(forClass = Operation.class)
    public static class OperationControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OperationController controller = (OperationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "operationController");
            return controller.getOperation(getKey(value));
        }

        persistence.OperationPK getKey(String value) {
            persistence.OperationPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new persistence.OperationPK();
            key.setGammeDeFabrication(values[0]);
            key.setNumeroOperation(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(persistence.OperationPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGammeDeFabrication());
            sb.append(SEPARATOR);
            sb.append(value.getNumeroOperation());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Operation) {
                Operation o = (Operation) object;
                return getStringKey(o.getOperationPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Operation.class.getName());
            }
        }

    }

}
