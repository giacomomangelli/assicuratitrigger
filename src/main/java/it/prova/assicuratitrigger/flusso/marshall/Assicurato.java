//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.05.26 alle 05:23:04 PM CEST 
//


package it.prova.assicuratitrigger.flusso.marshall;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
/**
* <p>Classe Java per assicurato complex type.
* 
* <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
* 
* <pre>
* &lt;complexType name="assicurato">
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;sequence>
*         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
*         &lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string"/>
*         &lt;element name="datanascita" type="{http://www.w3.org/2001/XMLSchema}date"/>
*         &lt;element name="nuovisinistri" type="{http://www.w3.org/2001/XMLSchema}integer"/>
*         &lt;element name="codicefiscale" type="{http://www.w3.org/2001/XMLSchema}string"/>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assicurato", propOrder = {
 "nome",
 "cognome",
 "datanascita",
 "nuovisinistri",
 "codicefiscale"
})
public class Assicurato {

 @XmlElement(required = true)
 protected String nome;
 @XmlElement(required = true)
 protected String cognome;
 @XmlElement(required = true)
 @XmlSchemaType(name = "date")
 protected XMLGregorianCalendar datanascita;
 @XmlElement(required = true)
 protected BigInteger nuovisinistri;
 @XmlElement(required = true)
 protected String codicefiscale;

 /**
  * Recupera il valore della proprietà nome.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getNome() {
     return nome;
 }

 /**
  * Imposta il valore della proprietà nome.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setNome(String value) {
     this.nome = value;
 }

 /**
  * Recupera il valore della proprietà cognome.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCognome() {
     return cognome;
 }

 /**
  * Imposta il valore della proprietà cognome.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCognome(String value) {
     this.cognome = value;
 }

 /**
  * Recupera il valore della proprietà datanascita.
  * 
  * @return
  *     possible object is
  *     {@link XMLGregorianCalendar }
  *     
  */
 public XMLGregorianCalendar getDatanascita() {
     return datanascita;
 }

 /**
  * Imposta il valore della proprietà datanascita.
  * 
  * @param value
  *     allowed object is
  *     {@link XMLGregorianCalendar }
  *     
  */
 public void setDatanascita(XMLGregorianCalendar value) {
     this.datanascita = value;
 }

 /**
  * Recupera il valore della proprietà nuovisinistri.
  * 
  * @return
  *     possible object is
  *     {@link BigInteger }
  *     
  */
 public BigInteger getNuovisinistri() {
     return nuovisinistri;
 }

 /**
  * Imposta il valore della proprietà nuovisinistri.
  * 
  * @param value
  *     allowed object is
  *     {@link BigInteger }
  *     
  */
 public void setNuovisinistri(BigInteger value) {
     this.nuovisinistri = value;
 }

 /**
  * Recupera il valore della proprietà codicefiscale.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCodicefiscale() {
     return codicefiscale;
 }

 /**
  * Imposta il valore della proprietà codicefiscale.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCodicefiscale(String value) {
     this.codicefiscale = value;
 }

}
