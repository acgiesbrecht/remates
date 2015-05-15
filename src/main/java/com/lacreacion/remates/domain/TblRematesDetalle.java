/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AdrianGiesbrecht
 */
@Entity
@Table(name = "tbl_remates_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRematesDetalle.findAll", query = "SELECT t FROM TblRematesDetalle t"),
    @NamedQuery(name = "TblRematesDetalle.findByFechahora", query = "SELECT t FROM TblRematesDetalle t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblRematesDetalle.findById", query = "SELECT t FROM TblRematesDetalle t WHERE t.id = :id"),
    @NamedQuery(name = "TblRematesDetalle.findByMonto", query = "SELECT t FROM TblRematesDetalle t WHERE t.monto = :monto"),
    @NamedQuery(name = "TblRematesDetalle.findByObservacion", query = "SELECT t FROM TblRematesDetalle t WHERE t.observacion = :observacion")})
public class TblRematesDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "id_miembro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblMiembros idMiembro;
    @JoinColumn(name = "id_remate", referencedColumnName = "id")
    @ManyToOne
    private TblRemates idRemate;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblRematesCategorias idCategoria;

    public TblRematesDetalle() {
    }

    public TblRematesDetalle(Integer id) {
        this.id = id;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public TblMiembros getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(TblMiembros idMiembro) {
        this.idMiembro = idMiembro;
    }

    public TblRemates getIdRemate() {
        return idRemate;
    }

    public void setIdRemate(TblRemates idRemate) {
        this.idRemate = idRemate;
    }

    public TblRematesCategorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(TblRematesCategorias idCategoria) {
        this.idCategoria = idCategoria;
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
        if (!(object instanceof TblRematesDetalle)) {
            return false;
        }
        TblRematesDetalle other = (TblRematesDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacreacion.remates.domain.TblRematesDetalle[ id=" + id + " ]";
    }

}
