package presentation;

import persistence.MouvementDeStock;
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
import persistence.TypeMouvementStock;
import service.ManageMouvementDeStock;

@Named("mouvementDeStockController")
@SessionScoped
public class MouvementDeStockController implements Serializable {

    private MouvementDeStock current;
    private DataModel items = null;
    @EJB
    private ManageMouvementDeStock manageMouvementDeStock;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MouvementDeStockController() {
    }

    public MouvementDeStock getSelected() {
        if (current == null) {
            current = new MouvementDeStock();
            current.setMouvementDeStockPK(new persistence.MouvementDeStockPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return manageMouvementDeStock.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(manageMouvementDeStock.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        current = (MouvementDeStock) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MouvementDeStock();
        current.setMouvementDeStockPK(new persistence.MouvementDeStockPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getMouvementDeStockPK().setReference(current.getArticle().getReference());
            manageMouvementDeStock.create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("MouvementDeStockCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MouvementDeStock) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getMouvementDeStockPK().setReference(current.getArticle().getReference());
            manageMouvementDeStock.edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("MouvementDeStockUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MouvementDeStock) getItems().getRowData();
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
            manageMouvementDeStock.remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("MouvementDeStockDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = manageMouvementDeStock.count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = manageMouvementDeStock.findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
        return JsfUtil.getSelectItems(manageMouvementDeStock.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(manageMouvementDeStock.findAll(), true);
    }

    public MouvementDeStock getMouvementDeStock(persistence.MouvementDeStockPK id) {
        return manageMouvementDeStock.find(id);
    }

    @FacesConverter(forClass = MouvementDeStock.class)
    public static class MouvementDeStockControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MouvementDeStockController controller = (MouvementDeStockController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mouvementDeStockController");
            return controller.getMouvementDeStock(getKey(value));
        }

        persistence.MouvementDeStockPK getKey(String value) {
            persistence.MouvementDeStockPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new persistence.MouvementDeStockPK();
            key.setReference(values[0]);
            key.setPeriode(java.sql.Date.valueOf(values[1]));
            key.setType(TypeMouvementStock.valueOf(values[2]));
            return key;
        }

        String getStringKey(persistence.MouvementDeStockPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getReference());
            sb.append(SEPARATOR);
            sb.append(value.getPeriode());
            sb.append(SEPARATOR);
            sb.append(value.getType());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MouvementDeStock) {
                MouvementDeStock o = (MouvementDeStock) object;
                return getStringKey(o.getMouvementDeStockPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MouvementDeStock.class.getName());
            }
        }

    }

    public TypeMouvementStock[] getTypeMouvementStock() {
        return TypeMouvementStock.values();
    }
}
