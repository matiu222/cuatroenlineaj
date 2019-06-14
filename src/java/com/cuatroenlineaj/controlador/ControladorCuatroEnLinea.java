/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuatroenlineaj.controlador;

import com.cuatroenlineaj.controlador.util.JsfUtil;
import com.cuatroenlineaj.modelo.Usuario;
import com.cuatroenlineaj.modelo.grafo.Arista;
import com.cuatroenlineaj.modelo.grafo.Ficha;
import com.cuatroenlineaj.modelo.grafo.Grafo;
import com.cuatroenlineaj.modelo.grafo.Vertice;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
/**
 *
 * @author USUARIO
 */
@Named(value = "controladorCuatroEnLinea")
@ApplicationScoped
public class ControladorCuatroEnLinea implements Serializable {

   private Usuario usuario;
    @EJB   
    private UsuarioFacade usuarioFacade;    
    
   
private int altoFichas = 6;
    private int altoIntermedio = 7;
   
    private int ancho = 7;
    private int alto = 6;
    private int total = ancho * alto;

    private byte distancia = 4;
    private DefaultDiagramModel model;
    private Grafo tablero = new Grafo();
    private int fichaClick;
    private Vertice vertice;
    private List boton;
    private boolean estadoJuego=false;
    private Date fichasSitema;
    
    public ControladorCuatroEnLinea() {
        boton = new ArrayList<>();
    }

    
   
    @PostConstruct
   public void pintarTablero()
    {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        
        colocar();
        
        int x = 18;
        int y = 5;
        String color = "Blanco";
        String StyleColor = "ui-diagram-element-ficha-blanca";
        
     for (int k = 1; k <= 6; k++) {

            for (int i = 1; i <= altoFichas; i++) {
                for (int j = 1; j <= ancho; j++) {
                    tablero.adicionarVertice(new Ficha(color, i, k));
                    Element ceo = new Element(tablero.getVertices().size(), x + "em", y + "em");
                    ceo.setDraggable(false);
                    ceo.setStyleClass(StyleColor);
                    ceo.addEndPoint(new BlankEndPoint(EndPointAnchor.CENTER));
                   
                    model.addElement(ceo);
                    x = x + distancia;
                }
                y = y + distancia ;
                x = 18;
            }
        }
        
        llenarAristas();

        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        //recorrer aristas
        for (Arista arista : tablero.getAristas()) {
            Element origen = model.getElements().get(arista.getOrigen() - 1);
            Element destino = model.getElements().get(arista.getDestino() - 1);
           
                    model.connect(new Connection(origen.getEndPoints().get(0), destino.getEndPoints().get(0)));
                    
                    
                    
            

        }
    }
    
 public void llenarAristas() {
         
        for (Vertice vertice : tablero.getVertices()) {
            if (vertice.getId() % ancho !=0 ) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + 1, 2);
            }
            if (vertice.getFicha().getNivel()< (altoFichas)) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + ancho, 1);
                
            }
              if (vertice.getFicha().getNivel()< (altoFichas) && 
                      vertice.getId() < ( ancho*altoFichas*vertice.getFicha().getTablero())+(vertice.getFicha().getNivel()
                 * ancho) && vertice.getId() % ancho !=0 ) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + ancho + 1, 1);
              }
            if (vertice.getFicha().getNivel()< (altoFichas) && 
                    vertice.getId() > ((vertice.getFicha().getNivel() 
                 * ancho) - ancho) + 1) {
                
                tablero.adicionarArista(vertice.getId(), vertice.getId() + ancho - 1, 1);
            }
            
    
        }
    }
 private EndPoint createEndPoint(EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setStyle("{fillStyle:'#404a4e'}");
        endPoint.setHoverStyle("{fillStyle:'#20282b'}");
         
        return endPoint;
    }
 
 public void activarJuego()
    {
        estadoJuego=true;
        JsfUtil.addSuccessMessage("Se ha habilitado el juego");
    }
 
 private void colocar(){
     for (int i=1; i <= ancho;i++){
         boton.add(i);
     }
 }
 
 public void accion(int numeroColumna, Usuario idColor){
  
      while (numeroColumna+ancho<=tablero.getVertices().size()){
          
          if(model.getElements().get(numeroColumna+ancho-1).getStyleClass().toString().compareTo("ui-diagram-element-grafo-blanco")==0){
          
          
          numeroColumna=numeroColumna+ancho;
          }else{
          
          break;
          }
          
      }
  String nombre = idColor.getNombre();
  model.getElements().get(numeroColumna-1).setStyleClass("ui-diagram-element-grafo-" + idColor.getColor());
  
  }
    public void onClickRight(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("elementId");

       
        fichaClick = vertice.getId();
       
    }
    
    
    public void pruebaMenu(){
        JsfUtil.addSuccessMessage(fichaClick + " presionada" );
    }

    public int getAltoFichas() {
        return altoFichas;
    }

    public void setAltoFichas(int altoFichas) {
        this.altoFichas = altoFichas;
    }

    public int getAltoIntermedio() {
        return altoIntermedio;
    }

    public void setAltoIntermedio(int altoIntermedio) {
        this.altoIntermedio = altoIntermedio;
    }

    public byte getDistancia() {
        return distancia;
    }

    public void setDistancia(byte distancia) {
        this.distancia = distancia;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }
     public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getFichaClick() {
        return fichaClick;
    }

    public void setFichaClick(int fichaClick) {
        this.fichaClick = fichaClick;
    }

    public List getBoton() {
        return boton;
    }

    public void setBoton(List boton) {
        this.boton = boton;
    }

    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(boolean estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public Date getFichasSitema() {
        return fichasSitema;
    }

    public void setFichasSitema(Date fichasSitema) {
        this.fichasSitema = fichasSitema;
    }

}
