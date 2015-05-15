/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AdrianGiesbrecht
 */
@Entity
@Table(name = "tbl_iglesia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIglesia.findAll", query = "SELECT t FROM TblIglesia t"),
    @NamedQuery(name = "TblIglesia.findById", query = "SELECT t FROM TblIglesia t WHERE t.id = :id"),
    @NamedQuery(name = "TblIglesia.findByNombre", query = "SELECT t FROM TblIglesia t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblIglesia.findByRuc", query = "SELECT t FROM TblIglesia t WHERE t.ruc = :ruc"),
    @NamedQuery(name = "TblIglesia.findByCtacte", query = "SELECT t FROM TblIglesia t WHERE t.ctacte = :ctacte"),
    @NamedQuery(name = "TblIglesia.findByDomicilio", query = "SELECT t FROM TblIglesia t WHERE t.domicilio = :domicilio"),
    @NamedQuery(name = "TblIglesia.findByBox", query = "SELECT t FROM TblIglesia t WHERE t.box = :box")})
public class TblIglesia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ruc")
    private Integer ruc;
    @Column(name = "ctacte")
    private Integer ctacte;
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "box")
    private Integer box;

    public TblIglesia() {
    }

    public TblIglesia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRuc() {
        return ruc;
    }

    public void setRuc(Integer ruc) {
        this.ruc = ruc;
    }

    public Integer getCtacte() {
        return ctacte;
    }

    public void setCtacte(Integer ctacte) {
        this.ctacte = ctacte;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getBox() {
        return box;
    }

    public void setBox(Integer box) {
        this.box = box;
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
        if (!(object instanceof TblIglesia)) {
            return false;
        }
        TblIglesia other = (TblIglesia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacreacion.remates.domain.TblIglesia[ id=" + id + " ]";
    }

}
