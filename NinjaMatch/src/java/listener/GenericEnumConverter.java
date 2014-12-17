/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

/**
 *
 * @author atan
 */
import java.util.ArrayList;
import java.util.Collections;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
import model.Ethnicity;


@FacesConverter("genericEnumConverter")
public class GenericEnumConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) 
    {
       // ArrayList<String> items = new ArrayList<>();
       // return (Collections.addAll(items, value.split("\\s*,\\s*")));
        return Ethnicity.valueOf(value);
    }

    public String getAsString(FacesContext facesContext,UIComponent component, Object o) 
    {
        
        return o.toString();
    }
}