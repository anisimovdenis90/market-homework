//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.13 at 08:20:25 PM GMT+03:00 
//


package ru.geekbrains.markethomework.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.geekbrains.spring.ws.products package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.geekbrains.spring.ws.products
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductRequest }
     */
    public GetProductRequest createGetProductRequest() {
        return new GetProductRequest();
    }

    /**
     * Create an instance of {@link GetAllProductsRequest }
     */
    public GetAllProductsRequest createGetAllProductsRequest() {
        return new GetAllProductsRequest();
    }

    /**
     * Create an instance of {@link GetProductResponse }
     */
    public GetProductResponse createGetProductResponse() {
        return new GetProductResponse();
    }

    /**
     * Create an instance of {@link ProductSoap }
     */
    public ProductSoap createProductSoap() {
        return new ProductSoap();
    }

    /**
     * Create an instance of {@link GetAllProductsResponse }
     */
    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

}
