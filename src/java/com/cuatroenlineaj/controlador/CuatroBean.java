/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuatroenlineaj.controlador;

import com.cuatroenlineaj.modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.diagram.DefaultDiagramModel;

/**
 *
 * @author USUARIO
 */
@Named(value = "cuatroBean")
@SessionScoped
public class CuatroBean implements Serializable {

    /**
     * Creates a new instance of CuatroBean
     */
    public CuatroBean() {
    }
    private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioFacade;
    private DefaultDiagramModel model;

    /**
     * Creates a new instance of ControladorJuegoCuatroenlinea
     */
    
    @PostConstruct
    private void inicializar()
    {
       usuario = new Usuario();
    }
   

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }
    
}
