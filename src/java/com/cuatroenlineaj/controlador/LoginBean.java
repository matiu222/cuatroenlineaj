/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuatroenlineaj.controlador;

import com.cuatroenlineaj.controlador.util.JsfUtil;
import com.cuatroenlineaj.modelo.Usuario;
import com.cuatroenlineaj.modelo.util.FacesUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
/**
 *
 * @author USUARIO
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{

private Usuario usuario;
    @EJB   
    private UsuarioFacade usuarioFacade;    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public LoginBean() {
    }
    @PostConstruct
    private void inicializar()
    {
        usuario= new Usuario();
    }
    
     public String ingresar()
    {       
    
        Usuario usuarioEncontrado=usuarioFacade.find(usuario.getCorreo());
        if(usuarioEncontrado != null)
        {
            if(usuario.getContrasena().compareTo(usuarioEncontrado.getContrasena())==0)
            {
                ControladorCuatroEnLinea contLinea= (ControladorCuatroEnLinea) FacesUtils.getManagedBean("controladorCuatroEnLinea");
                contLinea.setUsuario(usuarioEncontrado);
                if(usuarioEncontrado.getTipoUsuario().getId()==1)
                {
                    return "ingresar";
                }    
                else
                {
                    return "jugar";
                }
            }
            JsfUtil.addErrorMessage("Contraseña Incorrecta");
        }
        else
        {
            JsfUtil.addErrorMessage("El correo ingresado no existe");
        }
        return null;
    }
}