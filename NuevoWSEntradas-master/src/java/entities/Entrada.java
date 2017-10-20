/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alejandrohd
 */
@Entity
@Table(name = "ENTRADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrada.findAll", query = "SELECT e FROM Entrada e")
    , @NamedQuery(name = "Entrada.findById", query = "SELECT e FROM Entrada e WHERE e.id = :id")
    , @NamedQuery(name = "Entrada.findByPelicula", query = "SELECT e FROM Entrada e WHERE e.pelicula = :pelicula")
    , @NamedQuery(name = "Entrada.findBySala", query = "SELECT e FROM Entrada e WHERE e.sala = :sala")
    , @NamedQuery(name = "Entrada.findByFila", query = "SELECT e FROM Entrada e WHERE e.fila = :fila")
    , @NamedQuery(name = "Entrada.findByButaca", query = "SELECT e FROM Entrada e WHERE e.butaca = :butaca")})
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "PELICULA")
    private String pelicula;
    @Size(max = 25)
    @Column(name = "SALA")
    private String sala;
    @Column(name = "FILA")
    private Integer fila;
    @Column(name = "BUTACA")
    private Integer butaca;

    public Entrada() {
    }

    public Entrada(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getButaca() {
        return butaca;
    }

    public void setButaca(Integer butaca) {
        this.butaca = butaca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrada)) {
            return false;
        }
        Entrada other = (Entrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Entrada[ id=" + id + " ]";
    }
    
}
