/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author stels
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQueries({
    @NamedQuery(name="BI.findAll", query = "SELECT a FROM BI a"),
    @NamedQuery(name="BI.findNB", query = "SELECT a FROM BI a WHERE a.numeroIdentificacao = :nb")
})
public class BI implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //id in database
    private String numeroIdentificacao; //identity card number
    private String nomeCompleto; //fullname
    private String nomePai; //father's name
    private String nomeMae; //mother's name
    private String residencia; //address
    private String naturalidade;  //nacionality
    private String provincia; //city
    private char sexo; //sex
    private double altura; //height
    private String estadoCivil; //civil status
    private Date dataEmicao; //emission date
    private Date dataValidade; //expity date
}
