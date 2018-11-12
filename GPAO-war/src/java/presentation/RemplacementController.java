package presentation;

import persistence.Remplacement;
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
import service.ManageRemplacement;

@Named("remplacementController")
@SessionScoped
public class RemplacementController implements Serializable {

    private Remplacement current;
    private DataModel items = null;
    @EJB
    private ManageRemplacement manageRemplacement;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public RemplacementController() {
    }

    public Remplacement getSelected() {
        if (current == null) {
            current = new Remplacement();
            current.setRemplacementPK(new persistence.RemplacementPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return manageRemplacement.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(manageRemplacement.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        current = (Remplacement) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Remplacement();
        current.setRemplacementPK(new persistence.RemplacementPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getRemplacementPK().setRemplaceComposant(current.getRemplace().getLienDeNomenclaturePK().getComposant());
            current.getRemplacementPK().setRemplacantComposant(current.getRemplacant().getLienDeNomenclaturePK().getComposant());
            current.getRemplacementPK().setRemplaceCompose(current.getRemplace().getLienDeNomenclaturePK().getCompose());
            current.getRemplacementPK().setRemplacantCompose(current.getRemplacant().getLienDeNomenclaturePK().getCompose());
            manageRemplacement.create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("RemplacementCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Remplacement) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getRemplacementPK().setRemplaceComposant(current.getRemplace().getLienDeNomenclaturePK().getComposant());
            current.getRemplacementPK().setRemplacantComposant(current.getRemplacant().getLienDeNomenclaturePK().getComposant());
            current.getRemplacementPK().setRemplaceCompose(current.getRemplace().getLienDeNomenclaturePK().getCompose());
            current.getRemplacementPK().setRemplacantCompose(current.getRemplacant().getLienDeNomenclaturePK().getCompose());
            manageRemplacement.edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("RemplacementUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Remplacement) getItems().getRowData();
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
            manageRemplacement.remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("RemplacementDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = manageRemplacement.count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = manageRemplacement.findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
        return JsfUtil.getSelectItems(manageRemplacement.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(manageRemplacement.findAll(), true);
    }

    public Remplacement getRemplacement(persistence.RemplacementPK id) {
        return manageRemplacement.find(id);
    }

    @FacesConverter(forClass = Remplacement.class)
    public static class RemplacementControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RemplacementController controller = (RemplacementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "remplacementController");
            return controller.getRemplacement(getKey(value));
        }

        persistence.RemplacementPK getKey(String value) {
            persistence.RemplacementPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new persistence.RemplacementPK();
            key.setRemplaceCompose(values[0]);
            key.setRemplaceComposant(values[1]);
            key.setRemplacantCompose(values[2]);
            key.setRemplacantComposant(values[3]);
            return key;
        }

        String getStringKey(persistence.RemplacementPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getRemplaceCompose());
            sb.append(SEPARATOR);
            sb.append(value.getRemplaceComposant());
            sb.append(SEPARATOR);
            sb.append(value.getRemplacantCompose());
            sb.append(SEPARATOR);
            sb.append(value.getRemplacantComposant());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Remplacement) {
                Remplacement o = (Remplacement) object;
                return getStringKey(o.getRemplacementPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Remplacement.class.getName());
            }
        }

    }

}
