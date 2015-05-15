/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AdrianGiesbrecht
 */
@Entity
@Table(name = "tbl_remates_categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRematesCategorias.findAll", query = "SELECT t FROM TblRematesCategorias t"),
    @NamedQuery(name = "TblRematesCategorias.findById", query = "SELECT t FROM TblRematesCategorias t WHERE t.id = :id"),
    @NamedQuery(name = "TblRematesCategorias.findByDescripcion", query = "SELECT t FROM TblRematesCategorias t WHERE t.descripcion = :descripcion")})
public class TblRematesCategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private Collection<TblRematesDetalle> tblRematesDetalleCollection;

    public TblRematesCategorias() {
    }

    public TblRematesCategorias(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TblRematesDetalle> getTblRematesDetalleCollection() {
        return tblRematesDetalleCollection;
    }

    public void setTblRematesDetalleCollection(Collection<TblRematesDetalle> tblRematesDetalleCollection) {
        this.tblRematesDetalleCollection = tblRematesDetalleCollection;
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
        if (!(object instanceof TblRematesCategorias)) {
            return false;
        }
        TblRematesCategorias other = (TblRematesCategorias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
