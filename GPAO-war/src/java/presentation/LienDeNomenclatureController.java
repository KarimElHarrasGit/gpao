package presentation;

import persistence.LienDeNomenclature;
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

@Named("lienDeNomenclatureController")
@SessionScoped
public class LienDeNomenclatureController implements Serializable {

    private LienDeNomenclature current;
    private DataModel items = null;
    @EJB
    private presentation.LienDeNomenclatureFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public LienDeNomenclatureController() {
    }

    public LienDeNomenclature getSelected() {
        if (current == null) {
            current = new LienDeNomenclature();
            current.setLienDeNomenclaturePK(new persistence.LienDeNomenclaturePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private LienDeNomenclatureFacade getFacade() {
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
        current = (LienDeNomenclature) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new LienDeNomenclature();
        current.setLienDeNomenclaturePK(new persistence.LienDeNomenclaturePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getLienDeNomenclaturePK().setCompose(current.getArticle1().getReference());
            current.getLienDeNomenclaturePK().setComposant(current.getArticle().getReference());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("LienDeNomenclatureCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (LienDeNomenclature) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getLienDeNomenclaturePK().setCompose(current.getArticle1().getReference());
            current.getLienDeNomenclaturePK().setComposant(current.getArticle().getReference());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("LienDeNomenclatureUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/presentation/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (LienDeNomenclature) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/presentation/Bundle").getString("LienDeNomenclatureDeleted"));
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

    public LienDeNomenclature getLienDeNomenclature(persistence.LienDeNomenclaturePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = LienDeNomenclature.class)
    public static class LienDeNomenclatureControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LienDeNomenclatureController controller = (LienDeNomenclatureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lienDeNomenclatureController");
            return controller.getLienDeNomenclature(getKey(value));
        }

        persistence.LienDeNomenclaturePK getKey(String value) {
            persistence.LienDeNomenclaturePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new persistence.LienDeNomenclaturePK();
            key.setCompose(values[0]);
            key.setComposant(values[1]);
            return key;
        }

        String getStringKey(persistence.LienDeNomenclaturePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCompose());
            sb.append(SEPARATOR);
            sb.append(value.getComposant());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LienDeNomenclature) {
                LienDeNomenclature o = (LienDeNomenclature) object;
                return getStringKey(o.getLienDeNomenclaturePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + LienDeNomenclature.class.getName());
            }
        }

    }

}
