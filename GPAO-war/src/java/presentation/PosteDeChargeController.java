package presentation;

import persistence.PosteDeCharge;
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

@Named("posteDeChargeController")
@SessionScoped
public class PosteDeChargeController implements Serializable {

    private PosteDeCharge current;
    private DataModel items = null;
    @EJB
    private presentation.PosteDeChargeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PosteDeChargeController() {
    }

    public PosteDeCharge getSelected() {
        if (current == null) {
            current = new PosteDeCharge();
            current.setPosteDeChargePK(new persistence.PosteDeChargePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PosteDeChargeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        current = (PosteDeCharge) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PosteDeCharge();
        current.setPosteDeChargePK(new persistence.PosteDeChargePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("PosteDeChargeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PosteDeCharge) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("PosteDeChargeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PosteDeCharge) getItems().getRowData();
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
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("PosteDeChargeDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public PosteDeCharge getPosteDeCharge(persistence.PosteDeChargePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = PosteDeCharge.class)
    public static class PosteDeChargeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PosteDeChargeController controller = (PosteDeChargeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "posteDeChargeController");
            return controller.getPosteDeCharge(getKey(value));
        }

        persistence.PosteDeChargePK getKey(String value) {
            persistence.PosteDeChargePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new persistence.PosteDeChargePK();
            key.setNumeroSection(Integer.parseInt(values[0]));
            key.setNumeroSousSection(Integer.parseInt(values[1]));
            key.setEstMachine(Short.parseShort(values[2]));
            return key;
        }

        String getStringKey(persistence.PosteDeChargePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getNumeroSection());
            sb.append(SEPARATOR);
            sb.append(value.getNumeroSousSection());
            sb.append(SEPARATOR);
            sb.append(value.getEstMachine());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PosteDeCharge) {
                PosteDeCharge o = (PosteDeCharge) object;
                return getStringKey(o.getPosteDeChargePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PosteDeCharge.class.getName());
            }
        }

    }

}
